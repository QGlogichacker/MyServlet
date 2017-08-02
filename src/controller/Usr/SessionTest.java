package controller.Usr;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SessionTest", urlPatterns = {"/count"})
public class SessionTest extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        int count = 0;
        HttpSession hs = request.getSession();
        if (hs.getAttribute("count") != null) {
            Integer c = (Integer) hs.getAttribute("count");
            count = c + 1;
        }
        hs.setAttribute("count", count);
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet Count</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>ServletCount" + count + "</h1>");
        out.println("<a href=\"" + response.encodeURL("count") + "\">countUp</a>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}
