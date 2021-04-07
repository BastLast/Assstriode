package com.example.assstriode;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Calendar;

import static com.example.assstriode.R.string.sucess_toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Instantiate the RequestQueue.​
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String date = Calendar.getInstance().get(Calendar.YEAR) + "-" + (int) ((int) (Calendar.getInstance().get(Calendar.MONTH)) + 1) + "-" + Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        String url = "https://api.nasa.gov/neo/rest/v1/feed?start_date=" + date +
                "&end_date=" + date + "&api_key=47NucpwWv137CZPlAJZxykgMTMR9KF4bj5TzCYSG";

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response);

                Toast.makeText(HomeActivity.this, sucess_toast, Toast.LENGTH_SHORT).show();

// JSON response​
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(HomeActivity.this, "Okay, Houston, we've had a problem here.", Toast.LENGTH_SHORT).show();
            }
        });
        // Add the request to the RequestQueue.
        queue.add(jsObjRequest);

    }

}