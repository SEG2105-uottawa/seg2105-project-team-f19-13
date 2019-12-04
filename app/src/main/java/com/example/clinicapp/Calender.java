package com.example.clinicapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Pattern;

public class Calender extends AppCompatActivity{
    TextView manageCalender, mondayTitle, mondayHour, mondayEditTime, tuesdayTitle, tuesdayHour, tuesdayEditTime, wednesdayTitle, wednesdayHour, wednesdayEditTime, thursdayTitle, thursdayHour, thursdayEditTime, fridayTitle, fridayHour, fridayEditTime, textNote;
    Button mondayEditBtn, tuesdayEditBtn, wednesdayEditBtn, thursdayEditBtn, fridayEditBtn;
    DatabaseReference databaseReference;
    String currentClinicID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calender);
        manageCalender = (TextView) findViewById(R.id.manageCalendar);
        mondayTitle = (TextView) findViewById(R.id.mondayTitle);
        tuesdayTitle = (TextView) findViewById(R.id.tuesdayTitle);
        wednesdayTitle = (TextView) findViewById(R.id.wednesdayTitle);
        thursdayTitle = (TextView) findViewById(R.id.thursdayTitle);
        fridayTitle = (TextView) findViewById(R.id.fridayTitle);
        textNote = (TextView) findViewById(R.id.textNote);
        mondayEditBtn = (Button) findViewById(R.id.mondayEditBtn);
        tuesdayEditBtn = (Button) findViewById(R.id.tuesdayEditBtn);
        wednesdayEditBtn = (Button) findViewById(R.id.wednesdayEditBtn);
        thursdayEditBtn = (Button) findViewById(R.id.thursdayEditBtn);
        fridayEditBtn = (Button) findViewById(R.id.fridayEditBtn);
        mondayHour = (TextView) findViewById(R.id.mondayHour);
        mondayEditTime = (TextView) findViewById(R.id.mondayEditTime);
        tuesdayHour = (TextView) findViewById(R.id.tuesdayHour);
        tuesdayEditTime = (TextView) findViewById(R.id.tuesdayEditTime);
        wednesdayHour = (TextView) findViewById(R.id.wednesdayHour);
        wednesdayEditTime = (TextView) findViewById(R.id.wednesdayEditTime);
        thursdayHour = (TextView) findViewById(R.id.thursdayHour);
        thursdayEditTime = (TextView) findViewById(R.id.thursdayEditTime);
        fridayHour = (TextView) findViewById(R.id.fridayHour);
        fridayEditTime = (TextView) findViewById(R.id.fridayEditTime);
        final String uid;
        FirebaseUser user;
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("User").child("Employee").child(uid);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final String clinicid = dataSnapshot.child("clinicID").getValue(String.class);
                currentClinicID = clinicid;
                databaseReference = FirebaseDatabase.getInstance().getReference("Clinic").child(currentClinicID).child("Hours");
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        final String mondayHourTime = dataSnapshot.child("Monday").child("hour").getValue(String.class);
                        final String tuesdayHourTime = dataSnapshot.child("Tuesday").child("hour").getValue(String.class);
                        final String wednesdayHourTime = dataSnapshot.child("Wednesday").child("hour").getValue(String.class);
                        final String thursdayHourTime = dataSnapshot.child("Thursday").child("hour").getValue(String.class);
                        final String fridayHourTime = dataSnapshot.child("Friday").child("hour").getValue(String.class);
                        if(!((mondayHourTime == "") && (tuesdayHourTime == "") && (wednesdayHourTime == "") && (thursdayHourTime == "") && (fridayHourTime == ""))){
                            mondayHour.setText(mondayHourTime);
                            tuesdayHour.setText(tuesdayHourTime);
                            wednesdayHour.setText(wednesdayHourTime);
                            thursdayHour.setText(thursdayHourTime);
                            fridayHour.setText(fridayHourTime);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void mondayEdit(View v){
        if (!mondayEditTime.getText().toString().trim().equals("") && Pattern.matches("^[0-2][0-9]:[0-9][0-9]-[0-2][0-9]:[0-9][0-9]$", mondayEditTime.getText().toString().trim())){
            mondayHour.setText(mondayEditTime.getText());
            mondayEditTime.setText("");
            String time = mondayHour.getText().toString();
            Hour hour = new Hour();
            hour.setHour(time);
            System.out.println(hour);
            databaseReference.child("Monday").child("hour").setValue(time);

        }
        else{
            Toast.makeText(Calender.this, "Invalid Time", Toast.LENGTH_SHORT).show();
        }

    }

    public void tuesdayEdit(View v){
        if (!tuesdayEditTime.getText().toString().trim().equals("") && Pattern.matches("^[0-2][0-9]:[0-9][0-9]-[0-2][0-9]:[0-9][0-9]$", tuesdayEditTime.getText().toString().trim())){
            tuesdayHour.setText(tuesdayEditTime.getText());
            tuesdayEditTime.setText("");
            String time = tuesdayHour.getText().toString();
            Hour hour = new Hour();
            hour.setHour(time);
            databaseReference.child("Tuesday").child("hour").setValue(time);
        }
        else{
            Toast.makeText(Calender.this, "Invalid Time", Toast.LENGTH_SHORT).show();
        }
    }

    public void wednesdayEdit(View v){
        if (!wednesdayEditTime.getText().toString().trim().equals("") && Pattern.matches("^[0-2][0-9]:[0-9][0-9]-[0-2][0-9]:[0-9][0-9]$", wednesdayEditTime.getText().toString().trim())){
            wednesdayHour.setText(wednesdayEditTime.getText());
            wednesdayEditTime.setText("");
            String time = wednesdayHour.getText().toString();
            Hour hour = new Hour();
            hour.setHour(time);
            databaseReference.child("Wednesday").child("hour").setValue(hour);
        }
        else{
            Toast.makeText(Calender.this, "Invalid Time", Toast.LENGTH_SHORT).show();
        }
    }

    public void thursdayEdit(View v){
        if (!thursdayEditTime.getText().toString().trim().equals("") && Pattern.matches("^[0-2][0-9]:[0-9][0-9]-[0-2][0-9]:[0-9][0-9]$", thursdayEditTime.getText().toString().trim())){
            thursdayHour.setText(thursdayEditTime.getText());
            thursdayEditTime.setText("");
            String time = thursdayHour.getText().toString();
            Hour hour = new Hour();
            hour.setHour(time);
            databaseReference.child("Thursday").child("hour").setValue(time);
        }
        else{
            Toast.makeText(Calender.this, "Invalid Time", Toast.LENGTH_SHORT).show();
        }
    }

    public void fridayEdit(View v){
        if (!fridayEditTime.getText().toString().trim().equals("") && Pattern.matches("^[0-2][0-9]:[0-9][0-9]-[0-2][0-9]:[0-9][0-9]$", fridayEditTime.getText().toString().trim())){
            fridayHour.setText(fridayEditTime.getText());
            fridayEditTime.setText("");
            String time = fridayHour.getText().toString();
            Hour hour = new Hour();
            hour.setHour(time);
            databaseReference.child("Friday").child("hour").setValue(time);
        }
        else{
            Toast.makeText(Calender.this, "Invalid Time", Toast.LENGTH_SHORT).show();
        }
    }
}
