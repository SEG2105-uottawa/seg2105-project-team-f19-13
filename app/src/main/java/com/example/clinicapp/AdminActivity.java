package com.example.clinicapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity {
    EditText editTextServiceName;
    EditText editTextServiceRole;
    Button addButton;
    ListView listViewServices;
    DatabaseReference databaseServices;
    List<Service> services;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        editTextServiceName = (EditText) findViewById(R.id.editTextServiceName);
        editTextServiceRole = (EditText) findViewById(R.id.editTextServiceRole);
        listViewServices = (ListView) findViewById(R.id.listViewServices);
        addButton = (Button) findViewById(R.id.addButton);
        databaseServices = FirebaseDatabase.getInstance().getReference("Service");

        //adding an onclicklistener to button
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addService(); //Add method of addService
            }
        });

        services = new ArrayList<>();
        //
        /*listViewServices.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Service service = services.get(i);
                showUpdateDeleteDialog(service.getID(), service.getProductName());
                return true;
            }
        });*/
    }
}
