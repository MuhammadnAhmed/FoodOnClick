package com.example.foodonclick;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class bill_list extends Fragment {
    RecyclerView recyclerViewnew;
    public View onCreateView(LayoutInflater inf, ViewGroup v, Bundle b) {
        View view = inf.inflate(R.layout.total_bill_list_handler, v, false);
        recyclerViewnew = (RecyclerView) view.findViewById(R.id.recycler_id_3);
        recyclerViewnew.setLayoutManager(new LinearLayoutManager(getActivity()));


            DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference("order");
            databaseReference2.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    long count = (dataSnapshot.getChildrenCount());


                    String[]ItemName = new String[(int) count];
                    String[]ItemPrice =  new String[(int) count];

                    int i=0;
                    for (DataSnapshot ds : dataSnapshot.getChildren()){

                        ItemName[i]= ds.getValue().toString();
                        ItemPrice[i]= ds.getValue().toString();


                        i++;

                    }
                    if (i != 0){

                        bill_handler adapter = new bill_handler(ItemName,ItemPrice);
                        recyclerViewnew.setAdapter(adapter);


                    }
                    else {
                        Toast.makeText(getActivity(),"Nothing added in menu",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });


        return view;
    }
}
