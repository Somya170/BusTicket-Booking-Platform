import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Submitt extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String organization_name=request.getParameter("organization_name");
            String name=request.getParameter("name");
            String contact_number=request.getParameter("contact_number");
            String email=request.getParameter("email");
            String address=request.getParameter("address");
            String username=request.getParameter("username");
            String password=request.getParameter("password");
            String registration_number=request.getParameter("registration_number");

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/MINORPROJECT","root","Somya@0407");
            Statement smt=cn.createStatement();
            int i=smt.executeUpdate("insert into busregister(organization_name,name,contact_number,email,address,username,password,registration_number,status) values('"+organization_name+"','"+name+"','"+contact_number+"','"+email+"','"+address+"','"+username+"','"+password+"','"+registration_number+"',false)");
            if(i>0)
            {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Registration Complete</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<div style='text-align: center;'>");
                out.println("<h1>Bus Agency Registration Complete</h1>");
                out.println("<h2>You'll receive an Email Within 24 Hours</h2>");
                out.println("</div>");
                out.println("</body>");
                out.println("</html>");

                RequestDispatcher rd=request.getRequestDispatcher("index.html");
                rd.include(request,response);
            }
            cn.close();
        }
        catch(Exception e){
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
