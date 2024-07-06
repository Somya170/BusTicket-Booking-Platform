import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Admindata extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            // Load driver class
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create Connection
            try (Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/MINORPROJECT", "root", "Somya@0407");
                    // Create statement
                    Statement smt = cn.createStatement();
                    // Execute query
                    ResultSet rs = smt.executeQuery("SELECT * FROM busregister")) {

                // Start HTML response
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Admin Data</title>");
                out.println("<h1>Admin Data</h1>");
                out.println("</head>");
                out.println("<body>");
           

                // Start table
                out.println("<table border='1'>");
                out.println("<tr><th>Organization Name</th><th>Name</th><th>Contact Number</th><th>Email</th><th>Address</th><th>Username</th><th>Password</th><th>Registration Number</th></tr>");

                // Iterate over result set
                while (rs.next()) {
    String organization_name = rs.getString("organization_name");
    String name = rs.getString("name");
    String contact_number = rs.getString("contact_number");
    String email = rs.getString("email");
    String address = rs.getString("address");
    String username = rs.getString("username");
    String password = rs.getString("password");
    String registration_number = rs.getString("registration_number");

    // Print table row with Approve and Disapprove options
    out.println("<tr>");
    out.println("<td>" + organization_name + "</td>");
    out.println("<td>" + name + "</td>");
    out.println("<td>" + contact_number + "</td>");
    out.println("<td>" + email + "</td>");
    out.println("<td>" + address + "</td>");
    out.println("<td>" + username + "</td>");
    out.println("<td>" + password + "</td>");
    out.println("<td>" + registration_number + "</td>");
    out.println("<td><a href='approve.jsp?maill="+email+"'>Approve</a></td>");
    out.println("<td><a href='disapprove.jsp?id=" + registration_number + "'>Disapprove</a></td>");
    out.println("</tr>");
}

                // End table and HTML response
                out.println("</table>");
                out.println("</body>");
                out.println("</html>");
            }
        } catch (Exception e) {
            // Handle exceptions
            out.println("An error occurred: " + e.getMessage());
            e.printStackTrace(out);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Admin Data Servlet";
    }
}
