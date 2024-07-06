# BusTicket-Booking-Platform
Java-based web app for booking bus tickets using JSP, Servlets, MySQL for storage, and Apache Tomcat for deployment. Features include user authentication, booking management, and email notifications for confirmations. Technologies: Java, Servlets, JSP, HTML, CSS, MySQL, Apache Tomcat, Git, Maven

Bus Ticket Booking Web Application This repository contains a Java-based web application designed for booking bus tickets efficiently. It utilizes JSP (JavaServer Pages) and Servlets on the backend, with HTML and CSS for frontend presentation. The application integrates a MySQL database for data storage and Apache Tomcat for server deployment.

Features User Authentication: Secure registration and login functionality. Booking System: Interactive interface for selecting bus routes, dates, and seats. Email Notifications: Integration of a custom mailer class for booking confirmations. Admin Dashboard: Management interface for bus schedules and user bookings. Responsive Design: Ensures usability across various devices. Technologies Used Backend: Java, Servlets Frontend: JSP, HTML, CSS Database: MySQL Server: Apache Tomcat Tools: Git, Maven Installation Clone the repository:

bash Copy code git clone https://github.com/your-username/your-repo.git Set up MySQL database:

Create a database and import the schema from database-schema.sql. Configure database connection details in db.properties. Deploy on Apache Tomcat:

Build the project using Maven. Deploy the generated WAR file on Apache Tomcat. Usage Access the application through http://localhost:8080/bus-ticket-booking. Register as a new user or login with existing credentials. Browse available bus routes, select travel dates, and book seats. Receive email notifications for booking confirmations.
