package com.example.jelebuplantationsystem;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    private MaterialEditText userName,emailAddress,password,mobile;
    private RadioGroup radioGroup;
    private Button registerBtn;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Register");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();

        userName = findViewById(R.id.username);
        emailAddress = findViewById(R.id.email);
        password = findViewById(R.id.password);
        mobile = findViewById(R.id.mobile);
        radioGroup = findViewById(R.id.radioButton);
        registerBtn = findViewById(R.id.register);
        progressBar = findViewById(R.id.progressBar);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String user_name = userName.getText().toString();
                final String email = emailAddress.getText().toString();
                final String txt_password = password.getText().toString();
                final String txt_mobile = mobile.getText().toString();
                int checkedId = radioGroup.getCheckedRadioButtonId();
                RadioButton selected_gender = radioGroup.findViewById(checkedId);
                if (selected_gender == null) {
                    Toast.makeText(RegisterActivity.this, "Select gender please", Toast.LENGTH_SHORT).show();
                } else {
                    final String gender = selected_gender.getText().toString();
                    if (TextUtils.isEmpty(user_name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(txt_password) || TextUtils.isEmpty(txt_mobile)) {
                        Toast.makeText(RegisterActivity.this, "All fields are required to fill", Toast.LENGTH_SHORT).show();
                    } else {
                        register(user_name, email, txt_password, txt_mobile, gender);
                    }
                }
            }
        });
    }

            private void register(final String user_name, final String email, String txt_password, final String txt_mobile,  final String gender) {
                progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.createUserWithEmailAndPassword(email, txt_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser rUser = firebaseAuth.getCurrentUser();
                            assert rUser != null;
                            String userId = rUser.getUid();
                            databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(userId);
                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("userId", userId);
                            hashMap.put("username", user_name);
                            hashMap.put("email", email);
                            hashMap.put("gender", gender);
                            hashMap.put("mobile", txt_mobile);
                            hashMap.put("imageUrl", "default");
                            databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()){
                                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                    }else{
                                        Toast.makeText(RegisterActivity.this, (Objects.requireNonNull(task.getException())).getMessage(),Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        } else {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(RegisterActivity.this, (Objects.requireNonNull(task.getException())).getMessage(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }


    }
