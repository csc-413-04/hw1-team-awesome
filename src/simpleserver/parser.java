package simpleserver;
import com.google.gson.*;
import java.io.*;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Parser {

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
}
