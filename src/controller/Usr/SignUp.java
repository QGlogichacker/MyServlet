package controller.Usr;

import Util.PostUtil;
import bean.User;
import dao.Dblogin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//TODO:JUMP TO LOGIN
@WebServlet(name = "SignUp", urlPatterns = {"/signUp"})
public class SignUp extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User usr = PostUtil.getUser(request);
        try {
            PrintWriter writer = response.getWriter();
            writer.write("{\n\"status\":\"" + Dblogin.signUp(usr) + "\"\n}");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
