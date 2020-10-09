package com.example.jelebuplantationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.w3c.dom.Text;

import java.util.HashMap;

public class DeleteUserActivity extends AppCompatActivity {
    private Button btnDelete;
    private DatabaseReference mFireDatabase;
    private TextView dltname, dltemail, dltmobile, dltgender;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_remove_user);
        btnDelete = findViewById(R.id.deletedata);
        dltemail = findViewById(R.id.dltEmail);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser rUser = firebaseAuth.getCurrentUser();

        final String userId = rUser.getUid();


        mFireDatabase = FirebaseDatabase.getInstance().getReference("Users");

        final String email = dltemail.getText().toString();
        mFireDatabase.child(userId).child("email").setValue(email);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFireDatabase.child(userId).removeValue();
                Toast.makeText(DeleteUserActivity.this, "Successfully deleted user", Toast.LENGTH_SHORT).show();
                // clear information
                email.equals("");

                startActivity(new Intent(DeleteUserActivity.this, AdminHome.class));

            }
        });
    }
}
