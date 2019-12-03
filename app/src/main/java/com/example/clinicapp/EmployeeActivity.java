package com.example.clinicapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EmployeeActivity extends AppCompatActivity{
    Button profileView, serviceManage, calenderManage, logoutButton;
    TextView employeeWelcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employeeactivity);
        profileView = findViewById(R.id.profileView);
        serviceManage = findViewById(R.id.serviceManage);
        calenderManage = findViewById(R.id.calendarManage);
        logoutButton = findViewById(R.id.logoutButton);

        final String uid;
        FirebaseUser user;


        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User").child("Employee").child(uid);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final String fName = dataSnapshot.child("fName").getValue(String.class);
                final String role = dataSnapshot.child("role").getValue(String.class);
                employeeWelcome = (TextView) findViewById(R.id.employeeWelcome);
                employeeWelcome.setText("Welcome " + fName + "! You are logged-in as " + role);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        profileView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inToProfile = new Intent(EmployeeActivity.this, ClinicRegistry.class);
                startActivity(inToProfile);
            }
        });
        serviceManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inToService = new Intent(EmployeeActivity.this, EmployeeServiceView.class);
                startActivity(inToService);
            }
        });
        calenderManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inToCalender = new Intent(EmployeeActivity.this, Calender.class);
                startActivity(inToCalender);
            }
        });
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intoMain = new Intent(EmployeeActivity.this, MainActivity.class);
                startActivity(intoMain);
            }
        });
    }
}
