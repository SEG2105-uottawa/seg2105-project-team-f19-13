package com.example.clinicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceView extends AppCompatActivity {
    Button addServiceBtn, deleteServiceBtn;
    DatabaseReference databaseReference;
    List<Service> services;
    ListView adminListView, employeeListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_service_view);
        addServiceBtn = (Button) findViewById(R.id.addServiceBtn);
        deleteServiceBtn = (Button) findViewById(R.id.deleteServiceBtn);
        adminListView = (ListView) findViewById(R.id.adminListView);
        employeeListView = (ListView) findViewById(R.id.employeeListView);

        services = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("Service");

        addServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEmployeeService(); //Add method of addService
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    Service service = postSnapshot.getValue(Service.class);
                    services.add(service);
                }
                ServiceList servicesAdapter = new ServiceList(EmployeeServiceView.this, services);
                adminListView.setAdapter(servicesAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                services.clear();
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    Service service = postSnapshot.getValue(Service.class);
                    services.add(service);
                }
                ServiceList servicesAdapter = new ServiceList(EmployeeServiceView.this, services);
                employeeListView.setAdapter(servicesAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    //private boolean deleteEmployeeService(String id) {

    //}

    private void addEmployeeService() {

    }
}
