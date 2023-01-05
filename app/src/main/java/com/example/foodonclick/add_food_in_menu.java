package com.example.foodonclick;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add_food_in_menu extends Fragment implements View.OnClickListener {
    EditText ItemName,ItemPrice;
    Button AddItem;
    DatabaseReference DR;
    public View onCreateView(LayoutInflater inf, ViewGroup v, Bundle b) {
        View view = inf.inflate(R.layout.add_food_in_menu, v, false);
        ItemName=view.findViewById(R.id.Item_name);
        ItemPrice=view.findViewById(R.id.Item_price);
        AddItem=view.findViewById(R.id.Add_Item);
        AddItem.setOnClickListener(this);

        DR = FirebaseDatabase.getInstance().getReference("menu");

        return view;

    }

    @Override
    public void onClick(View v) {
        if (v.equals(AddItem))
        {
            if (ItemName.getText().toString().length() != 0 && ItemPrice.getText().toString().length() != 0)
            {
            String s = DR.push().getKey();
            menu m = new menu(ItemName.getText().toString(),ItemPrice.getText().toString());
            DR.child(s).setValue(m);
            Toast.makeText(getActivity(),"Item Added Successfully",Toast.LENGTH_SHORT).show();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.addToBackStack(null);
            ft.replace(R.id.Frame,new add_food_in_menu()).commit();
        }
            else {
                Toast.makeText(getActivity(),"Please enter data in all the fields",Toast.LENGTH_SHORT).show();
            }
        }

    }
}

