package com.example.foodonclick;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class bill_handler extends RecyclerView.Adapter<bill_handler.ListViewHolder> {
    String[] itemname;
    String[] itemprice;



    public bill_handler(String[] name, String []price){
        this.itemname = name;
        this.itemprice = price;


    }


    @NonNull
    @Override
    public bill_handler.ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.total_bill_list, viewGroup,false);

        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull bill_handler.ListViewHolder listViewHolder, int i) {


        listViewHolder.ItemNamebill.setText(this.itemname[i]);

    }


    @Override
    public int getItemCount()
    {
        return itemname.length;
    }


    public class ListViewHolder extends RecyclerView.ViewHolder{
        TextView ItemNamebill;


        public ListViewHolder(View itemView) {
            super(itemView);
            ItemNamebill=  itemView.findViewById(R.id.Item_name_bill);


        }



    }


}

