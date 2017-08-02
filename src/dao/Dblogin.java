package dao;

import bean.User;
import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Dblogin {
    public static User login(User user) throws Exception {
        DbUtil dbutil = new DbUtil();
        Connection connection = null;
        //调用方法返回数据库的连接
        connection = dbutil.getCon();
        //保存对数据库的操作语句
        String sql = "select * from usr where usr=? and pas=?";

        //预编译sql
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, user.getUser());
        pstm.setString(2, user.getPassword());

        //编译sql
        ResultSet rs = pstm.executeQuery();


        User usr = null;
        //处理结果集，返回对应用户
        if (rs.next())
            usr = new User(rs.getInt("id"), rs.getString("usr"), rs.getString("pas"));
        dbutil.close(pstm, connection);
        return usr;
    }

    public static String hasUser(String name) throws Exception {
        if (name == null)
            return null;
        DbUtil dbutil = new DbUtil();
        Connection connection = dbutil.getCon();
        //保存对数据库的操作语句
        String sql = "select * from usr where usr=?";

        //预编译sql
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, name);

        //编译sql
        ResultSet rs = pstm.executeQuery();


        //处理结果集，返回对应用户
        boolean b = false;
        if (rs.next())
            b = true;

        dbutil.close(pstm, connection);
        return "{\n\"status\":\"" + b + "\"\n}";
    }

    public static boolean signUp(User user) throws Exception {
        if (user == null)
            return false;
        DbUtil dbutil = new DbUtil();
        Connection connection = dbutil.getCon();
        //保存对数据库的操作语句
        String sql = "select * from usr where usr=?";

        //预编译sql
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, user.getUser());

        //编译sql
        ResultSet rs = pstm.executeQuery();

        //处理结果集，返回对应用户
        if (rs.next())
            return false;

        sql = "insert into qglibtest.usr (usr,pas) values (?,?);";

        //预编译sql
        pstm = connection.prepareStatement(sql);
        pstm.setString(1, user.getUser());
        pstm.setString(2, user.getPassword());
        pstm.execute();

        dbutil.close(pstm, connection);
        return true;
    }

    public static void main(String[] args) {
        Gson g = new Gson();
        String s = g.toJson(new User("123", "666"));
        User user = g.fromJson(s, User.class);
        System.out.println(user);
    }

}
