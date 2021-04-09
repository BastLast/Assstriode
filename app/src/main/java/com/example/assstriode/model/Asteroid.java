package com.example.assstriode.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Asteroid {

    private String name;


    @Override
    public String toString() {
        return name;
    }

    public Asteroid(JSONObject JsonAsteroid) {
        try {
            this.name = (String) JsonAsteroid.get("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
