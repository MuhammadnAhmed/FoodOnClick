package com.example.foodonclick;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class food_menu_list_handler_for_admin extends RecyclerView.Adapter<food_menu_list_handler_for_admin.ListViewHolder>{
    String[] itemname;
    String[] itemprice;
    String[] itemkey;


    public food_menu_list_handler_for_admin(String[] name, String []price , String [] itemkey){
        this.itemkey = itemkey;
        this.itemname = name;
        this.itemprice = price;

    }


    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.menu_iten_for_admin, viewGroup,false);

        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder listViewHolder, int i) {

        final String tempKey = this.itemkey[i];
        listViewHolder.DeleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("menu");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        int count = (int) dataSnapshot.getChildrenCount();

                        if (count > 1){
                            menu m = new menu(null,null);
                            databaseReference.child(tempKey).setValue(m);

                        }
                        else {
                            Snackbar.make(v, "You must keep atleast 1 item", Snackbar.LENGTH_LONG).show();
                        }



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });

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


    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView ItemName,ItemPrice;
        Button DeleteItem;
        String Itemkey = new String();

        public ListViewHolder(View itemView) {
            super(itemView);
            ItemName=  itemView.findViewById(R.id.Item_name_admin_menu);
            ItemPrice= itemView.findViewById(R.id.Item_price_admin_menu);
            DeleteItem= itemView.findViewById(R.id.DeleteItem);


        }



    }

}

