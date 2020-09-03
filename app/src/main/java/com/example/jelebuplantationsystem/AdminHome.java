package com.example.jelebuplantationsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminHome extends AppCompatActivity implements View.OnClickListener {

    private CardView AdminProfile,  Users, Settings, AdminLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        AdminProfile = (CardView) findViewById(R.id.adminprofile);
        Users = (CardView) findViewById(R.id.user);
        Settings = (CardView) findViewById(R.id.setting);
        AdminLogOut = (CardView) findViewById(R.id.Adminlogout);

        AdminProfile.setOnClickListener(this);
        Users.setOnClickListener(this);
        Settings.setOnClickListener(this);
        AdminLogOut.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.adminprofile:
                i = new Intent(this, AdminProfile.class);
                startActivity(i);
                break;
            case R.id.user:
                i = new Intent(this, usermanagement.class);
                startActivity(i);
                break;
            case R.id.setting:
                i = new Intent(this, adminsetting.class);
                startActivity(i);
                break;
            case R.id.Adminlogout:
                i = new Intent(this, Login.class);
                startActivity(i);
                break;
            default:
                break;
        }
    }
}