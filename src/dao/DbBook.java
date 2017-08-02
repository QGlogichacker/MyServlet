package dao;

import Util.MyUtil;
import bean.Book;
import bean.User;
import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DbBook {
    public static Book[] hotBooks() throws Exception {
        DbUtil dbutil = new DbUtil();
        Connection connection = null;
        //调用方法返回数据库的连接
        connection = dbutil.getCon();
        //保存对数据库的操作语句
        String sql = "select * from books";

        //预编译sql
        PreparedStatement pstm = connection.prepareStatement(sql);

        //编译sql
        ResultSet rs = pstm.executeQuery();

        User usr = null;
        //处理结果集，返回对应用户
        ArrayList<Book> books = new ArrayList<>();
        while (rs.next())
            books.add(new Book(rs.getString("book_name"), rs.getString("author"), rs.getString("rating"), rs.getString("picture")));
        dbutil.close(pstm, connection);
        return MyUtil.toArray(books, Book.class);
    }

    public static Book[] search() throws Exception {
        DbUtil dbutil = new DbUtil();
        Connection connection = null;
        //调用方法返回数据库的连接
        connection = dbutil.getCon();
        //保存对数据库的操作语句
        String sql = "select * from books WHERE ";

        //预编译sql
        PreparedStatement pstm = connection.prepareStatement(sql);

        //编译sql
        ResultSet rs = pstm.executeQuery();

        User usr = null;
        //处理结果集，返回对应用户
        ArrayList<Book> books = new ArrayList<>();
        while (rs.next())
            books.add(new Book(rs.getString("book_name"), rs.getString("author"), rs.getString("rating"), rs.getString("picture")));
        dbutil.close(pstm, connection);
        return MyUtil.toArray(books, Book.class);
    }

    public static void main(String[] args) throws Exception {
        System.out.println(new Gson().toJson(hotBooks()));
    }
}
