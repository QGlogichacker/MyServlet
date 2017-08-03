package controller.Recom;

import bean.Book;
import bean.User;
import com.google.gson.Gson;
import dao.DbBook;
import dao.DbUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "controller/Recom", urlPatterns = {"/recom"})
public class Recom extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        //User usr = (User) request.getSession().getAttribute("login");
        User usr = new User(9,"123","456");
        try {
            ArrayList<Book> books = DbBook.getCom(DbUser.getType(usr),4);
            if(books!=null){
                writer.write(new Gson().toJson(books));
            }else{
                writer.write("{}");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            writer.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
