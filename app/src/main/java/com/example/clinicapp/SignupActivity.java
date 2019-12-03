package com.example.clinicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
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

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class SignupActivity extends AppCompatActivity {
    EditText emailText, passwordText,firstName, lastName, phoneNumber;
    Button SignupBtn;
    RadioButton employeeBtn, patientBtn;
    private FirebaseAuth mFireBaseAuth;
    DatabaseReference databaseReference;
    String role= "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        emailText = (EditText) findViewById(R.id.emailText);
        passwordText = (EditText) findViewById(R.id.passwordText);
        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        SignupBtn = (Button) findViewById(R.id.SignupBtn);
        employeeBtn = (RadioButton) findViewById(R.id.employeeBtn);
        patientBtn = (RadioButton) findViewById(R.id.patientBtn);

        mFireBaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("User");

        SignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = emailText.getText().toString().trim();
                final String password = passwordText.getText().toString().trim();
                final String fName = firstName.getText().toString().trim();
                final String lName = lastName.getText().toString().trim();
                final String phoneNum = phoneNumber.getText().toString().trim();
                if(employeeBtn.isChecked()){
                    role = "Employee";
                }
                if(patientBtn.isChecked()){
                    role = "Patient";
                }
                if(password.length()<6) {
                    Toast.makeText(SignupActivity.this, "Password is too short", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(SignupActivity.this, "Please enter your email", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(password)){
                    Toast.makeText(SignupActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(fName)){
                    Toast.makeText(SignupActivity.this, "Please enter your first name", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(lName)){
                    Toast.makeText(SignupActivity.this, "Please enter your last name", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(phoneNum)){
                    Toast.makeText(SignupActivity.this, "Please enter your phone number", Toast.LENGTH_SHORT).show();
                }
                else if(!(patientBtn.isChecked() || employeeBtn.isChecked())){
                    Toast.makeText(SignupActivity.this, "Please select a role", Toast.LENGTH_SHORT).show();
                }else {
                    mFireBaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                User information = new User(fName, lName, email, phoneNum, password, role);
                                if(role.equals("Employee")){
                                    FirebaseDatabase.getInstance().getReference("User").child("Employee").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(SignupActivity.this, "Employee SignUp Complete", Toast.LENGTH_SHORT).show();
                                            final String uid;
                                            FirebaseUser user;
                                            user = FirebaseAuth.getInstance().getCurrentUser();
                                            uid = user.getUid();
                                            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User").child("Employee").child(uid);
                                            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(DataSnapshot dataSnapshot) {
                                                    final String role= dataSnapshot.child("role").getValue(String.class);
                                                    System.out.println(role);
                                                    if(role.equals("Employee")){
                                                        Intent i = new Intent(SignupActivity.this, EmployeeActivity.class);
                                                        startActivity(i);
                                                    }
                                                }
                                                @Override
                                                public void onCancelled(DatabaseError databaseError) {

                                                }
                                            });

                                        }
                                    });
                                }else{
                                    FirebaseDatabase.getInstance().getReference("User").child("Patient").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(SignupActivity.this, "Patient SignUp Complete", Toast.LENGTH_SHORT).show();
                                            final String id;
                                            FirebaseUser user2;
                                            user2 = FirebaseAuth.getInstance().getCurrentUser();
                                            id = user2.getUid();
                                            DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference("User").child("Patient").child(id);
                                            reference2.addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    final String role = dataSnapshot.child("role").getValue(String.class);
                                                    if (role.equals("Patient")){
                                                        Intent in = new Intent(SignupActivity.this, WelcomeActivity.class);
                                                        startActivity(in);
                                                    }
                                                }
                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                        }
                                    });
                                }

                            }
                        }
                    });
                }
            }
        });
    }
    public static String toSHA256(String password){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return toHexString(encodedhash);
        } catch(NoSuchAlgorithmException e) {
            return null;
        }
    }

    private static String toHexString(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
