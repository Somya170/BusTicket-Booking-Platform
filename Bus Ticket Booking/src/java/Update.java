import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Update extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        // Retrieve form parameters
        String busNumber = request.getParameter("busNumber");
        String busName = request.getParameter("busName");
        String source = request.getParameter("source");
        String destination = request.getParameter("destination");
        String departureTime = request.getParameter("departureTime");
        String arrivalTime = request.getParameter("arrivalTime");
        String fare = request.getParameter("fare");
        
        try {
            // Establish database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/MINORPROJECT", "root", "Somya@0407");
            
            // Prepare SQL statement for updating bus details
            PreparedStatement pstmt = cn.prepareStatement("UPDATE addbus SET busName=?, source=?, destination=?, departureTime=?, arrivalTime=?, fare=? WHERE busNumber=?");
            pstmt.setString(1, busName);
            pstmt.setString(2, source);
            pstmt.setString(3, destination);
            pstmt.setString(4, departureTime);
            pstmt.setString(5, arrivalTime);
            pstmt.setString(6, fare);
            pstmt.setString(7, busNumber);
            
            // Execute the update query
            int rowsAffected = pstmt.executeUpdate();
            
            // Check if the update was successful
            if (rowsAffected > 0) {
                out.println("<h2>Bus details updated successfully!</h2>");
                
                // Redirect to Modifybus servlet
                response.sendRedirect("Modifybus?email+");
            } else {
                out.println("<h2>Failed to update bus details!</h2>");
            }
            
            // Close resources
            pstmt.close();
            cn.close();
        } catch (Exception e) {
            out.println("<h2>Error: " + e.getMessage() + "</h2>");
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
        return "Short description";
    }
}
