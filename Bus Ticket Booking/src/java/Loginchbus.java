import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Loginchbus extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = response.getWriter();
    
        try {
            String email=request.getParameter("email");
            String password=request.getParameter("password");
         
            Class.forName("com.mysql.cj.jdbc.Driver");
         
            Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/MINORPROJECT","root","Somya@0407");
            Statement smt=cn.createStatement();
         
    ResultSet rs = smt.executeQuery("SELECT * FROM busregister WHERE email='" + email + "' AND password='" + password + "' AND status = 1");

         
            if(rs.next()){
                String name = rs.getString("name"); // Fetch name from the database
                pw.println("Successfully Login");
                // Forwarding to User servlet and passing email and name as parameters
             RequestDispatcher rd=request.getRequestDispatcher("agenciehome.jsp?email=" + email + "&name=" + name);
                rd.forward(request,response);
            } else {
                pw.println("<h2>Invalid User</h1>");
                RequestDispatcher rd=request.getRequestDispatcher("index.html");
                rd.include(request, response);
            }
            cn.close();
        } catch(Exception e) {
            pw.println(e.getMessage());
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
