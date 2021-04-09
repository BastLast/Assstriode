package com.example.assstriode.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.ViewUtils;

import com.example.assstriode.R;
import com.example.assstriode.model.Asteroid;

import java.util.List;

public class AsteroidAdapter extends ArrayAdapter<Asteroid> {

    private final List<Asteroid> asteroids;
    private final LayoutInflater inflater;

    public AsteroidAdapter(@NonNull Context context, List<Asteroid> asteroids) {
        super(context, -1, asteroids);
        this.asteroids = asteroids;
        this.inflater = LayoutInflater.from(context);
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @Nullable ViewGroup parent) {
        Asteroid asteroid = this.asteroids.get(position);
        View asteroidLvLine = this.inflater.inflate(R.layout.asteroid_line, parent, false);
        TextView tvName = asteroidLvLine.findViewById(R.id.tvName);
        TextView tvDistance = asteroidLvLine.findViewById(R.id.tvDistance);
        TextView tvMagnitude = asteroidLvLine.findViewById(R.id.tvMagnitude);

        tvName.setText(asteroid.getName());
        tvDistance.setText(getContext().getString(R.string.distance) + " " + asteroid.getDistance());
        tvMagnitude.setText(getContext().getString(R.string.magnitude) + " " + asteroid.getMagnitude());
        return asteroidLvLine;
    }

}
