package com.example.clinicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class WelcomeActivity extends AppCompatActivity {
    Button logoutBtn, addressSearch, serviceSearch;
    TextView welcomeText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        final String uid;
        FirebaseUser user;



        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User").child("Patient").child(uid);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final String fName = dataSnapshot.child("fName").getValue(String.class);
                final String role= dataSnapshot.child("role").getValue(String.class);

                welcomeText = (TextView) findViewById(R.id.welcomeText);
                welcomeText.setText("Welcome " + fName + "! You are logged-in as " + role);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        addressSearch = (Button)findViewById(R.id.addressSearch);

        logoutBtn = (Button)findViewById(R.id.logoutBtn);

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intoMain = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intoMain);
            }
        });



        addressSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WelcomeActivity.this, ServiceSearch.class);
                startActivity(i);

            }
        });
    }
}
