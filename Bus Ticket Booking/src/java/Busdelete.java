import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteAgency
 */
public class Busdelete extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        // Get the ID of the agency to be deleted from the request parameter
        String id = request.getParameter("id");
        
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Create a statement
            try ( // Establish the database connection
                    Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/minorproject", "root", "Somya@0407"); // Create a statement
                    Statement smt = cn.createStatement()) {
                
                // Execute the delete query
                int rowsAffected = smt.executeUpdate("DELETE FROM busregister WHERE id = '" + id + "'");
                
                if (rowsAffected > 0) {
                    out.println("Agency deleted successfully.");
                } else {
                    out.println("Failed to delete agency.");
                }
                
            }
        } catch (ClassNotFoundException | SQLException e) {
            out.println(e.getMessage());
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
