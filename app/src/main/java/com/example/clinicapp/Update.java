package com.example.clinicapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Update extends AppCompatActivity {
    EditText servName, servRole;
    Button btnUpdate, btnCancel;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        servName=(EditText) findViewById(R.id.servName);
        servRole=(EditText) findViewById(R.id.servRole);
        btnUpdate=(Button) findViewById(R.id.btnUpdate);
        btnCancel= (Button) findViewById(R.id.btnCancel);
    }
    private void updateService(String serviceName, String serviceRole){
        databaseReference = FirebaseDatabase.getInstance().getReference("Service").child(serviceName);
        Service service = new Service ();
    }



}
