import com.google.gson.Gson;

import java.util.HashMap;

public class test {

    public static void main(String[] args) {
        String s = "\"2333\"";
        HashMap hs = new HashMap();
        hs.put("123",s);
        System.out.println(new Gson().toJson(hs));
    }
}
