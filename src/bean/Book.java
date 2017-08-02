package bean;

import com.google.gson.Gson;

import java.util.HashMap;

public class Book {
    private String name;
    private String author;
    private String rating;
    private String picture;
    private String douban;
    private String content;
    private String aboutwriter;
    private String type;
    private String ISBN;

    public Book(String name, String author, String rating, String picture) {
        this.name = name;
        this.author = author;
        this.rating = rating;
        this.picture = picture;
    }

    public static void main(String[] args) {
        Book b = new Book("a", "b", "c", "d");
        System.out.println(new Gson().toJson(b));
    }

    private HashMap<String, String> sim() {
        HashMap<String, String> hs = new HashMap<>();
        hs.put("name", name);
        hs.put("author", author);
        hs.put("rating", rating);
        hs.put("picture", picture);
        return hs;
    }
}
