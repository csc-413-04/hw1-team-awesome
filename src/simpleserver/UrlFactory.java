package simpleserver;

import com.google.gson.*;
import java.util.ArrayList;

public class UrlFactory {

Users[] user_All = parser.getUserObject();
    Posts[] posts_All = parser.getPostObjects();
    ArrayList<Users> users_Array = new ArrayList<>();
    ArrayList<Posts> posts_Array = new ArrayList<>();
    
    String response = "<code><p style=\"position: relative; left:50px\">\"status\":\"SUCCESS\"</p><p style=\"position: relative; left:50px\">\"entries\":";
    String fail = "<code><p style=\"position: relative; left:50px\">\"status\":\"ERROR\"</p></code>";
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

     public String UsersAll(String url) {
        String totNum = String.valueOf(user_All.length);
        response = response.concat(totNum + "</p><p style=\"position: relative; left:50px\">\"data\":");
        response = response.concat("</p><p style=\"position: relative; left:100px\">" + parser.getUserJson() + "</p></code>");
        return response;
    }
    
    public String UserGiven(String url) {
        int equal = url.indexOf("=");
        // if url length is large enough to show the user ID,
        // the method takes the substring of url w/ just an ID
        if (url.length() >= equal + 1) {
            String user_String = url.substring(equal + 1, url.length());
            // if the string ID is a number, it will parse them into an int
            if (parser.NumCheck(user_String) == true) {
                int id = Integer.parseInt(user_String);
                for (int i = 0; i < user_All.length; i++) {
                    if (id == user_All[i].User_get_id()) {
                        users_Array.add(user_All[i]);
                    } else {

                    }
                }
                // convert total number(s) of searches into String
                String totNum = String.valueOf(users_Array.size());

                // frontend stuff
                response = response.concat(totNum + "</p><p style=\"position: relative; left:50px\">\"data\":");
                response = response.concat("</p><p style=\"position: relative; left:100px\">" + gson.toJson(users_Array) + "</p></code>");
                return response;
            }
            else {
                return fail;
            }
            } else {
                return fail;
            }
        }
    // return error if the url length is too short


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
            if (parser.NumCheck(maxlength) == true) {
                int sum = Integer.parseInt(maxlength);
                int id = Integer.parseInt(post_id);
                Posts dummy_post;
                String dummy_data;
                
                for (int i = 0; i < posts_All.length; i++) {
                    
                    // IF the post id matches AND maxlength <= Posts data length,
                    // dummy_data stores that Posts Object's data and limit to certain length and
                    // dummy_post stores new Posts Object with altered data
                    if (id == posts_All[i].getPosts_id() && sum <= posts_All[i].getPosts_data().length()) {
                        dummy_data = posts_All[i].getPosts_data();
                        // if maxlength is 0
                        if (sum < 1) {
                            dummy_data = "";
                        }
                        // if maxlength is 1
                        else if (sum == 1) {
                            dummy_data = dummy_data.substring(0, 1);
                        }
                        // if maxlength is any other number
                        else {
                            dummy_data = dummy_data.substring(0, sum);
                        }
                        dummy_post = new Posts(posts_All[i].getPosts_id(), posts_All[i].getUser(), dummy_data);
                        
                        // push that Posts objects inside posts_Array
                        posts_Array.add(dummy_post);
                    }
                }
                //parse total search entries into String
                String totNum = String.valueOf(posts_Array.size());
                // frontend stuff
                response = response.concat(totNum + "</p><p style=\"position: relative; left:50px\">\"data\":");
                // frontend stuff
                response = response.concat("</p><p style=\"position: relative; left:100px\">" + gson.toJson(posts_Array) + "</p></code>");
                // error if no search entries
                if (posts_Array.size() == 0) {
                    return fail;
                } else {
                    return response;
                }
            // return error if the maxlength String is not a number
            } else {
                return fail;
            }
        // return error if url length is too short
        } else {
            return fail;
        }
    }

    public String PostsGiven(String url) {
        int equal = url.indexOf("=");
        // IF url_given's length is > than index of "=",
        // get the url w/ only the post id
        if (url.length() >= equal + 1) {
            String dummy_data = url.substring(equal + 1, url.length());
            // parse dummy_data into an int if it is a number
            if (parser.NumCheck(dummy_data) == true) {
                int id = Integer.parseInt(dummy_data);
                for (int i = 0; i < posts_All.length; i++) {
                    // push given Posts Objects if id matches
                    if (id == posts_All[i].getPosts_id()) {
                        posts_Array.add(posts_All[i]);
                        break;
                    }
                }
                // parse total search entries to String
                String totNum = String.valueOf(posts_Array.size());
                // frontend stuff
                response = response.concat(totNum + "</p><p style=\"position: relative; left:50px\">\"data\":");
                // frontend stuff
                response = response.concat("</p><p style=\"position: relative; left:100px\">" + gson.toJson(posts_Array) + "</p></code>");
            // return error if dummy_data is not a number
            } else {
                return fail;
            }
        // return error if url length is too short
        } else {
            return fail;
        }
        // ERROR if no results
        if (posts_Array.size() == 0) {
            return fail;
        } else {
            return response;
        }


    }
    public String Default() {
        response = "<code><p style=\"position: relative; left:50px\">\"status\":\"SUCCESS\"</p></code>";
        return response;
    }

    public String Fail() {
        return fail;
    }
}