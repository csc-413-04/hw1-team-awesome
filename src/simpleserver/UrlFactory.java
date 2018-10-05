package simpleserver;

import com.google.gson.*;
import java.util.ArrayList;

public class UrlFactory {

Users[] user_All = Parser.getUserObject();
    Posts[] posts_All = Parser.getPostObjects();
    ArrayList<Users> users_Array = new ArrayList<>();
    ArrayList<Posts> posts_Array = new ArrayList<>();
    String response = "<code><p style=\"position: relative; left:50px\">\"status\":\"SUCCESS\"</p><p style=\"position: relative; left:50px\">\"entries\":";
    String fail = "<code><p style=\"position: relative; left:50px\">\"status\":\"ERROR\"</p></code>";
    Gson gson = new GsonBuilder().setPrettyPrinting().create();



}
