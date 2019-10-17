package com.example.clinicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    EditText emailText, passwordText;
    Button loginBtn;
    TextView registerText;
    FirebaseAuth mFireBaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFireBaseAuth = FirebaseAuth.getInstance();
        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passwordText);
        loginBtn = findViewById(R.id.loginBtn);
        registerText = findViewById(R.id.registerText);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFireBaseAuth.getCurrentUser();
                if(mFirebaseUser != null){
                    Toast.makeText(MainActivity.this, "You are logged in", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, WelcomeActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(MainActivity.this, "Please try logging in again",Toast.LENGTH_SHORT).show();
                }
            }
        };
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailText.getText().toString();
                String password = passwordText.getText().toString();
                if(email.isEmpty()){
                    emailText.setError("Please enter your email address");
                    emailText.requestFocus();
                }
                else if(password.isEmpty()){
                    passwordText.setError("Please enter your password");
                    passwordText.requestFocus();
                }
                else if(email.isEmpty() && password.isEmpty()){
                    Toast.makeText(MainActivity.this,"Fields are empty!",Toast.LENGTH_SHORT).show();
                }
                else if(!(email.isEmpty() && password.isEmpty())) {
                    mFireBaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(MainActivity.this,"Login Error, Please Login Again",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Intent intToWelcome = new Intent(MainActivity.this, WelcomeActivity.class);
                                startActivity(intToWelcome);
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(MainActivity.this,"Error Occured!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inToSignUp = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(inToSignUp);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFireBaseAuth.addAuthStateListener(mAuthStateListener);

    }
}
