package com.example.foodonclick;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class food_menu_list_handler_for_user extends RecyclerView.Adapter<food_menu_list_handler_for_user.ListViewHolder> {
    String[] itemname;
    String[] itemprice;
    String[] itemkey;


    public food_menu_list_handler_for_user(String[] name, String []price, String [] itemkey){
        this.itemname = name;
        this.itemprice = price;
        this.itemkey = itemkey;

    }


    @NonNull
    @Override
    public food_menu_list_handler_for_user.ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.food_item_for_user, viewGroup,false);

        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder listViewHolder, int i) {
        final String tempKey = this.itemname[i];
        final String tempKey2 = this.itemprice[i];

        listViewHolder.AddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                DatabaseReference DR;
                DR = FirebaseDatabase.getInstance().getReference("order");
                DR.child(tempKey).setValue(tempKey2);
                Snackbar.make(v, "Item added in order queue", Snackbar.LENGTH_LONG).show();
            }
        });

        listViewHolder.ItemName.setText(this.itemname[i]);
        listViewHolder.ItemPrice.setText(this.itemprice[i]);
    }


    @Override
    public int getItemCount()
    {
        return itemname.length;
    }


    public class ListViewHolder extends RecyclerView.ViewHolder{
        TextView ItemName,ItemPrice;
        Button AddItem;

        public ListViewHolder(View itemView) {
            super(itemView);
            ItemName=  itemView.findViewById(R.id.Item_name_user_menu);
            ItemPrice= itemView.findViewById(R.id.Item_price_user_menu);
            AddItem= itemView.findViewById(R.id.AddItemInOrder);


        }



    }


}