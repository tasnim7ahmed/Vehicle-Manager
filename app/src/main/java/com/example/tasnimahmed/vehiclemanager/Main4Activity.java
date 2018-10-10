package com.example.tasnimahmed.vehiclemanager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main4Activity extends AppCompatActivity {
    MyHelper mMyHelper;
    SQLiteDatabase mSQLiteDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);


        mMyHelper = new MyHelper(getApplicationContext(), " trip ", null,1);
        mSQLiteDB = mMyHelper.getReadableDatabase();
        final ArrayList<String> date_list = new ArrayList<String>();

        String query1="Select date from trip;";
        Cursor c = mSQLiteDB.rawQuery(query1,null);
        String date_add="";

        if (c.moveToFirst()) {
            do {
                date_add = String.valueOf(c.getString(c.getColumnIndex("date")));
                date_list.add(date_add);
            }while (c.moveToNext());


        } else {
            Toast.makeText(getApplicationContext(), "Add to ListView failed !!!", Toast.LENGTH_LONG).show();
        }


        ArrayAdapter<String> dateAdapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,date_list);

        ListView listView = findViewById(R.id.date_list_view);
        listView.setAdapter(dateAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String gg = date_list.get(position);
                Toast.makeText(getApplicationContext(),gg,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),Main5Activity.class);
                intent.putExtra("date_disi",gg);
                startActivity(intent);

            }
        });
    }
}
