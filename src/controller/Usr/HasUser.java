package controller.Usr;

import Util.PostUtil;
import dao.Dblogin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet(name = "HasUser", urlPatterns = {"/hasUser"})
public class HasUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        try {
            HashMap hs = PostUtil.getMap(request);
            String s = (String) hs.get("user");
            writer.write(Dblogin.hasUser(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
