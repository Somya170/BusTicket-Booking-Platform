import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin_home extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/MINORPROJECT", "root", "Somya@0407");
            Statement smt = cn.createStatement();
            ResultSet rs = smt.executeQuery("select * from BUSREGISTER");

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Admin Home</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f4; }");
            out.println(".container { text-align: center; margin-top: 50px; }");
            out.println("h1 { color: #333; }");
            out.println(".button { background-color: #4CAF50; color: white; padding: 14px 20px; margin: 8px 0; border: none; cursor: pointer; border-radius: 4px; text-decoration: none; display: inline-block; font-size: 16px; transition: background-color 0.3s ease; width: 200px; }");
            out.println(".button:hover { background-color: #45a049; }");
            out.println(".button-container { margin-bottom: 20px; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<h1>Welcome to Admin Home</h1>");
            out.println("<div class='button-container'><a href='Unverified' class='button'>Show All Unverified Agencies</a></div>");
            out.println("<div class='button-container'><a href='Verified' class='button'>Show All Verified Agencies</a></div>");
            out.println("<div class='button-container'><a href='Allagencies' class='button'>Show All Agencies</a></div>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");

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
