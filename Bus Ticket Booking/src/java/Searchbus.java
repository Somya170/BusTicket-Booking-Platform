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

@WebServlet("/search")
public class Searchbus extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String email = request.getParameter("email"); // Retrieve the email parameter

        // Database connection details
        String url = "jdbc:mysql://localhost:3306/MINORPROJECT";
        String username = "root";
        String password = "Somya@0407";

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the database connection
            Connection conn = DriverManager.getConnection(url, username, password);

            // Prepare SQL statement to fetch buses with fare and seats available
            String sql = "SELECT busNumber, busName, departureTime, arrivalTime, fare, seats FROM addbus WHERE source = ? AND destination = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, from);
            pstmt.setString(2, to);

            // Execute the query
            ResultSet rs = pstmt.executeQuery();

            // Display search results
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Bus ON Hands - Search Result</title>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; background-color: #f5f5f5; margin: 0; padding: 0; }");
            out.println(".container { max-width: 600px; margin: 50px auto; padding: 20px; background-color: #fff; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }");
            out.println("h1 { color: #333; text-align: center; margin-bottom: 20px; }");
            out.println("ul { list-style-type: none; padding: 0; }");
            out.println("li { margin-bottom: 20px; border: 1px solid #ccc; border-radius: 5px; padding: 10px; background-color: #f9f9f9; }");
            out.println("form { display: inline-block; }");
            out.println("input[type=\"submit\"] { padding: 5px 10px; background-color: #4CAF50; color: white; border: none; border-radius: 3px; cursor: pointer; }");
            out.println("input[type=\"submit\"]:hover { background-color: #45a049; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class=\"container\">");
            out.println("<h1>Bus ON Hands - Search Result</h1>");
            out.println("<p>Buses available from " + from + " to " + to + ":</p>");
            out.println("<ul>");

            while (rs.next()) {
                String busName = rs.getString("busName");
                String departureTime = rs.getString("departureTime");
                String arrivalTime = rs.getString("arrivalTime");
                double fare = rs.getDouble("fare");
                int seats = rs.getInt("seats");
                String busNumber = rs.getString("busNumber"); // Assuming busNumber is a column in the addbus table

                out.println("<li>");
                out.println("<div><strong>" + busName + "</strong></div>");
                out.println("<div>Departure Time: " + departureTime + "</div>");
                out.println("<div>Arrival Time: " + arrivalTime + "</div>");
                out.println("<div>Fare: $" + fare + "</div>");
                out.println("<div>Seats Available: " + seats + "</div>");
                out.println("<form action='Book' method='post'>");
                out.println("<input type='hidden' name='busName' value='" + busName + "'>");
                out.println("<input type='hidden' name='busNumber' value='" + busNumber + "'>");
                out.println("<input type='hidden' name='email' value='" + email + "'>"); // Pass email as hidden input
                out.println("<input type='submit' value='Book Bus'>");
                out.println("</form>");
                out.println("</li>");
            }

            out.println("</ul>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");

            // Close resources
            rs.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            out.println("An error occurred: " + e.getMessage());
        }
    }
}
