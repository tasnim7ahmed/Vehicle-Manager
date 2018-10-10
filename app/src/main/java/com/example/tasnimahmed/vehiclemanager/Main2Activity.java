package com.example.tasnimahmed.vehiclemanager;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.Locale;

public class Main2Activity extends AppCompatActivity {

    private LocationManager locationManager;
    private MyHelper myHelper;
    private SQLiteDatabase myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final String date_curr = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault()).format(new Date());
        TextView date = findViewById(R.id.date);
        date.setText("Date : " + date_curr);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
        final double longi = location.getLongitude();
        final double lati = location.getLatitude();

        String loca = String.valueOf(longi)+" , "+String.valueOf(lati);
        TextView loc = findViewById(R.id.location);
        loc.setText("Location : "+loca);

        myHelper = new MyHelper(getApplicationContext()," trip ",null,1);
        myDB = myHelper.getWritableDatabase();
        Button save = findViewById(R.id.save);
        EditText odo = findViewById(R.id.odo);
        EditText cost_edit = findViewById(R.id.cost_edit);
        EditText total_cost_edit = findViewById(R.id.total_edit);
        String gg = odo.getText().toString();
        final int _odo;
        if(!gg.equals(""))
        {
            _odo = Integer.parseInt(gg);
        }
        else {
            _odo = 0;
        }
        final int _cost;
        if (!cost_edit.getText().toString().equals(""))
        {
            _cost = Integer.parseInt(cost_edit.getText().toString());
        }
        else {
            _cost = 0;
        }

        final int total_cost = _odo*_cost;
        total_cost_edit.setText(String.valueOf(total_cost));
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                myDB.execSQL("Insert into trip values " +
                        "('" + date_curr +"',"+_odo+","+_cost+","+total_cost+",'"+String.valueOf(longi)+"','"+String.valueOf(lati)+"');");
                Toast.makeText(getApplicationContext(),"Information Added Successfully",Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

        Button cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }


}
