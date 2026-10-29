package com.mycompany.cliniqueue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class Database {

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:cliniqueue.db");
    }

    public static void createTables() {
        String sql = "CREATE TABLE IF NOT EXISTS queue ("
                   + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                   + "name TEXT, surname TEXT, id_number TEXT,"
                   + "visit_type TEXT, ticket TEXT, room INTEGER,"
                   + "status TEXT DEFAULT 'waiting',"
                   + "created_at TEXT DEFAULT CURRENT_TIMESTAMP)";
        try (Connection c = connect(); Statement s = c.createStatement()) {
            s.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void saveTicket(String name, String surname, String idNumber,
                              String visitType, String ticket, int room) {
    String sql = "INSERT INTO queue (name, surname, id_number, visit_type, ticket, room) "
               + "VALUES (?, ?, ?, ?, ?, ?)";
    try (Connection c = connect(); PreparedStatement p = c.prepareStatement(sql)) {
        p.setString(1, name);
        p.setString(2, surname);
        p.setString(3, idNumber);
        p.setString(4, visitType);
        p.setString(5, ticket);
        p.setInt(6, room);
        p.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
   }
    
    public static String nextTicket(String prefix) {
    String sql = "SELECT COUNT(*) FROM queue WHERE ticket LIKE ?";
    try (Connection c = connect(); PreparedStatement p = c.prepareStatement(sql)) {
        p.setString(1, prefix + "%");
        java.sql.ResultSet rs = p.executeQuery();
        int count = rs.next() ? rs.getInt(1) : 0;
        return String.format("%s%03d", prefix, count + 1);
    } catch (SQLException e) {
        e.printStackTrace();
        return prefix + "001";
    }
}
  }