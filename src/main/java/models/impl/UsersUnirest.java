package models.impl;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import models.repo.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UsersUnirest {
    private final String USERS_URL = "http://users.local/users";

    public User getUser(String userId){
        HttpResponse<JsonNode> jsonResponse = null;
        try {
            jsonResponse = Unirest.get(USERS_URL)
                    .header("accept", "application/json").asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        JSONArray users = (JSONArray) jsonResponse.getBody().getObject().get("data");
        List<JSONObject> userList = new ArrayList<>();
        for (int i=0; i<users.length(); i++) {
            userList.add((JSONObject) users.get(i));
        }
        JSONObject user = userList.stream().filter(x -> x.get("id").equals(userId)).findAny().orElse(null);
        return User.fromJson(user != null ? user.toString() : null);
    }
}
