package dao;

import bean.User;
import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class DbUser {
    public static final ArrayList<String> match = new ArrayList<>();
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

    public static boolean hasUser(String name) throws Exception {
        if (name == null)
            return false;
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
        return b;
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


        //有这个用户
        if (rs.next()){
            dbutil.close(pstm,connection);
            return false;
        }
        sql = "insert into qglibtest.usr (usr,pas) values (?,?);";

        //预编译sql
        pstm.close();
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

    public HashMap<String, Integer> getChart(User usr) throws Exception {
        if (usr == null)
            return null;
        DbUtil dbutil = new DbUtil();
        Connection connection = dbutil.getCon();
        //保存对数据库的操作语句
        String sql = "select * from usr where id=?";

        //预编译sql
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setInt(1, usr.id);

        //编译sql
        ResultSet rs = pstm.executeQuery();

        HashMap<String,Integer> hs = new HashMap<>();
        hs.put("文学",0);
        hs.put("流行",0);
        hs.put("文化",0);
        hs.put("生活",0);
        hs.put("经管",0);
        hs.put("科技",0);

        //处理结果集，返回对应用户
        if(rs.next()) {
            pstm.close();
            String views= rs.getString("view");
            sql = "select * from bookEx where ISBN IN ("+views+")";

            //预编译sql
            pstm = connection.prepareStatement(sql);

            //编译sql
            ResultSet newRs = pstm.executeQuery();
            while(newRs.next()){
                String type = newRs.getString("type");
                hs.replace(type,hs.get(type)+1);
            }
        }else{
            dbutil.close(pstm, connection);
            return null;
        }
        dbutil.close(pstm, connection);
        //return true;
        return  hs;
    }

}
