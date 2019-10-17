package com.example.clinicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {
    EditText emailText, passwordText,firstName, lastName, phoneNumber;
    Button SignupBtn;
    TextView roleText;
    FirebaseAuth mFireBaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Spinner mySpinner = (Spinner) findViewById(R.id.classRole);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(SignupActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.roles));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        mFireBaseAuth = FirebaseAuth.getInstance();
        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passwordText);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        phoneNumber = findViewById(R.id.phoneNumber);
        SignupBtn = findViewById(R.id.SignupBtn);
        roleText = findViewById(R.id.roleText);

        SignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailText.getText().toString();
                String password = passwordText.getText().toString();
                String fName = firstName.getText().toString();
                String lName = lastName.getText().toString();
                String phoneNum = phoneNumber.getText().toString();
                if(email.isEmpty()){
                    emailText.setError("Please enter your email address");
                    emailText.requestFocus();
                }
                else if(password.isEmpty()){
                    passwordText.setError("Please enter your password");
                    passwordText.requestFocus();
                }
                else if(fName.isEmpty()){
                    firstName.setError("Please enter your first name");
                    firstName.requestFocus();
                }
                else if(lName.isEmpty()){
                    lastName.setError("Please enter your last name");
                    lastName.requestFocus();
                }
                else if(phoneNum.isEmpty()){
                    phoneNumber.setError("Please enter your password");
                    phoneNumber.requestFocus();
                }
                else if(email.isEmpty() && password.isEmpty() && fName.isEmpty() && lName.isEmpty() && phoneNum.isEmpty()){
                    Toast.makeText(SignupActivity.this,"Fields are empty!",Toast.LENGTH_SHORT).show();
                }
                else if(!(email.isEmpty() && password.isEmpty() && fName.isEmpty() && lName.isEmpty() && phoneNum.isEmpty())){
                    mFireBaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(SignupActivity.this,"SgnUp Unsuccessful, Please Try Again",Toast.LENGTH_SHORT).show();

                            }
                            else{
                                startActivity(new Intent(SignupActivity.this, WelcomeActivity.class));
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(SignupActivity.this,"Error Occured!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        SignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignupActivity.this, MainActivity.class);
                startActivity(i);
            }
        });



    }
}
