import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Payment")
public class Payment extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Retrieve bookingNumber from request parameter
        int bookingNumber = Integer.parseInt(request.getParameter("bookingNumber"));

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

            // Prepare SQL statement to retrieve booking details by bookingNumber
            String sql = "SELECT * FROM BookingSummary WHERE bookingNumber = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, bookingNumber);

            // Execute the query
            rs = pstmt.executeQuery();

            // Check if booking details are found
            if (rs.next()) {
                // Retrieve booking details from ResultSet
                String email = rs.getString("email");
                String busName = rs.getString("busName");
                String busNumber = rs.getString("busNumber");
                double totalFare = rs.getDouble("totalFare");

                // Display payment form
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Payment Page</title>");
                out.println("<meta charset=\"UTF-8\">");
                out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
                out.println("<style>");
                out.println("body { font-family: Arial, sans-serif; background-color: #f5f5f5; margin: 0; padding: 0; }");
                out.println(".container { max-width: 600px; margin: 50px auto; padding: 20px; background-color: #fff; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }");
                out.println("h1 { color: #333; text-align: center; margin-bottom: 20px; }");
                out.println(".payment-form { margin-bottom: 20px; }");
                out.println("input[type=text], input[type=submit] { padding: 10px; width: 100%; margin-bottom: 10px; }");
                out.println("input[type=submit] { background-color: #007bff; color: white; border: none; border-radius: 5px; cursor: pointer; }");
                out.println("input[type=submit]:hover { background-color: #0056b3; }");
                out.println("</style>");
                out.println("</head>");
                out.println("<body>");
                out.println("<div class=\"container\">");
                out.println("<h1>Payment Page</h1>");
                out.println("<div class=\"payment-form\">");
                out.println("<form action=\"Payment\" method=\"post\">");
                out.println("<input type=\"hidden\" name=\"bookingNumber\" value=\"" + bookingNumber + "\">");
                out.println("<label>Email: " + email + "</label><br>");
                out.println("<label>Bus Name: " + busName + "</label><br>");
                out.println("<label>Bus Number: " + busNumber + "</label><br>");
                out.println("<label>Total Fare: $" + totalFare + "</label><br>");
                out.println("<input type=\"text\" name=\"utrNumber\" placeholder=\"Enter UTR Number\" required><br>");
                out.println("<input type=\"submit\" value=\"Submit Payment\">");
                out.println("</form>");
                out.println("</div>");
                out.println("</div>");
                out.println("</body>");
                out.println("</html>");
            } else {
                out.println("<html><body><h3>Error: Booking details not found.</h3></body></html>");
            }
        } catch (ClassNotFoundException | SQLException e) {
            out.println("Error fetching booking details: " + e.getMessage());
        } finally {
            // Close all database resources
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                out.println("Error closing database resources: " + e.getMessage());
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Retrieve form parameters
        int bookingNumber = Integer.parseInt(request.getParameter("bookingNumber"));
        String utrNumber = request.getParameter("utrNumber");

        // Database connection details
        String url = "jdbc:mysql://localhost:3306/MINORPROJECT";
        String username = "root";
        String password = "Somya@0407";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the database connection
            conn = DriverManager.getConnection(url, username, password);

            // Prepare SQL statement to update UTR number in BookingSummary
            String sql = "UPDATE BookingSummary SET utrNumber = ? WHERE bookingNumber = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, utrNumber);
            pstmt.setInt(2, bookingNumber);

            // Execute the update
            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                // Payment successful, send email confirmation
                sendEmailConfirmation(bookingNumber, conn);
                
                // Display success message
                out.println("<html><body><h3>Payment successful!</h3><p>You will receive a confirmation email shortly.</p></body></html>");
            } else {
                out.println("<html><body><h3>Error: Payment failed. Please try again.</h3></body></html>");
            }
        } catch (ClassNotFoundException | SQLException e) {
            out.println("Error processing payment: " + e.getMessage());
        } finally {
            // Close all database resources
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                out.println("Error closing database resources: " + e.getMessage());
            }
        }
    }

    private void sendEmailConfirmation(int bookingNumber, Connection conn) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Prepare SQL statement to retrieve booking details by bookingNumber
            String sql = "SELECT email, busName, busNumber, totalFare FROM BookingSummary WHERE bookingNumber = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, bookingNumber);

            // Execute the query
            rs = pstmt.executeQuery();

            // Check if booking details are found
            if (rs.next()) {
                // Retrieve booking details from ResultSet
                String email = rs.getString("email");
                String busName = rs.getString("busName");
                String busNumber = rs.getString("busNumber");
                double totalFare = rs.getDouble("totalFare");

                // Simulate sending email (replace with your actual email sending logic)
                System.out.println("Sending email confirmation to: " + email);
                System.out.println("Booking details:");
                System.out.println("Bus Name: " + busName);
                System.out.println("Bus Number: " + busNumber);
                System.out.println("Total Fare: $" + totalFare);
            } else {
                System.out.println("Booking details not found for bookingNumber: " + bookingNumber);
            }
        } finally {
            // Close ResultSet and PreparedStatement
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
    }
}
