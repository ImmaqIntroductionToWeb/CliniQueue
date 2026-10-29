# CliniQueue

An offline queue management system for rural clinics, built with Java Swing and SQLite.

## Demo
[Watch the demo video](https://youtu.be/GqI43gAhr7c))

## The problem
Many rural clinics have long, unstructured queues, unreliable internet
and no wifi. Patients don't know when they will be seen, and staff have
no reliable record of who arrived first.

## The solution
CliniQueue is a kiosk-style desktop app that runs fully offline. A patient
checks in, registers, chooses their reason for visiting, and receives a
ticket with a queue number and room number. Tickets are saved in a local
database, so the queue is not lost if the power goes off.

## How it works
1. **Check-In:** the patient starts at the welcome screen.
2. **Register:** the patient enters their name, surname, ID number & File number if applicable.
   The app checks that no field is empty and that the ID is exactly
   13 digits.
3. **Reason for visit:** the patient picks a colour-coded button with an icon.
4. **Ticket:** the patient gets a ticket for their visit type.
   - **General illness:** the ticket is saved to the database and the
     number (for example G004) is generated from the saved records.
   - **Chronic medication:** the ticket shows the patient's details and
     file number.
   - **Treatments:** Wound Management has its own ticket page.

## Screenshots
![Check-in](screenshots/checkin.png)
![Reason for visit](screenshots/reason.png)
![Register](screenshots/register.png)
![Ticket](screenshots/ticket.png)

## Design decisions
- **Offline-first:** runs on one local computer with no internet needed.
- **Local SQLite database:** a single file with no server to install or
  maintain. Each ticket is written to the database immediately, so a
  power cut does not lose the queue.
- **Queue numbers from the database:** the next ticket number is based on
  the saved records, so numbering continues correctly after a restart.
- **Low-literacy users:** colour-coded buttons with icons and a simple flow.
- **Privacy (POPIA):** only the minimum details are collected, and the
  database file is kept off GitHub.
- **Secure database access:** uses prepared statements to protect against
  SQL injection.
- **Input validation:** required fields and a 13-digit ID check.

## Built with
Java, Swing, NetBeans, Maven, SQLite (JDBC)

## Future improvements
- Save Chronic Med and Treatment tickets to the database
- Staff screen to call the next patient
- Remaining treatment options (only Wound Management is built)
- Emergency fast-track
- isiZulu language option
- Ticket printing
