package com.example.clinicapp;

import android.icu.text.IDNA;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

public class ClinicRegistry extends AppCompatActivity {
    EditText clinicName, clinicAddress, clinicPhoneNumber, insuranceType, paymentMethod;
    Button registerClinicBtn;
    DatabaseReference databaseReference;
    Clinic clinic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinicregister);

        clinicName = (EditText) findViewById(R.id.clinicName);
        clinicAddress = (EditText) findViewById(R.id.clinicAddress);
        clinicPhoneNumber = (EditText) findViewById(R.id.clinicPhoneNumber);
        insuranceType = (EditText) findViewById(R.id.insuranceType);
        paymentMethod = (EditText) findViewById(R.id.paymentMethod);
        registerClinicBtn = (Button) findViewById(R.id.registerClinicBtn);
        clinic = new Clinic();
        databaseReference = FirebaseDatabase.getInstance().getReference("Clinic");

        registerClinicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cname = clinicName.getText().toString().trim();
                String caddresss = clinicAddress.getText().toString().trim();
                String cphoneNumber = clinicPhoneNumber.getText().toString().trim();
                String cinsuranceType = insuranceType.getText().toString().trim();
                String cpaymenthMethod = paymentMethod.getText().toString().trim();
                if (TextUtils.isEmpty(cname)) {
                    Toast.makeText(ClinicRegistry.this, "Please enter clinic name", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(caddresss)) {
                    Toast.makeText(ClinicRegistry.this, "Please enter clinic address", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(cphoneNumber)) {
                    Toast.makeText(ClinicRegistry.this, "Please enter clinic phone number", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(cinsuranceType)) {
                    Toast.makeText(ClinicRegistry.this, "Please enter insurance type", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(cpaymenthMethod)) {
                    Toast.makeText(ClinicRegistry.this, "Please enter accepted payment method", Toast.LENGTH_SHORT).show();
                }else{
                    clinic.setClinicName(cname);
                    clinic.setClinicAddress(caddresss);
                    clinic.setClinicPhoneNumber(cinsuranceType);
                    clinic.setPaymentMethod(cpaymenthMethod);
                    clinic.setInsuranceType(cinsuranceType);
                    String mGroupId = databaseReference.push().getKey();

                    databaseReference.child(mGroupId).child("Info").setValue(clinic);
                    Toast.makeText(ClinicRegistry.this, "Clinic Registry Complete", Toast.LENGTH_SHORT).show();

                    databaseReference = FirebaseDatabase.getInstance().getReference("User");
                    final String uid;
                    FirebaseUser user;
                    user = FirebaseAuth.getInstance().getCurrentUser();
                    uid = user.getUid();
                    databaseReference.child("Employee").child(uid).child("clinicID").setValue(mGroupId);
                    Intent inToEmployeeAcitvity = new Intent(ClinicRegistry.this, EmployeeActivity.class);
                    startActivity(inToEmployeeAcitvity);
                }
            }
        });
    }
}
