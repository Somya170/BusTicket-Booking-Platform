import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addbus")
public class Addbus extends HttpServlet {

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/MINORPROJECT";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "Somya@0407";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String busNumber = request.getParameter("busNumber");
        String busName = request.getParameter("busName");
        String source = request.getParameter("source");
        String destination = request.getParameter("destination");
        String departureTime = request.getParameter("departureTime");
        String arrivalTime = request.getParameter("arrivalTime");
        String fare = request.getParameter("fare");
        int seats = Integer.parseInt(request.getParameter("seats")); // Assuming the input is properly parsed from HTML form
        String busType = request.getParameter("busType");
        String email = request.getParameter("email");

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Open a connection
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            // SQL query to insert bus details into addbus table
            String addbusSql = "INSERT INTO addbus (busNumber, busName, source, destination, departureTime, arrivalTime, fare, seats, busType, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            // Create PreparedStatement object for addbus table
            stmt = conn.prepareStatement(addbusSql);

            // Set parameters for addbus table
            stmt.setString(1, busNumber);
            stmt.setString(2, busName);
            stmt.setString(3, source);
            stmt.setString(4, destination);
            stmt.setString(5, departureTime);
            stmt.setString(6, arrivalTime);
            stmt.setString(7, fare);
            stmt.setInt(8, seats);
            stmt.setString(9, busType);
            stmt.setString(10, email);

            // Execute the query for addbus table
            stmt.executeUpdate();

            // SQL query to insert bus details into bookingbus_details table
            String bookingSql = "INSERT INTO bookingbus_details (busNumber, busName, source, destination, departureTime, arrivalTime, fare, seats, busType, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            // Create PreparedStatement object for bookingbus_details table
            stmt = conn.prepareStatement(bookingSql);

            // Set parameters for bookingbus_details table
            stmt.setString(1, busNumber);
            stmt.setString(2, busName);
            stmt.setString(3, source);
            stmt.setString(4, destination);
            stmt.setString(5, departureTime);
            stmt.setString(6, arrivalTime);
            stmt.setString(7, fare);
            stmt.setInt(8, seats);
            stmt.setString(9, busType);
            stmt.setString(10, email);

            // Execute the query for bookingbus_details table
            stmt.executeUpdate();

            // Print success message
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Bus Added</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Bus Added Successfully</h1>");
            out.println("<p>Bus Number: " + busNumber + "</p>");
            out.println("<p>Bus Name: " + busName + "</p>");
            out.println("<p>Source: " + source + "</p>");
            out.println("<p>Destination: " + destination + "</p>");
            out.println("<p>Departure Time: " + departureTime + "</p>");
            out.println("<p>Arrival Time: " + arrivalTime + "</p>");
            out.println("<p>Fare: " + fare + "</p>");
            out.println("<p>Total Seats: " + seats + "</p>");
            out.println("<p>Bus Type: " + busType + "</p>");
            out.println("<p>Email: " + email + "</p>");
            out.println("</body>");
            out.println("</html>");

        } catch (ClassNotFoundException | SQLException ex) {
            out.println("Error: " + ex.getMessage());
        } finally {
            try {
                // Close resources
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                out.println("Error: " + ex.getMessage());
            }
        }
    }
}
