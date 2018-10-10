package com.example.tasnimahmed.vehiclemanager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class Main5Activity extends AppCompatActivity {
    MyHelper mMyHelper;
    SQLiteDatabase mSQLiteDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        Intent intent = getIntent();
        String stdid = intent.getStringExtra("date_disi");
        mMyHelper = new MyHelper(getApplicationContext(), "trip", null,1);
        mSQLiteDB = mMyHelper.getReadableDatabase();

        String query1="Select * from trip where date="+"'"+stdid+"';";
        Cursor c = mSQLiteDB.rawQuery(query1,null);
        String odo,cost;
        String longi,lati;
        String total = "";
        TextView title3 = findViewById(R.id.title3);
        TextView location3 = findViewById(R.id.location3);
        TextView location4 = findViewById(R.id.location4);
        TextView location5 = findViewById(R.id.location5);
        TextView location6 = findViewById(R.id.location6);
        if(c.moveToFirst())
        {
            odo = String.valueOf(c.getString(c.getColumnIndex("odometer")));
            cost = String.valueOf(c.getString(c.getColumnIndex("cost")));
            total = String.valueOf(c.getString(c.getColumnIndex("total_cost")));
            lati = String.valueOf(c.getString(c.getColumnIndex("latitude")));
            longi = String.valueOf(c.getString(c.getColumnIndex("longitude")));

            String loca = longi+" , "+lati;
            title3.setText(stdid);
            location3.setText("Location : "+loca);
            location4.setText("Odometer : "+odo);
            location5.setText("Cost/L (BDT) : "+cost);
            location6.setText("Total Cost : "+total);


        }else{
            Toast.makeText(getApplicationContext(), " NO RECORDS FOUND", Toast.LENGTH_LONG).show();
        }

    }
}
