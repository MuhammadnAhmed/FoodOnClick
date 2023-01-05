package com.example.foodonclick;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class food_menu_list_for_user extends Fragment implements View.OnClickListener{
    EditText Tableno;
    Button PlaceOrder;

    public View onCreateView(LayoutInflater inf, ViewGroup v, Bundle b) {
        View view = inf.inflate(R.layout.food_menu_list_for_user, v, false);
        Tableno=view.findViewById(R.id.Table_no);
        PlaceOrder=view.findViewById(R.id.button);
        PlaceOrder.setOnClickListener(this);
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_id_2);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("menu");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                long count = (dataSnapshot.getChildrenCount());

                String[]ItemID = new String[(int) count];
                String[]ItemName = new String[(int) count];
                String[]ItemPrice =  new String[(int) count];

                int i=0;
                for (DataSnapshot ds : dataSnapshot.getChildren()){

                    ItemName[i]= ds.child("itemName").getValue().toString();
                    ItemPrice[i]= ds.child("itemPrice").getValue().toString();
                    ItemID[i]= ds.getKey();

                    i++;

                }
                if (i != 0){

                    food_menu_list_handler_for_user adapter = new food_menu_list_handler_for_user(ItemName,ItemPrice,ItemID);
                    recyclerView.setAdapter(adapter);

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

    @Override
    public void onClick(View v) {
        if (v.equals(PlaceOrder))
        {
            if (Tableno.getText().toString().length()!=0)
            { FragmentManager FM = getFragmentManager();
                FragmentTransaction FT = FM.beginTransaction();
                FT.addToBackStack(null);
                FT.replace(R.id.Frame, new bill_list()).commit();

            }
            else{
                Toast.makeText(getActivity(),"Please enter table number",Toast.LENGTH_SHORT).show();

            }

        }
    }
}
