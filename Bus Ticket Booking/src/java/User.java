import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class User extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        if (email != null && !email.isEmpty() && name != null && !name.isEmpty()) {
            // Store email in session
            HttpSession session = request.getSession();
            session.setAttribute("email", email);
            session.setAttribute("name", name);

            out.println("<h1>Welcome, " + name + "</h1>");
            RequestDispatcher rd = request.getRequestDispatcher("indexuser.jsp");
            rd.include(request, response);
        } else {
            out.println("Please provide valid email and name");
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
