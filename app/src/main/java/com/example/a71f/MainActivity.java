package com.example.a71f;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnCreateAd;
    Button btnViewAdvertisements;
    Button btnShowMaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCreateAd = findViewById(R.id.btnCreateAd);
        btnViewAdvertisements = findViewById(R.id.btnViewAdvertisements);
        btnShowMaps = findViewById(R.id.btnShowMaps);

        btnCreateAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateAd.class);
                startActivity(intent);
            }
        });

        btnViewAdvertisements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListAd.class);
                startActivity(intent);
            }
        });

        btnShowMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
    }
}