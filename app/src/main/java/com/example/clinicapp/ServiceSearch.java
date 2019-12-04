package com.example.clinicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SearchView;


import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ServiceSearch extends AppCompatActivity {
    DatabaseReference ref;
    ArrayList<Clinic> list;
    RecyclerView recyclerView;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_search);

        ref = FirebaseDatabase.getInstance().getReference("Clinic");
        recyclerView = findViewById(R.id.rv);
        searchView = findViewById(R.id.searchView);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(ref != null){
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){
                        list = new ArrayList<>();
                        for(DataSnapshot ds : dataSnapshot.getChildren()){
                            list.add(ds.getValue(Clinic.class));

                        }
                        AdapterClass adapterClass = new AdapterClass(list);
                        recyclerView.setAdapter(adapterClass);
                    }
                    if(searchView != null){
                        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                            @Override
                            public boolean onQueryTextSubmit(String query) {
                                return false;
                            }

                            @Override
                            public boolean onQueryTextChange(String newText) {
                                search(newText);
                                return true;

                            }
                        });
                    }
                }

                private void search(String str){
                    ArrayList<Clinic> myList = new ArrayList();
                    for(Clinic object : list){
                        if(object.getClinicAddress().toLowerCase().contains(str.toLowerCase())){
                            myList.add(object);
                        }

                    }
                    AdapterClass adapterClass = new AdapterClass(myList);
                    recyclerView.setAdapter(adapterClass);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }




}
