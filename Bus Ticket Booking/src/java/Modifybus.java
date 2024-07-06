import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Modifybus extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // Get the logged-in email ID from the URL query parameters
        String loggedInEmail = request.getParameter("email"); // Assuming email is passed as a query parameter
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/MINORPROJECT", "root", "Somya@0407");
            PreparedStatement pstmt = cn.prepareStatement("SELECT * FROM addbus WHERE email = ?");
            pstmt.setString(1, loggedInEmail);
            ResultSet rs = pstmt.executeQuery();
            
            out.println("<html>");
            out.println("<head><title>All Buses</title>");
            out.println("<style>");
            out.println("table { width: 100%; border-collapse: collapse; }");
            out.println("th, td { padding: 8px; text-align: left; border-bottom: 1px solid #ddd; }");
            out.println("th { background-color: #f2f2f2; }");
            out.println("tr:hover { background-color: #f5f5f5; }");
            out.println("a { text-decoration: none; color: #007bff; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2 style='text-align:center;'>All Buses for " + loggedInEmail + "</h2>");
            out.println("<table>");
            out.println("<tr><th>Bus Number</th><th>Bus Name</th><th>Source</th><th>Destination</th><th>Departure Time</th><th>Arrival Time</th><th>Fare</th><th>Edit</th><th>Delete</th></tr>");
            while (rs.next()) {
                String busNumber = rs.getString("busNumber");
                out.println("<tr>");
                out.println("<td>" + rs.getString("busNumber") + "</td>");
                out.println("<td>" + rs.getString("busName") + "</td>");
                out.println("<td>" + rs.getString("source") + "</td>");
                out.println("<td>" + rs.getString("destination") + "</td>");
                out.println("<td>" + rs.getString("departureTime") + "</td>");
                out.println("<td>" + rs.getString("arrivalTime") + "</td>");
                out.println("<td>" + rs.getString("fare") + "</td>");
                out.println("<td><a href='Edit?busno=" + busNumber + "'>Edit</a></td>");
                out.println("<td><a href='Delete?busNumber=" + rs.getString("busNumber") + "&email=" + loggedInEmail + "'>Delete</a></td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("</body></html>");
            
            rs.close();
            pstmt.close();
            cn.close();
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }
}
