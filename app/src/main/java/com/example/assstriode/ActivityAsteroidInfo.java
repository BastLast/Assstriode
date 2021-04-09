package com.example.assstriode;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.example.assstriode.model.Asteroid;

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


    }

    /**
     * generate the url for api calls
     *
     * @return
     */
    private String generateUrl() {
        return "https://api.nasa.gov/neo/rest/v1/neo/3726710?api_key=" + getResources().getString(R.string.apikey);
    }
}
