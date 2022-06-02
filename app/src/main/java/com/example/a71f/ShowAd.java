package com.example.a71f;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.a71f.data.DatabaseHelper;
import com.example.a71f.model.Items;
import com.example.a71f.util.Util;

import org.w3c.dom.Text;

public class ShowAd extends AppCompatActivity {

    DatabaseHelper db;
    Items item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_ad);

        TextView txtName = findViewById(R.id.txtName);
        TextView txtPhone = findViewById(R.id.txtPhone);
        TextView txtDescription = findViewById(R.id.txtDescription);
        TextView txtDate = findViewById(R.id.txtDate);
        TextView txtLocation = findViewById(R.id.txtLocation);

        db = new DatabaseHelper(this);

        Integer userid = getIntent().getIntExtra("ads",0);

        item = db.getItem(userid);

        txtName.setText(item.getName());
        txtPhone.setText(item.getPhone());
        txtDescription.setText(item.getDescription());
        txtDate.setText(item.getDate());
        txtLocation.setText(item.getLocation());


    }

    public void Remove(View view){
        Intent intent = new Intent(this, ListAd.class);
        startActivity(intent);

        // changed to remove the item from the list rather then the id
        db.removeAd(item);
    }
}