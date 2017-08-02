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
            books.add(new Book(rs.getString("book_name"), rs.getString("author"), rs.getString("rating"), rs.getString("picture"),rs.getString("ISBN"),rs.getString("douban"),rs.getString("content"),rs.getString("cla")).contentCut(20));
        dbutil.close(pstm, connection);
        return MyUtil.toArray(books, Book.class);
    }

    public static ArrayList<Book> search(String keyWord) throws Exception {
        DbUtil dbutil = new DbUtil();
        Connection connection = null;
        //调用方法返回数据库的连接
        connection = dbutil.getCon();
        String enq = "%"+keyWord+"%";
        //保存对数据库的操作语句
        String sql = "select * from bookEX WHERE book_name LIKE ? OR author LIKE ? OR cla LIKE ? OR content LIKE ? OR aboutwriter LIKE ?";

        //预编译sql
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,enq);
        pstm.setString(2,enq);
        pstm.setString(3,enq);
        pstm.setString(4,enq);
        pstm.setString(5,enq);


        //编译sql
        ResultSet rs = pstm.executeQuery();

        User usr = null;
        //处理结果集，返回对应用户
        ArrayList<Book> books = new ArrayList<>();
        while (rs.next())
            books.add(new Book(rs.getString("book_name"), rs.getString("author"), rs.getString("rating"), rs.getString("picture"),rs.getString("ISBN"),rs.getString("douban"),rs.getString("content"),rs.getString("cla")).contentCut(20));
        dbutil.close(pstm, connection);
        return books;
    }

    public static Book bookEx(String isbn) throws Exception {
        DbUtil dbutil = new DbUtil();
        Connection connection = null;
        //调用方法返回数据库的连接
        connection = dbutil.getCon();
        //保存对数据库的操作语句
        String sql = "select * from bookEX WHERE ISBN=?";

        //预编译sql
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,isbn);

        //编译sql
        ResultSet rs = pstm.executeQuery();

        Book b = null;
        //处理结果集，返回对应用户
        ArrayList<Book> books = new ArrayList<>();
        if (rs.next())
            b = new Book(rs.getString("book_name"), rs.getString("author"), rs.getString("rating"), rs.getString("picture"),rs.getString("douban"),rs.getString("content"),rs.getString("aboutwriter"),rs.getString("cla"),rs.getString("ISBN"));
        dbutil.close(pstm, connection);
        return b;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(new Gson().toJson(hotBooks()));
    }
}
