package com.example.assstriode.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

public class Asteroid implements Serializable {

    private String name;
    private float distance;
    private double magnitude;
    private double periodeOrbitale;
    private int id;

    public Asteroid(JSONObject JsonAsteroid) {
        try {
            this.name = (String) JsonAsteroid.get("name");
            this.distance = parseFloat((String) JsonAsteroid.getJSONArray("close_approach_data").getJSONObject(0).getJSONObject("miss_distance").get("lunar"));
            this.id = parseInt((String) JsonAsteroid.get("id"));
            this.magnitude = (double) JsonAsteroid.get("absolute_magnitude_h");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String getName() {
        return this.name;
    }

    public float getDistance() {
        return this.distance;
    }

    public double getMagnitude() {
        return this.magnitude;
    }

    public void setPeriodeOrbitale(double periodeOrbitale) {
        this.periodeOrbitale = periodeOrbitale;
    }

    public double getPeriodeOrbitale() {
        return this.periodeOrbitale;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
