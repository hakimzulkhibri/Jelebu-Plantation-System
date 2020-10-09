package com.example.jelebuplantationsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class usermanagement extends AppCompatActivity {
    private FirebaseFirestore FirebaseFirestore;
    private RecyclerView mFirestoreList;
    private FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usermanagement);

        FirebaseFirestore = FirebaseFirestore.getInstance();
        mFirestoreList = findViewById(R.id.firestore_list);

        //Query
        Query query = FirebaseFirestore.collection("users");

        //RecyclerOptions
        FirestoreRecyclerOptions<UsersModel> options = new FirestoreRecyclerOptions.Builder<UsersModel>()
                .setQuery(query, UsersModel.class)
                .build();

         adapter = new FirestoreRecyclerAdapter<UsersModel, UsersViewHolder>(options) {
            @NonNull
            @Override
            public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_single, parent, false );
                return new UsersViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull UsersViewHolder holder, int position, @NonNull UsersModel model) {
                holder.list_email.setText(model.getEmail());
                holder.list_name.setText(model.getName());
                holder.list_phone.setText(model.getPhone());

            }
        };
         mFirestoreList.setHasFixedSize(true);
         mFirestoreList.setLayoutManager(new LinearLayoutManager(this));
         mFirestoreList.setAdapter(adapter);
        //View Holder

    }

    private class UsersViewHolder extends RecyclerView.ViewHolder {

        private TextView list_email;
        private TextView list_name;
        private TextView list_phone;

        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);

            list_email = itemView.findViewById(R.id.list_email);
            list_name = itemView.findViewById(R.id.list_name);
            list_phone = itemView.findViewById(R.id.list_phone);
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
}