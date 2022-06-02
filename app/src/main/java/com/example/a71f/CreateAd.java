package com.example.a71f;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a71f.data.DatabaseHelper;
import com.example.a71f.model.Items;
import com.google.android.libraries.places.api.model.Place;

import java.util.ArrayList;

public class CreateAd extends AppCompatActivity {

    Button getCurrentLocation;
    Button createAdButton;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ad);

        TextView etxtName = findViewById(R.id.etxtName);
        TextView etxtPhone = findViewById(R.id.etxtPhone);
        TextView etxtDescription = findViewById(R.id.etxtDescription);
        TextView etxtDate = findViewById(R.id.etxtDate);
        TextView etxtLocation = findViewById(R.id.etxtLocation);

        db = new DatabaseHelper(this);


        getCurrentLocation = findViewById(R.id.btnGetCurrentLoc);
        getCurrentLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent placeIntent = new Intent(CreateAd.this, PlacesActivity.class);
                startActivity(placeIntent);
            }
        });

        createAdButton = findViewById(R.id.btnSubmit);
        createAdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etxtName.getText().toString();
                String phone = etxtPhone.getText().toString();
                String description = etxtDescription.getText().toString();
                String date = etxtDate.getText().toString();
                String location = etxtLocation.getText().toString();



                db.insertAds(new Items(name, phone, description, date, location));
                //db.addData(name, phone, description, date, location);
            }
        });

    }

    // test variation of the database
    //public void AddData(String name, String phone, String description, String date, String locaiton){
    //    boolean insertData = db.addData(name, phone, description, date, locaiton);
    //
    //    if (insertData == true){
    //        Toast.makeText(CreateAd.this, "Successfully entered information", Toast.LENGTH_SHORT).show();
    //    }
    //    else{
    //        Toast.makeText(CreateAd.this, "Something failed", Toast.LENGTH_SHORT).show();
    //    }
    //}
}