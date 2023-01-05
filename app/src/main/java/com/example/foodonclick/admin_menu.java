package com.example.foodonclick;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class admin_menu extends Fragment implements View.OnClickListener{
    Button ViewMenu,AddInMenu;
    public View onCreateView(LayoutInflater inf, ViewGroup v, Bundle b) {
        View view = inf.inflate(R.layout.admin_menu, v, false);
        ViewMenu=view.findViewById(R.id.ViewMenuAdmin);
        AddInMenu=view.findViewById(R.id.AddItemInMenuAdmin);
        ViewMenu.setOnClickListener(this);
        AddInMenu.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.equals(ViewMenu))
        {
            food_menu_list_for_admin f = new food_menu_list_for_admin();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.addToBackStack(null);
            ft.replace(R.id.Frame,f).commit();

        }
        if (v.equals(AddInMenu))
        {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.addToBackStack(null);
            ft.replace(R.id.Frame,new add_food_in_menu()).commit();
        }
    }
}
