package com.example.a71f;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.a71f.data.DatabaseHelper;

import java.util.ArrayList;

public class ListAd extends AppCompatActivity {
    // added ArrayList<Integer> itemids
    DatabaseHelper db;
    ArrayList<Integer> itemids = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_ad);

        ListView listView = findViewById(R.id.listView);
        db = new DatabaseHelper(this);
        ArrayList<String> list = new ArrayList<>();
        Cursor data = db.getListContents();

        if(data.getCount() == 0){
            Toast.makeText(ListAd.this, "There was no database", Toast.LENGTH_LONG).show();
        }
        else{
            while(data.moveToNext()){
                // the 1 represents the column number being referenced
                list.add(data.getString(1));
                itemids.add(data.getInt(0));

                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
                listView.setAdapter(listAdapter);
            }
        }

        // create the onlist listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent intent = new Intent(getApplicationContext(), ShowAd.class);
                intent.putExtra("ads", itemids.get(position));
                startActivityForResult(intent, 1);
            }
        });
    }
}