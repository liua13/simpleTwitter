package com.codepath.apps.restclienttemplate.models;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

@Parcel
public class User {
    public String username;
    public String name;
    public String profilePicURL;

    public User(){}

    public static User fromJson(JSONObject jsonObject) throws JSONException {
        User user = new User();
        user.username = jsonObject.getString("screen_name");
        user.name = jsonObject.getString("name");
        user.profilePicURL = jsonObject.getString("profile_image_url_https");
        return user;
    }
}
