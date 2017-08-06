package controller.Recom;

import bean.User;
import com.google.gson.Gson;
import dao.DbUser;

import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet(name = "Chart", urlPatterns = {"/chart"})
public class Chart extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap hashMap = new HashMap();
        HttpSession s = request.getSession();
        User usr = (User)s.getAttribute("login");
        try{ hashMap.put("ratio", DbUser.getChart(usr));}catch (Exception e){e.printStackTrace();}
//        try {
//            hashMap.put("ratio", DbUser.getChart(new User(9,"123","456")));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        if(usr!=null)
            hashMap.put("name",usr.getUser());
        PrintWriter writer = response.getWriter();
        writer.write(new Gson().toJson(hashMap));
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
