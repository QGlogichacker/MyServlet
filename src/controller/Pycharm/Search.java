package controller.Pycharm;

import Util.PostUtil;
import bean.Book;
import com.google.gson.Gson;
import dao.DbBook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "Search", urlPatterns = {"/search"})
public class Search extends HttpServlet {
    class SearchResult{
        ArrayList<Book> booklist;
        long time;
        public SearchResult(ArrayList<Book> booklist){
             time = System.currentTimeMillis();
             this.booklist = booklist;
        }

        public boolean timeUp (long limit){
            return System.currentTimeMillis()- this.time > limit;
        }

        protected ArrayList<Book> visit(){
            time = System.currentTimeMillis();
            return this.booklist;
        }
    }
    HashMap<String,SearchResult> hs = new HashMap<>();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap<String,String> hhs = PostUtil.getMap(request);
        String datas = hhs.get("datas");
        String key = "Searching"+datas;
        String index = hhs.get("index");
        if(hs.get(key)==null)
            try {
                hs.put(key,new SearchResult(DbBook.search(datas)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        //getData

        ArrayList<Book> books = hs.get(key).visit();
        int count = 0;
        ArrayList<Book> booksreturn = new ArrayList<>();
        for(int n=Integer.valueOf(index)+28,i=n-28;i<books.size()&&i<n;i++){
            booksreturn.add(books.get(i));
            count++;
        }
        HashMap mmp = new HashMap();
        mmp.put("status",""+!(count==0));
        mmp.put("books",booksreturn);
        PrintWriter writer = response.getWriter();
        writer.write(new Gson().toJson(mmp));
        writer.close();

        //扫描搜索库，时间太长未访问将删除这个库
        for(Map.Entry<String,SearchResult> en:hs.entrySet())
            if(en.getValue().timeUp(3600000))
                hs.remove(en.getKey());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
