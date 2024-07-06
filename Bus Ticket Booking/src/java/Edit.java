import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Edit extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String busNumber = request.getParameter("busno");
        String busName = "", source = "", destination = "", departureTime = "", arrivalTime = "", fare = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/MINORPROJECT", "root", "Somya@0407");
            Statement smt = cn.createStatement();
            ResultSet rs = smt.executeQuery("SELECT * FROM addbus WHERE busNumber = '" + busNumber + "'");

            if (rs.next()) {
                busName = rs.getString("busName");
                source = rs.getString("source");
                destination = rs.getString("destination");
                departureTime = rs.getString("departureTime");
                arrivalTime = rs.getString("arrivalTime");
                fare = rs.getString("fare");
            }
            cn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Edit Bus</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background-color: #f5f5f5; margin: 0; padding: 0; }");
        out.println("h1 { text-align: center; }");
        out.println("form { max-width: 400px; margin: 20px auto; background-color: #fff; padding: 20px; border-radius: 5px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }");
        out.println("label { display: block; margin-bottom: 10px; }");
        out.println("input[type='text'] { width: calc(100% - 22px); padding: 8px; margin-bottom: 15px; }");
        out.println("input[type='submit'] { width: 100%; padding: 10px; background-color: #4CAF50; color: #fff; border: none; border-radius: 5px; cursor: pointer; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Edit Bus</h1>");
        out.println("<form action='Update' method='post'>");
        out.println("<input type='hidden' name='busNumber' value='" + busNumber + "'>");
        out.println("<label>Bus Name: <input type='text' name='busName' value='" + busName + "'></label>");
        out.println("<label>Source: <input type='text' name='source' value='" + source + "'></label>");
        out.println("<label>Destination: <input type='text' name='destination' value='" + destination + "'></label>");
        out.println("<label>Departure Time: <input type='text' name='departureTime' value='" + departureTime + "'></label>");
        out.println("<label>Arrival Time: <input type='text' name='arrivalTime' value='" + arrivalTime + "'></label>");
        out.println("<label>Fare: <input type='text' name='fare' value='" + fare + "'></label>");
        out.println("<input type='submit' value='Update'>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
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
