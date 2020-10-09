package com.example.jelebuplantationsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.AdditionalUserInfo;

public class AdminHome extends AppCompatActivity {

    private Button displayuser;
    private CardView addUsers, deleteUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        displayuser = findViewById(R.id.displayUser);
        addUsers = (CardView) findViewById(R.id.adduser);
        deleteUsers = (CardView) findViewById(R.id.removeuser);

        addUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHome.this, AddUserActivity.class));
            }
        });
        deleteUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHome.this, DeleteUserActivity.class));
            }
        });

        displayuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHome.this, DisplayUserActivity.class));
            }
        });
    }
}