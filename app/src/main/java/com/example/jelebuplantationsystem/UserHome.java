package com.example.jelebuplantationsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UserHome extends AppCompatActivity implements View.OnClickListener {

    private CardView myprofile, datareport, temperature, soilhumidity, logout, timestamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        //Define Card
        myprofile = (CardView) findViewById(R.id.myprofile);
        datareport = (CardView) findViewById(R.id.datareport);
        temperature = (CardView) findViewById(R.id.temperature);
        soilhumidity = (CardView) findViewById(R.id.soilhumidity);
        logout = (CardView) findViewById(R.id.logout);
        timestamp = (CardView) findViewById(R.id.timestamp);
        //Add Click Listener to each card
        myprofile.setOnClickListener(this);
        datareport.setOnClickListener(this);
        temperature.setOnClickListener(this);
        soilhumidity.setOnClickListener(this);
        logout.setOnClickListener(this);
        timestamp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.myprofile: i = new Intent(this, MainActivity.class);startActivity(i); break;
            case R.id.datareport: i = new Intent(this, DataReport.class);startActivity(i); break;
            case R.id.temperature: i = new Intent(this, TemperatureCheck.class);startActivity(i); break;
            case R.id.soilhumidity: i = new Intent(this, SoilHumidity.class);startActivity(i); break;
            case R.id.timestamp: i = new Intent(this, Timestamp.class);startActivity(i); break;
            case R.id.logout: i = new Intent(this, Login.class);startActivity(i); break;
            default:break;




        }
    }
}