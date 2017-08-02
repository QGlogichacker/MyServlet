package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DbUtil {


    //url,保存数据库的地址
    private String dbUrl = "jdbc:mysql://192.168.1.110:3306/qglibtest?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    //保存数据库名
    private String dbUserName = "root";
    //保存数据库的密码
    private String dbPassword = "password";
    //保存驱动名
    private String jdbcName = "com.mysql.jdbc.Driver";

    //加载驱动，过去数据库连接
    public Connection getCon() throws Exception {
        Class.forName(jdbcName);
        return DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
    }

    //关闭数据库连接
    public void close(PreparedStatement ps, Connection connection) throws Exception {
        if (ps != null)
            ps.close();
        if (connection != null)
            connection.close();
    }
}