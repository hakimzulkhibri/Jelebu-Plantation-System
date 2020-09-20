package com.example.jelebuplantationsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class usermanagement extends AppCompatActivity implements View.OnClickListener{

    private CardView ShowUser, AddUser, RemoveUser, AdminLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usermanagement);

        ShowUser = (CardView) findViewById(R.id.showuser);
        AddUser = (CardView) findViewById(R.id.adduser);
        RemoveUser = (CardView) findViewById(R.id.removeuser);
        AdminLogOut = (CardView) findViewById(R.id.Adminlogout);

        ShowUser.setOnClickListener(this);
        AddUser.setOnClickListener(this);
        RemoveUser.setOnClickListener(this);
        AdminLogOut.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.showuser:
                i = new Intent(this, ShowUser.class);
                startActivity(i);
                break;
            case R.id.adduser:
                i = new Intent(this, AddUser.class);
                startActivity(i);
                break;
            case R.id.removeuser:
                i = new Intent(this, RemoveUser.class);
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
