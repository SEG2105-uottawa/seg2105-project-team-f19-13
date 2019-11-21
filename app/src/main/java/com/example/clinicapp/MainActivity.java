package com.example.clinicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.MissingFormatArgumentException;

public class MainActivity extends AppCompatActivity {
    EditText emailText, passwordText;
    Button loginBtn, signupBtn;

    private FirebaseAuth mFireBaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passwordText);
        loginBtn = findViewById(R.id.loginBtn);
        signupBtn = findViewById(R.id.signupBtn);

        mFireBaseAuth = FirebaseAuth.getInstance();



        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String  email = emailText.getText().toString().trim();
                String password = passwordText.getText().toString().trim();
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(MainActivity.this, "Please enter your email", Toast.LENGTH_SHORT).show();

                }
                else if(TextUtils.isEmpty(password)){
                    Toast.makeText(MainActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                }
                else if(email.equals("admin@admin.com") && password.equals("5T5ptQ"))
                {
                    Intent x = new Intent(getApplicationContext(), AdminActivity.class);
                    startActivity(x);

                }
                else if(TextUtils.isEmpty(password)){
                    Toast.makeText(MainActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();

                }
                else if(!(TextUtils.isEmpty(email)&& TextUtils.isEmpty(password))) {
                    mFireBaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                final String uid;
                                FirebaseUser user;
                                user = FirebaseAuth.getInstance().getCurrentUser();
                                uid = user.getUid();
                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User").child(uid);

                                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        final String role= dataSnapshot.child("role").getValue(String.class);
                                        System.out.println(role);
                                        if(role.equals("Employee")){
                                            Intent i = new Intent(MainActivity.this, EmployeeActivity.class);
                                            startActivity(i);
                                        }
                                        else{
                                            Intent in = new Intent(MainActivity.this, WelcomeActivity.class);
                                            startActivity(in);
                                        }


                                    }
                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });



                            } else {
                                Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inToSignUp = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(inToSignUp);
            }
        });
    }
}
