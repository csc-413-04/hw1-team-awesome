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

     public String UsersAll(String url) {
        String totNum = String.valueOf(user_All.length);
        response = response.concat(totNum + "</p><p style=\"position: relative; left:50px\">\"data\":");
        response = response.concat("</p><p style=\"position: relative; left:100px\">" + Parser.getUserJson() + "</p></code>");
        return response;
    }
    
    public String UserGiven(String url) {
        int equal = url.indexOf("=");
        // if url length is large enough to show the user ID,
        // the method takes the substring of url w/ just an ID
        if (url.length() >= equal + 1) {
            String user_String = url.substring(equal + 1, url.length());
            // if the string ID is a number, it will parse them into an int
            for (int i = 0; i < user_All.length; i++) {
                    if (id == user_All[i].User_get_id()) {
                        users_Array.add(user_All[i]);
                    }
                    else {

                    }
                }
            // convert total number(s) of searches into String
                String totNum = String.valueOf(users_Array.size());
            
                // frontend stuff
            response = response.concat(totNum + "</p><p style=\"position: relative; left:50px\">\"data\":");
                response = response.concat("</p><p style=\"position: relative; left:100px\">" + gson.toJson(users_Array) + "</p></code>");
                return response;
            } else {
                return fail;
            }
        }
    // return error if the url length is too short
        else {
            return fail;
        
        }
    }
    public String PostsMaxLength(String url) {
        
        int equal = url.indexOf("=");
        int and = url.indexOf("&");
        int limit = url.indexOf("h");
        
        // gets the url w/ just a post id
        String post_id = url.substring(equal + 1, (and));
        
        // if url length is big enough to show the maxlength number,
        // it will take substring of url w/ just the maxlength number
        if (url.length() > limit + 2) {
            String maxlength = url.substring(limit + 2, (url.length()));
            //if maxlength is a number,
            // it will parse it into an int
            if (Parser.NumCheck(maxlength) == true) {
                int sum = Integer.parseInt(maxlength);
                int id = Integer.parseInt(post_id);
            }
    }


}
