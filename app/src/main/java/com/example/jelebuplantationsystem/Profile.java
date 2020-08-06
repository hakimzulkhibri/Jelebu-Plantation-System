package com.example.jelebuplantationsystem;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthSettings;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.security.AccessController;

public class Profile<v, DocumentReference, activity, documentSnapshot, view> extends AppCompatActivity {
    private static final int GALLERY_INTENT_CODE = 1023;
    TextView fullName, email, phone, verifyMsg;
    FirebaseAuth firebaseAuth;
    Firebasefirestore firebasefirestore;
    String userId;
    Button resendCode;
    Button resetPassLocal, changedProfile;
    FirebaseUser user;
    ImageView profileImage;
    StorageReference storageReference


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phone = findViewById(R.id.profilePhone);
        fullName = findViewById(R.id.profileName);
        email = findViewById(R.id.profileEmail);
        resetPassLocal = findViewById(R.id.resetPassword);

        profileImage = findViewById(R.id.profileImage);
        changedProfile = findViewById(R.id.changedProfile);


        firebaseAuth = FirebaseAuth.getInstance();
        firebasefirestore = Firebasefirestore.getInstane();
        storageReference = FirebaseStorage.getInstance().getReference();

        StorageReference profileRef = storageReference.child("users/"+firebaseAuth.getCurrentUser().getUid()+"/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri> () {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(profileImage);
            }
        });


        resendCode = findViewById(R.id.resetPassword);

    }

    DocumentReference documentReference = firebasefirestore.collection("users").document(userId);
    documentReference.addSnapshotListener(this,(documentSnapshot, e) {
        if (documentSnapshot.exists()) {
            phone.setText(documentSnapshot.getString("phone"));
            fullName.setText(documentSnapshot.getString("fullName"));
            email.setText(documentSnapshot.getString("email"));

        }else {
            Log.d("tag", "onEvent: Document do not exist");
        }
    })

        changedProfile.setOnClickListener((v) {

        AccessController v;
        Intent i = new Intent(v.getContext(), EditProfile.class);
        i.putExtra("fullName", fullName.getText().toString());
        i.putExtra("email", email.getText().toString());
        i.putExtra("phone", phone.getText().toString());
        startActivity(i);
            Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(openGalleryIntent, 1000);

        });
}


    private void uploadImageToFirebase(Uri imageUri) {
            final StorageReference fileRef = storageReference.child("users/"+firebaseAuth.getCurrentUser().getUid()+"/profile.jpg");
            fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Picasso.get().load(uri).into(profileImage);
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Profile.this, "Failed.", Toast.LENGTH_SHORT).show();
                }
            });

    }


}
