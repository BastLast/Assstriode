package com.example.assstriode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.assstriode.adapter.AsteroidAdapter;
import com.example.assstriode.model.Asteroid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.assstriode.R.string.fail_toast;
import static com.example.assstriode.R.string.sucess_toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Instantiate the RequestQueue.​
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String today = generateDateString();
        String url = generateUrl(today);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                displayAsteroidList(response, today);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(HomeActivity.this, fail_toast, Toast.LENGTH_SHORT).show();
            }
        });
        // Add the request to the RequestQueue.
        queue.add(jsObjRequest);

    }

    /**
     * display the asteroids
     *
     * @param response
     * @param today
     */
    private void displayAsteroidList(JSONObject response, String today) {
        Toast.makeText(HomeActivity.this, sucess_toast, Toast.LENGTH_SHORT).show();
        List<Asteroid> asteroids = listAsteroids(response, today);
        ListView listview = findViewById(R.id.listview);
        AsteroidAdapter adapter = new AsteroidAdapter(getApplicationContext(), asteroids);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Asteroid asteroid = asteroids.get(position);
                Intent intent = new Intent(getApplicationContext(), ActivityAsteroidInfo.class);
                intent.putExtra("asteroid", asteroid);
                startActivity(intent);
            }
        });
    }

    /**
     * get the list of asteroids
     *
     * @param response
     * @param today
     * @return
     */
    private List<Asteroid> listAsteroids(JSONObject response, String today) {
        List<Asteroid> asteroids = new ArrayList<>();
        try {
            JSONArray asteroidsjson = response.getJSONObject("near_earth_objects").getJSONArray(today);
            for (int i = 0; i < asteroidsjson.length(); i++) {
                asteroidsjson.getJSONObject(i);
                asteroids.add(new Asteroid(asteroidsjson.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return asteroids;
    }

    /**
     * generate the url for api calls
     *
     * @param today
     * @return
     */
    private String generateUrl(String today) {
        return "https://api.nasa.gov/neo/rest/v1/feed?start_date=" + today +
                "&end_date=" + today + "&api_key=" + getResources().getString(R.string.apikey);
    }

    /**
     * get the date as a string
     *
     * @return
     */
    private String generateDateString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }

}