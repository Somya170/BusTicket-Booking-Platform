import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Checkout")
public class Checkout extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Retrieve form parameters
        String email = request.getParameter("email");
        String busName = request.getParameter("busName");
        String busNumber = request.getParameter("busNumber");
        double fare = Double.parseDouble(request.getParameter("fare"));
        int totalSeats = Integer.parseInt(request.getParameter("totalSeats"));
        int maleSeats = Integer.parseInt(request.getParameter("maleSeats"));
        int femaleSeats = Integer.parseInt(request.getParameter("femaleSeats"));
        int seaterSeats = Integer.parseInt(request.getParameter("seaterSeats"));
        int sleeperSeats = Integer.parseInt(request.getParameter("sleeperSeats"));
        String busType = request.getParameter("busType");

        // Calculate total fare
        double totalFare = fare * totalSeats;

        // Validate the inputs (if needed)
        if (maleSeats + femaleSeats > totalSeats || seaterSeats + sleeperSeats > totalSeats) {
            out.println("<html><body><h3>Error: Invalid seat selection.</h3></body></html>");
            return;
        }

        // Database connection details
        String url = "jdbc:mysql://localhost:3306/MINORPROJECT";
        String username = "root";
        String password = "Somya@0407";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the database connection
            conn = DriverManager.getConnection(url, username, password);

            // Prepare SQL statement to insert booking details
            String sql = "INSERT INTO BookingSummary (email, busName, busNumber, fare, totalSeats, maleSeats, femaleSeats, seaterSeats, sleeperSeats, busType, totalFare) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, email);
            pstmt.setString(2, busName);
            pstmt.setString(3, busNumber);
            pstmt.setDouble(4, fare);
            pstmt.setInt(5, totalSeats);
            pstmt.setInt(6, maleSeats);
            pstmt.setInt(7, femaleSeats);
            pstmt.setInt(8, seaterSeats);
            pstmt.setInt(9, sleeperSeats);
            pstmt.setString(10, busType);
            pstmt.setDouble(11, totalFare);

            // Execute the update
            int affectedRows = pstmt.executeUpdate();

            // Check if the insert was successful
            if (affectedRows > 0) {
                // Retrieve the auto-generated booking number
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    int bookingNumber = rs.getInt(1); // Retrieve the generated bookingNumber
                    // Redirect to payment page with the bookingNumber
                    response.sendRedirect("Payment?bookingNumber=" + bookingNumber);
                    return;
                } else {
                    out.println("<html><body><h3>Error: Failed to retrieve booking number.</h3></body></html>");
                    return;
                }
            } else {
                out.println("<html><body><h3>Error: Inserting booking details failed.</h3></body></html>");
                return;
            }
        } catch (Exception e) {
            out.println("Error saving booking details: " + e.getMessage());
        } finally {
            // Close all database resources
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                out.println("Error closing database resources: " + e.getMessage());
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
