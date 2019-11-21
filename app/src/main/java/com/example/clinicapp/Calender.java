package com.example.clinicapp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class Calender extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calender);
    }

    public void mondayEdit(View v){
        TextView mondayHour = (TextView) findViewById(R.id.mondayHour);
        TextView mondayEdit = (TextView) findViewById(R.id.mondayEdit);
        if (!mondayEdit.getText().toString().trim().equals("") && Pattern.matches("^[0-2][0-9][0-9][0-9]-[0-2][0-9][0-9][0-9]$", mondayEdit.getText().toString().trim())){
            mondayHour.setText(mondayEdit.getText());
            mondayEdit.setText("");
        }
        else{
            Toast.makeText(Calender.this, "Invalid Time", Toast.LENGTH_SHORT).show();
        }

    }

    public void tuesdayEdit(View v){
        TextView tuesdayHour = (TextView) findViewById(R.id.tuesdayHour);
        TextView tuesdayEdit = (TextView) findViewById(R.id.tuesdayEdit);
        if (!tuesdayEdit.getText().toString().trim().equals("") && Pattern.matches("^[0-2][0-9][0-9][0-9]-[0-2][0-9][0-9][0-9]$", tuesdayEdit.getText().toString().trim())){
            tuesdayHour.setText(tuesdayEdit.getText());
            tuesdayEdit.setText("");
        }
        else{
            Toast.makeText(Calender.this, "Invalid Time", Toast.LENGTH_SHORT).show();
        }
    }

    public void wednesdayEdit(View v){
        TextView wednesdayHour = (TextView) findViewById(R.id.wednesdayHour);
        TextView wednesdayEdit = (TextView) findViewById(R.id.wednesdayEdit);
        if (!wednesdayEdit.getText().toString().trim().equals("") && Pattern.matches("^[0-2][0-9][0-9][0-9]-[0-2][0-9][0-9][0-9]$", wednesdayEdit.getText().toString().trim())){
            wednesdayHour.setText(wednesdayEdit.getText());
            wednesdayEdit.setText("");
        }
        else{
            Toast.makeText(Calender.this, "Invalid Time", Toast.LENGTH_SHORT).show();
        }
    }

    public void thursdayEdit(View v){
        TextView thursdayHour = (TextView) findViewById(R.id.thursdayHour);
        TextView thursdayEdit = (TextView) findViewById(R.id.thursdayEdit);
        if (!thursdayEdit.getText().toString().trim().equals("") && Pattern.matches("^[0-2][0-9][0-9][0-9]-[0-2][0-9][0-9][0-9]$", thursdayEdit.getText().toString().trim())){
            thursdayHour.setText(thursdayEdit.getText());
            thursdayEdit.setText("");
        }
        else{
            Toast.makeText(Calender.this, "Invalid Time", Toast.LENGTH_SHORT).show();
        }
    }

    public void fridayEdit(View v){
        TextView fridayHour = (TextView) findViewById(R.id.fridayHour);
        TextView fridayEdit = (TextView) findViewById(R.id.fridayEdit);
        if (!fridayEdit.getText().toString().trim().equals("") && Pattern.matches("^[0-2][0-9][0-9][0-9]-[0-2][0-9][0-9][0-9]$", fridayEdit.getText().toString().trim())){
            fridayHour.setText(fridayEdit.getText());
            fridayEdit.setText("");
        }
        else{
            Toast.makeText(Calender.this, "Invalid Time", Toast.LENGTH_SHORT).show();
        }
    }
}
