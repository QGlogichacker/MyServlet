package bean;

public class User {
    public int id;
    String user;
    String password;
    int [] Chart = null;

    public User(int id, String usr, String pas) {
        this.id = id;
        this.user = usr;
        this.password = pas;
    }

    public User(String user, String password) {
        this.user = user;
        this.password = password;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("usr='").append(user).append('\'');
        sb.append(", pas='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

}
