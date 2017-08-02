package controller.Usr;

import Util.PostUtil;
import bean.User;
import com.google.gson.Gson;
import dao.Dblogin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User usr = PostUtil.getUser(request);
        try {
            User user = Dblogin.login(usr);
            session.setAttribute("login", user);
            if (user != null) {
                request.getSession().setAttribute("login", user);
                //request.getRequestDispatcher("/Servlet?name="+user.getUser()).forward(request,response);
                HashMap hs = new HashMap();
                hs.put("status", "true");
                hs.put("user", user.getUser());
                hs.put("url", "/Servlet?name=" + user.getUser());
                PrintWriter writer = response.getWriter();
                writer.write(new Gson().toJson(hs));
                writer.close();
            } else response.getWriter().write("{\n\"status\":\"false\"\n}");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
