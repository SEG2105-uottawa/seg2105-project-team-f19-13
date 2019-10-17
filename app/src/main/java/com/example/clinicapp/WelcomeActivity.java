package com.example.clinicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class WelcomeActivity extends AppCompatActivity {
    Button logoutBtn;
    EditText firstName;
    TextView welcomeText;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        firstName = findViewById(R.id.firstName);
        String fName = firstName.getText().toString();
        //String role = mySpinner.getSelectedItem().toString();

        welcomeText = (TextView) findViewById(R.id.welcomeText);
        welcomeText.setText("Welcome" + fName + "! You are logged-in as");

        logoutBtn = findViewById(R.id.loginBtn);

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intoMain = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intoMain);
            }
        });
    }
}
