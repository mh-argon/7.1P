package com.example.a71f.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.a71f.model.Items;
import com.example.a71f.util.Util;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // create a table
        String CREATE_ITEM_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "(" + Util.item_id + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + Util.item_name + " TEXT,"
                + Util.item_phone + " TEXT,"
                + Util.item_description + " TEXT,"
                + Util.item_date + " TEXT,"
                + Util.item_location + " TEXT)";

        sqLiteDatabase.execSQL(CREATE_ITEM_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // for dropping a table
        String DROP_USER_TABLE = "DROP TABLE IF EXISTS";
        sqLiteDatabase.execSQL(DROP_USER_TABLE, new String[]{Util.TABLE_NAME});

        onCreate(sqLiteDatabase);
    }

    // defined as long as the insertAds is returning a value as the adid
    public long insertAds(Items items)
    {
        // get writable because yuoui want to edit it
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.item_name, items.getName());
        contentValues.put(Util.item_phone, items.getPhone());
        contentValues.put(Util.item_description, items.getDescription());
        contentValues.put(Util.item_date, items.getDate());
        contentValues.put(Util.item_location, items.getLocation());

        long adId = db.insert(Util.TABLE_NAME, null, contentValues);
        // has to close the db
        db.close();
        return adId;
    }

    // different method of adding data to the list
    // public boolean addData(String name, String phone, String description, String date, String location){
    //    SQLiteDatabase db = this.getWritableDatabase();
    //    ContentValues contentValues = new ContentValues();
    //    contentValues.put(Util.item_name, name);
    //    contentValues.put(Util.item_phone, phone);
    //    contentValues.put(Util.item_description, description);
    //    contentValues.put(Util.item_date, date);
    //    contentValues.put(Util.item_location, location);

    //    long result = db.insert(Util.TABLE_NAME, null, contentValues);

    //    if (result == -1){
    //        return false;
    //    }
    //    else{
    //        return true;
    //    }
    //}


    public void removeAd(Items item){
        SQLiteDatabase db = this.getWritableDatabase();
        // commented out the contentValues section
        // added a method to get the id and delete based on the id

        //ContentValues contentValues = new ContentValues();
        //contentValues.remove(TABLE_NAME);
        Integer id = item.getId();
        db.delete(Util.TABLE_NAME, Util.item_id+"=?", new String[]{id.toString()});
        db.close();
    }

    public Cursor getListContents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("Select * From " + Util.TABLE_NAME, null);
        return data;
    }

    // added the fix here tp get the item based on userid
    public Items getItem(Integer userid) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("Select * From " + Util.TABLE_NAME +" WHERE item_id=?", new String[]{userid.toString()});
        Log.d("Itemscount", data.getCount()+"");
        data.moveToFirst();
        Items item = new Items(data.getString(1), data.getString(2), data.getString(3), data.getString(4), data.getString(5));
        item.setId(data.getInt(0));
        return item;
    }
}
