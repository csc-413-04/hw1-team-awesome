package simpleserver;
import com.google.gson.*;
import java.io.*;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class parser {

    // Users Json String
    private static String JsonAllUsers = null;
    // Posts Json String
    private static String JsonAllPosts = null;

       // stores Users object
    private static Users[] user_All = null;
    // stores Posts object
    private static Posts[] posts_All = null;
    
    public static void CreateUsers() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        BufferedReader buffer;
        // try block runs if src/data.json exists
        try {
            buffer = new BufferedReader(new FileReader("src/data.json"));
            JsonParser parser = new JsonParser();
            JsonObject obj = parser.parse(buffer).getAsJsonObject();
            Users[] user = gson.fromJson(obj.get("users"), Users[].class);
            user_All = user;
            JsonAllUsers = gson.toJson(user);
            JsonElement pretty = parser.parse(JsonAllUsers);
            for (int i = 0; i < user_All.length; i++) {
                user_All[i] = new Users(user_All[i].User_get_name(), user_All[i].User_get_id());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void CreatePosts() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        BufferedReader buffer;
        try {
            buffer = new BufferedReader(new FileReader("src/data.json"));
            JsonParser parser = new JsonParser();
            JsonObject obj = parser.parse(buffer).getAsJsonObject();
            Posts[] post = gson.fromJson(obj.get("posts"), Posts[].class);
            posts_All = post;
            JsonAllPosts = gson.toJson(posts_All);
            // get all the Users Object inside posts_All
            for (int i = 0; i < posts_All.length; i++) {
                posts_All[i] = new Posts(posts_All[i].getPosts_id(), posts_All[i].getUser(), posts_All[i].getPosts_data());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

 public static String getUserJson() {
        return JsonAllUsers;
    }

    public static Users[] getUserObject() {
        return user_All;
    }

    public static Posts[] getPostObjects() {
        return posts_All;
    }


    public static boolean NumCheck(String num) {
        try {
            int num_check = Integer.parseInt(num);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static String process(String url) {
        String[] url_section = url.split(" ");
        String url_given = url_section[1];
        // default response

        UrlFactory factory = new UrlFactory();
        if (url_given.equals("/user")) {
            return factory.UsersAll(url_given);
        }
        // IF url starts w/ /user?userid=...
        else if (url_given.startsWith("/user?userid=")) {
            return factory.UserGiven(url_given);
        }
        // IF url starts w/ /posts?posts=(any id num )&maxlength=...
        else if (url_given.contains("maxlength=")) {
            return factory.PostsMaxLength(url_given);
        }
        // if url starts with 'posts?postsid='...
        else if (url_given.startsWith("/posts?postsid=")) {
            return factory.PostsGiven(url_given);

        }
        else if (url_given.equals("/")){
            return factory.Default();
        }
        else {
            return factory.Fail();
        }

    }


}
