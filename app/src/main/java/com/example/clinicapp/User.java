package com.example.clinicapp;

public class User {
    public String fName, lName, email, phoneNum, password , role;

    public User(){

    }

    public User(String fName, String lName, String email, String phoneNum, String password, String role) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.phoneNum = phoneNum;
        this.password = SignupActivity.toSHA256(password);
        this.role = role;
    }
}
