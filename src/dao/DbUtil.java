package dao;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DbUtil {


    //url,保存数据库的地址
    private String dbUrl = "jdbc:mysql://localhost:3306/qglibtest?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    //保存数据库名
    private String dbUserName = "root";
    //保存数据库的密码
    private String dbPassword = "014789*a";
    //保存驱动名
    private String jdbcName = "com.mysql.jdbc.Driver";

    public static ClearThread ct = null;

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

    public static String python(String pythonPath, String[] params) {
        File file = new File(pythonPath);
        if (!file.exists()){
            return "python脚本不存在！";
        }

        String[] command = Arrays.copyOf(new String[]{"python3", pythonPath}, params.length + 2);
        System.arraycopy(params, 0, command, 2, params.length);

        List<String> res = new ArrayList<>();
        try {
            Process process = Runtime.getRuntime().exec(command, null, null);

            ct = new ClearThread(process);
            ct.start();

            process.waitFor();
            Thread.sleep(1000);

            ct.setEnd(true);
            res = ct.getRes();
            System.out.println(res);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "success";
    }

    static class ClearThread extends Thread {
        Process process;
        boolean end;
        List<String> res;

        public ClearThread(Process p) {
            process = p;
            end = false;
            res = new ArrayList<>();
        }

        @Override
        public void run() {
            if (process == null) {
                return;
            }

            Scanner scanner = new Scanner(process.getInputStream());
            while (process != null && !end) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    res.add(line);
                }
            }
        }

        public void setEnd(boolean ed) {
            end = ed;
        }

        public List<String> getRes() {
            return res;
        }
    }

    public static void main(String[] args) {
        python("/home/logic_hacker/133.py",new String[0]);
    }
}