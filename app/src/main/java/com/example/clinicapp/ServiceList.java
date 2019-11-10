package com.example.clinicapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ServiceList extends ArrayAdapter<Service> {
    private Activity context;
    List<Service> services;

    public ServiceList(Activity context, List<Service> services) {
        super(context, R.layout.service_item_list, services);
        this.context = context;
        this.services = services;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.service_item_list, null, true);

        TextView textViewServiceName = (TextView) listViewItem.findViewById(R.id.textViewServiceName);
        TextView textViewServiceRole = (TextView) listViewItem.findViewById(R.id.textViewServiceRole);

        Service service = services.get(position);
        textViewServiceName.setText(service.getServiceName());
        textViewServiceRole.setText(service.getServiceRole());
        return listViewItem;
    }
}
