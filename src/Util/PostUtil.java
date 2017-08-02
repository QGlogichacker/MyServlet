package Util;

import bean.Book;
import bean.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class PostUtil {
    public static final HashMap<String, String> getMap(HttpServletRequest hs) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(hs.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String s = null;
            while ((s = br.readLine()) != null)
                sb.append(s);
            return new Gson().fromJson(sb.toString(), new TypeToken<HashMap<String, String>>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static final User getUser(HttpServletRequest hs) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(hs.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String s = null;
            while ((s = br.readLine()) != null)
                sb.append(s);
            return new Gson().fromJson(String.valueOf(sb), User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
