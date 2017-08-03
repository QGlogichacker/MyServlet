package dao;

import Util.MyUtil;
import bean.Book;
import bean.User;
import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class DbUser {
    public static ArrayList<String> Claname;
    private static Random random = new Random();
    DbUser(){
        super();
    }

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
        sql = "insert into qglibtest.usr (usr,pas,hist) values (?,?,\"\")";

        //预编译sql
        pstm.close();
        pstm = connection.prepareStatement(sql);
        pstm.setString(1, user.getUser());
        pstm.setString(2, user.getPassword());
        pstm.execute();

        dbutil.close(pstm, connection);
        return true;
    }

    public static void main(String[] args) throws Exception {
        updateView(new User("123", "666"),"1");
    }

    public static HashMap<String, Integer> getChart(User usr) throws Exception {
        if (usr == null)
            return null;
        if(Claname==null){
            Claname = new ArrayList<>();
            Claname.add("文学");
            Claname.add("流行");
            Claname.add("文化");
            Claname.add("生活");
            Claname.add("经管");
            Claname.add("科技");
        }
        DbUtil dbutil = new DbUtil();
        Connection connection = dbutil.getCon();
        //保存对数据库的操作语句
        String sql = "select * from usr where id=?";

        //预编译sql
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setInt(1, usr.id);

        //编译sql
        ResultSet rs = pstm.executeQuery();

        int count[] = new int[6];

        //处理结果集，返回对应的投票器
        if(rs.next()) {
            String views= rs.getString("hist");
            sql = "select * from bookEX where ISBN IN ("+views+")";


            //预编译sql
            pstm.close();
            pstm = connection.prepareStatement(sql);

            //编译sql
            ResultSet newRs = pstm.executeQuery();
            while(newRs.next()){
                String type = newRs.getString("cla");
                count[Claname.indexOf(type)]++;
            }
        }else{
            dbutil.close(pstm, connection);
            return null;
        }
        dbutil.close(pstm, connection);
        //return true;
        HashMap hs = new HashMap();
        for(int i=0;i<6;i++)
            hs.put(Claname.get(i),count[i]);
        return  hs;
    }

    public static String getType(User usr) throws Exception {
        if (usr == null)
            return null;
        if(Claname==null){
            Claname = new ArrayList<>();
            Claname.add("文学");
            Claname.add("流行");
            Claname.add("文化");
            Claname.add("生活");
            Claname.add("经管");
            Claname.add("科技");
        }
        String retu="";
        DbUtil dbutil = new DbUtil();
        Connection connection = dbutil.getCon();
        //保存对数据库的操作语句
        String sql = "select * from usr where id=?";

        //预编译sql
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setInt(1, usr.id);

        //编译sql
        ResultSet rs = pstm.executeQuery();

        int count[] = new int[6];

        //处理结果集，返回对应的投票器
        if(rs.next()) {
            String views= rs.getString("hist");
            sql = "select * from bookEX where ISBN IN ("+views+")";

            //预编译sql
            pstm.close();
            pstm = connection.prepareStatement(sql);

            //编译sql
            ResultSet newRs = pstm.executeQuery();
            while(newRs.next()){
                String type = newRs.getString("cla");
                count[Claname.indexOf(type)]++;
            }
            int max = 4;
            //int index =random.nextInt(5);
            int index = 0;
            for(int i=0;i<6;i++)
                if(count[i]>max){
                    max = count[i];
                    index = i;
                }
            retu = Claname.get(index);
        }else{
            dbutil.close(pstm, connection);
            return null;
        }
        dbutil.close(pstm, connection);
        return retu;
    }

    public static void updateView(User usr,String isbn) throws Exception {
        if (usr == null||isbn==null)
            return;
        DbUtil dbutil = new DbUtil();
        Connection connection = dbutil.getCon();
        //保存对数据库的操作语句
        String sql = "Update usr SET hist=IF(hist=\"\",?,concat(hist,\",\",?)) where id=?";

        //预编译sql
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, isbn);
        pstm.setString(2, isbn);
        pstm.setInt(3, usr.id);

        //编译sql
        pstm.execute();
        dbutil.close(pstm,connection);
    }


}
