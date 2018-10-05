package simpleserver;
import java.util.*;

public class Users{

    private final static Map<Integer, Users> user_all = new HashMap<>();
    private String username;
    private int userid;

    public Users(String user_name, int user_id) {
        username = user_name;
        this.userid = user_id;
        user_all.put(user_id, this);
    }
    
    public String User_get_name() {
        return username;
    }

}
