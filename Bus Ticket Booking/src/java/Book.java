import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/book")
public class Book extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        // Retrieve parameters from request
        String busName = request.getParameter("busName");
        String busNumber = request.getParameter("busNumber");
        String email = request.getParameter("email"); // Retrieve email parameter

        // Validate inputs
        if (busName == null || busNumber == null || busName.isEmpty() || busNumber.isEmpty()) {
            out.println("<html><body><h3>Invalid bus name or number.</h3></body></html>");
            return;
        }

        // Database connection details
        String url = "jdbc:mysql://localhost:3306/MINORPROJECT";
        String username = "root";
        String password = "Somya@0407";

        // Initialize fare amount and total seats
        double fareAmount = 0.0;
        int totalSeats = 0;

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the database connection
            Connection conn = DriverManager.getConnection(url, username, password);

            // Prepare SQL statement to fetch fare amount and total seats
            String sql = "SELECT fare, seats FROM addbus WHERE busName = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, busName);

            // Execute the query
            ResultSet rs = pstmt.executeQuery();

            // Retrieve fare amount and total seats
            if (rs.next()) {
                fareAmount = rs.getDouble("fare");
                totalSeats = rs.getInt("seats");
            } else {
                out.println("<html><body><h3>Bus not found.</h3></body></html>");
                return;
            }

            // Close resources
            rs.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            out.println("<html><body><h3>An error occurred: " + e.getMessage() + "</h3></body></html>");
            return;
        }

        // Display booking form
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Booking Details</title>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background-color: #f5f5f5; margin: 0; padding: 0; }");
        out.println(".container { max-width: 600px; margin: 50px auto; padding: 20px; background-color: #fff; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }");
        out.println("h1 { color: #333; text-align: center; margin-bottom: 20px; }");
        out.println("form { margin-bottom: 20px; }");
        out.println("input[type=\"submit\"] { padding: 10px; background-color: #007bff; color: white; border: none; border-radius: 5px; cursor: pointer; }");
        out.println("input[type=\"submit\"]:hover { background-color: #0056b3; }");
        out.println("</style>");
        out.println("<script>");
        out.println("function updateFare() {");
        out.println("  const totalSeats = document.getElementById('totalSeats').value;");
        out.println("  const fare = " + fareAmount + ";");
        out.println("  document.getElementById('totalFare').innerText = '$' + (totalSeats * fare).toFixed(2);");
        out.println("}");
        out.println("function validateSeats() {");
        out.println("  const totalSeats = parseInt(document.getElementById('totalSeats').value);");
        out.println("  const maleSeats = parseInt(document.getElementById('maleSeats').value);");
        out.println("  const femaleSeats = parseInt(document.getElementById('femaleSeats').value);");
        out.println("  const seaterSeats = parseInt(document.getElementById('seaterSeats').value);");
        out.println("  const sleeperSeats = parseInt(document.getElementById('sleeperSeats').value);");
        out.println("  if (maleSeats + femaleSeats !== totalSeats) {");
        out.println("    alert('Total male and female seats must be equal to total seats selected');");
        out.println("    return false;");
        out.println("  }");
        out.println("  if (seaterSeats + sleeperSeats !== totalSeats) {");
        out.println("    alert('Total seater and sleeper seats must be equal to total seats selected');");
        out.println("    return false;");
        out.println("  }");
        out.println("  return true;");
        out.println("}");
        out.println("</script>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class=\"container\">");
        out.println("<h1>Booking Details</h1>");
        out.println("<form action='Checkout' method='post' onsubmit='return validateSeats()'>");
        out.println("<p><strong>Bus Name:</strong> " + busName + "</p>");
        out.println("<p><strong>Bus Number:</strong> " + busNumber + "</p>");
        out.println("<p>Select Total Seats (Max " + totalSeats + "):</p>");
        out.println("<input type='number' id='totalSeats' name='totalSeats' min='1' max='" + totalSeats + "' required onchange='updateFare()'><br>");
        out.println("<p>Select Number of Male Seats:</p>");
        out.println("<input type='number' id='maleSeats' name='maleSeats' min='0' max='" + totalSeats + "' required><br>");
        out.println("<p>Select Number of Female Seats:</p>");
        out.println("<input type='number' id='femaleSeats' name='femaleSeats' min='0' max='" + totalSeats + "' required><br>");
        out.println("<p>Select Number of Seater Seats:</p>");
        out.println("<input type='number' id='seaterSeats' name='seaterSeats' min='0' max='" + totalSeats + "' required><br>");
        out.println("<p>Select Number of Sleeper Seats:</p>");
        out.println("<input type='number' id='sleeperSeats' name='sleeperSeats' min='0' max='" + totalSeats + "' required><br>");
        out.println("<p>Select Bus Type:</p>");
        out.println("<select name='busType' required>");
        out.println("<option value='sleeper'>Sleeper</option>");
        out.println("<option value='seater'>Seater</option>");
        out.println("<option value='both'>Both</option>");
        out.println("</select><br><br>");
        out.println("<p>Amount Payable for 1 Ticket: $" + fareAmount + "</p>");
        out.println("<p>Total Amount Payable: <span id='totalFare'>$0.00</span></p>");
        out.println("<input type='hidden' name='busName' value='" + busName + "'>");
        out.println("<input type='hidden' name='busNumber' value='" + busNumber + "'>");
        out.println("<input type='hidden' name='fare' value='" + fareAmount + "'>");
        out.println("<input type='hidden' name='email' value='" + email + "'>"); // Pass email as hidden input
        out.println("<input type='submit' value='Checkout'>");
        out.println("</form>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
