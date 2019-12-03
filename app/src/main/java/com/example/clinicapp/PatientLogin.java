package com.example.clinicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PatientLogin extends AppCompatActivity {
    EditText patientEmail, patientPassword;
    Button patientSignin;

    private FirebaseAuth mFireBaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_login);

        patientEmail = findViewById(R.id.patientEmail);
        patientPassword = findViewById(R.id.patientPassword);
        patientSignin = findViewById(R.id.patientSignin);

        mFireBaseAuth = FirebaseAuth.getInstance();



        patientSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String  email = patientEmail.getText().toString().trim();
                String password = patientPassword.getText().toString().trim();
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(PatientLogin.this, "Please enter your email", Toast.LENGTH_SHORT).show();

                }
                else if(TextUtils.isEmpty(password)){
                    Toast.makeText(PatientLogin.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                }
                else if(email.equals("admin@admin.com") && password.equals("5T5ptQ"))
                {
                    Intent x = new Intent(getApplicationContext(), AdminActivity.class);
                    startActivity(x);

                }
                else if(TextUtils.isEmpty(password)){
                    Toast.makeText(PatientLogin.this, "Please enter your password", Toast.LENGTH_SHORT).show();

                }
                else if(!(TextUtils.isEmpty(email)&& TextUtils.isEmpty(password))) {
                    mFireBaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(PatientLogin.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                final String uid;
                                FirebaseUser user;
                                user = FirebaseAuth.getInstance().getCurrentUser();
                                uid = user.getUid();
                                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User").child("Patient").child(uid);

                                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        final String role= dataSnapshot.child("role").getValue(String.class);
                                        System.out.println(role);
                                        if(role.equals("Patient")){
                                            Intent i = new Intent(PatientLogin.this, WelcomeActivity.class);
                                            startActivity(i);
                                        }
                                    }
                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                            } else {
                                Toast.makeText(PatientLogin.this, "Login Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
