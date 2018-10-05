package simpleserver;

import java.util.*;

public class Posts {

 private final static Map<Integer, Posts> posts_all = new HashMap<>();
    private int postid;
    private int userid;
    private String data;

    public Posts(int post_id, int user_id, String data) {
        this.postid = post_id;
        this.userid = user_id;
        this.data = data;
        posts_all.put(postid, this);
    }
 public String getPosts_data() {
        return data;
    }
  public int getPosts_id() {
        return this.postid;
    }
}
