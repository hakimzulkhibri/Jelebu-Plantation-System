package com.example.jelebuplantationsystem;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class UsersList extends ArrayAdapter<UsersData> {

    private Activity context;
    private List<UsersData> userList;

    public UsersList(Activity context, List<UsersData> userList) {
        super(context, R.layout.list_layout, userList);
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewEmail = (TextView) listViewItem.findViewById(R.id.TextViewEmail);
        TextView textViewMobile = (TextView) listViewItem.findViewById(R.id.textViewMobile);

        UsersData users = userList.get(position);
        textViewName.setText(users.getUsername());
        textViewEmail.setText(users.getEmail());
        textViewMobile.setText(users.getMobile());



        return listViewItem;
    }
}
