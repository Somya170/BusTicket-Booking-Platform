<%@ page import="java.sql.*, java.io.*, javax.servlet.*, javax.servlet.http.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Disapprove</title>
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
             PreparedStatement pstmt = cn.prepareStatement("DELETE FROM busregister WHERE email = ?")) {

            // Set parameter and execute update
            pstmt.setString(1, email);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                out.println("<h1>Agency Disapproved Successfully!</h1>");
            } else {
                out.println("<h1>Error Disapproving Agency!</h1>");
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
    out.println("<h1>Email parameter is missing or empty.</h1>");
}
%>
</body>
</html>
