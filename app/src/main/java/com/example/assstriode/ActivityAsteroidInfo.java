package com.example.assstriode;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.assstriode.model.Asteroid;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.assstriode.R.string.fail_toast;
import static java.lang.Float.parseFloat;

public class ActivityAsteroidInfo extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asteroid_info);

        TextView tvName = findViewById(R.id.tvNameTitle);
        TextView tvDistance = findViewById(R.id.tvDistanceInfo);
        TextView tvMagnitude = findViewById(R.id.tvMagnitudeInfo);
        TextView tvPeriod = findViewById(R.id.tvPeriodeOrbitale);

        Asteroid asteroid = (Asteroid) getIntent().getSerializableExtra("asteroid");

        tvName.setText(asteroid.getName());
        tvDistance.setText(getString(R.string.distance) + " " + asteroid.getDistance());
        tvMagnitude.setText(getString(R.string.magnitude) + " " + asteroid.getMagnitude());

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        String url = generateUrl(asteroid.getId());
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response);
                try {
                    asteroid.setPeriodeOrbitale(parseFloat((String) response.getJSONObject("orbital_data").get("orbital_period")));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                tvPeriod.setText(getString(R.string.orbital) + " " + asteroid.getPeriodeOrbitale());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ActivityAsteroidInfo.this, fail_toast, Toast.LENGTH_SHORT).show();
            }
        });
        // Add the request to the RequestQueue.
        queue.add(jsObjRequest);

    }

    /**
     * generate the url for api calls
     *
     * @param id
     * @return
     */
    private String generateUrl(int id) {
        return "https://api.nasa.gov/neo/rest/v1/neo/"+ id +"?api_key=" + getResources().getString(R.string.apikey);
    }
}
