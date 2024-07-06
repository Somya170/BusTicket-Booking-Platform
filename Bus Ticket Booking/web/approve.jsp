<%@ page import="java.sql.*, java.io.*, javax.servlet.*, javax.servlet.http.*, com.mycompany.Mailer" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Approve</title>
</head>
<body>
<%
String email = request.getParameter("maill");

if (email != null && !email.isEmpty()) {
    try {
        // Load driver class (This could be done in a separate initialization phase)
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Create Connection using try-with-resources
        try (Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/minorproject", "root", "Somya@0407");
             PreparedStatement pstmt = cn.prepareStatement("UPDATE busregister SET status = ? WHERE email = ?")) {

            // Set parameters and execute update
            pstmt.setBoolean(1, true); // Set status to true (approved)
            pstmt.setString(2, email);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                String sub = "Operator Approve mail";
                String msg = "Your travel has been approved. You can login now";

                // Send mail
                try {
                    Mailer.send(email, sub, msg);
                    out.println("Mail sent successfully");
                } catch (Exception ex) {
                    out.println("Failed to send mail: " + ex.getMessage());
                }
            } else {
                out.println("No rows affected. Email not found.");
            }
        } catch (SQLException e) {
            out.println("Error connecting to the database: " + e.getMessage());
        }
    } catch (ClassNotFoundException e) {
        out.println("Error loading database driver: " + e.getMessage());
    } catch (Exception e) {
        out.println("An unexpected error occurred: " + e.getMessage());
    }
} else {
    out.println("Email parameter is missing or empty.");
}
%>
</body>
</html>
