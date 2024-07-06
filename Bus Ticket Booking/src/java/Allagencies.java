/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

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

/**
 *
 * @author VICTUS
 */
public class Allagencies extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
 out.println("All Travel agencies");
        
         try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/minorproject","root","Somya@0407");
        
        Statement  smt = cn.createStatement();
        
ResultSet rs = smt.executeQuery("SELECT * FROM busregister");
    out.println("<table border='2'>");
        out.println("<tr><th>Id</th><th>organizationname</th><th>Name</th><th>Cno</th><th>Emaill</th><th>Addresss</th><th>Uname</th><th>psd</th><th>Rno</th><th>Delete</th></tr>");


        while(rs.next()){
            String Id=rs.getString("id");
            String organizationname =rs.getString("organization_name");
            String Name=rs.getString("name");
            String Cno=rs.getString("contact_number");
            String Emaill=rs.getString("email");
            String Addresss=rs.getString("address");
              String Uname=rs.getString("username");
                String psd=rs.getString("password");
                  String Rno=rs.getString("registration_number");
             
       // Inside the while loop where you generate the approval link
out.println("<tr><td>" + Id + "</td><td>" + organizationname + "</td><td>" + Name + "</td><td>" + Cno + "</td><td>" + Emaill + "</td><td>" + Addresss + "</td><td>" + Uname + "</td><td>" + psd + "</td><td>" + Rno + "</td><td><a href='Busdelete?id=" + Id + "'>Delete</a></td></tr>");

            
        }
        out.println("</table>");
          
    }
    catch(ClassNotFoundException | SQLException e){
        out.println(e.getMessage());
    }
  
}
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}// </editor-fold>


