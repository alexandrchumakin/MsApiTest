package models.repo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

public class User {
    private String id;
    private String name;
    private String lastname;

    public User(){
        setId(new ObjectId().toString());
        setName(generateString());
        setLastname(generateString());
    }

    public User(HashMap userData){
        setId(userData.get("id").toString());
        setName(userData.get("name").toString());
        setLastname(userData.get("lastname").toString());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    private static String generateString(){
        String alphChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * alphChars.length());
            salt.append(alphChars.charAt(index));
        }
        return salt.toString();
    }

    public static User fromJson(String userDataResp){
        HashMap userData = new HashMap<String, String>();
        try {
            userData = new ObjectMapper().readValue(userDataResp, HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new User(userData);
    }
}
