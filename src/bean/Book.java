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

    public Book(String name, String author, String rating, String picture,String isbn,String douban,String content,String type) {
        this.name = name;
        this.author = author;
        this.rating = rating;
        this.picture = picture;
        this.ISBN = isbn;
        this.douban = douban;
        this.content = content;
        this.type = type;
    }

    private HashMap<String, String> sim() {
        HashMap<String, String> hs = new HashMap<>();
        hs.put("name", name);
        hs.put("author", author);
        hs.put("rating", rating);
        hs.put("picture", picture);
        return hs;
    }

    public Book(String name, String author, String rating, String picture, String douban, String content, String aboutwriter, String type, String ISBN) {
        this.name = name;
        this.author = author;
        this.rating = rating;
        this.picture = picture;
        this.douban = douban;
        this.content = content;
        this.aboutwriter = aboutwriter;
        this.type = type;
        this.ISBN = ISBN;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Book{");
        sb.append("content='").append(content).append('\'');
        sb.append(", ISBN='").append(ISBN).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public Book contentCut(int length){
        if(this.content.length()>length)
            this.content = content.substring(0,length)+"...";
        if(this.name.length()>7)
            this.name = name.substring(0,7)+"...";
        return this;
    }

    public static void main(String[] args) {

    }
}
