package com.example.clinicapp;

import androidx.appcompat.app.AppCompatActivity;



public class Hour extends AppCompatActivity {
    public void setHour(String hour) {
        this.hour = hour;
    }

    public String hour;
    public Hour(){
    }

    public Hour(String hour) {
        this.hour = hour;
    }
}
