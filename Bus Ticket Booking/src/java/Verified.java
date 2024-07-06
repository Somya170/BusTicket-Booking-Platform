import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Verified extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<h1>Verified Travel Agencies</h1>"); // Title added
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/minorproject","root","Somya@0407");
            Statement smt = cn.createStatement();
           ResultSet rs = smt.executeQuery("SELECT * FROM busregister WHERE status = 1");
 out.println("<title>Verified Travel Agencies</title>");
            out.println("<table border='2'>");
            out.println("<tr><th>Id</th><th>Organization Name</th><th>Name</th><th>Contact Number</th><th>Email</th><th>Address</th><th>Username</th><th>Password</th><th>Registration Number</th></tr>");

            while(rs.next()){
                String Id = rs.getString("id");
                String organizationname = rs.getString("organization_name");
                String Name = rs.getString("name");
                String Cno = rs.getString("contact_number");
                String Emaill = rs.getString("email");
                String Addresss = rs.getString("address");
                String Uname = rs.getString("username");
                String psd = rs.getString("password");
                String Rno = rs.getString("registration_number");

                out.println("<tr><td>" + Id + "</td><td>" + organizationname + "</td><td>" + Name + "</td><td>" + Cno + "</td><td>" + Emaill + "</td><td>" + Addresss + "</td><td>" + Uname + "</td><td>" + psd + "</td><td>" + Rno + "</td></tr>");
            }
            out.println("</table>");
        }
        catch(ClassNotFoundException | SQLException e) {
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
