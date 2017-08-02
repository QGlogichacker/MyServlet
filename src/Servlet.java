
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "servlet", urlPatterns = {"/Servlet"})
public class Servlet extends javax.servlet.http.HttpServlet {
    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        session.setAttribute("name", "jiahao");
        session.setAttribute("password", "123456");
        System.out.println(session.getAttribute("name"));
        System.out.println(session.getAttribute("password"));


        String s = request.getParameter("name");
        response.setContentType("text/html;Charset = utf-8");
        PrintWriter writer = response.getWriter();
        writer.println("<html>");
        writer.println("<title>");
        writer.println("Welcome");
        writer.println("</title>");
        writer.println("<body>");
        writer.println("Hello !!!" + s + "!\nMainView");
        writer.println("</body>");
        writer.println("</html>");
        writer.close();
    }
}
