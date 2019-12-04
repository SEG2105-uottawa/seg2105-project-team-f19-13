package com.example.clinicapp;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;



public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyViewHolder>{
    ArrayList<Clinic> list;
    public AdapterClass(ArrayList<Clinic> list){
        this.list = list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_holder,parent,false);
        return new MyViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.id.setText(list.get(position).getClinicName());
        holder.desc.setText(list.get(position).getClinicAddress());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, View.OnClickListener,
            MenuItem.OnMenuItemClickListener{
        TextView id,desc;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.dealID);
            desc = itemView.findViewById(R.id.description);
            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);


        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
           System.out.println("CLICKED");
           return true;
        }

        @Override
        public void onClick(View v) {

        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuItem myActionItem = menu.add("Add to Booking");
            myActionItem.setOnMenuItemClickListener(this);

        }
    }



}

