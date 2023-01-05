package com.example.foodonclick;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class user_menu extends Fragment implements View.OnClickListener{
    Button ViewMenu,SignOut;
    public View onCreateView(LayoutInflater inf, ViewGroup v, Bundle b) {
        View view = inf.inflate(R.layout.user_menu, v, false);
        ViewMenu=view.findViewById(R.id.ViewMenuUser);
        SignOut=view.findViewById(R.id.Signout);
        ViewMenu.setOnClickListener(this);
        SignOut.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.equals(ViewMenu))
        {
            food_menu_list_for_user f = new food_menu_list_for_user();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.addToBackStack(null);
            ft.replace(R.id.Frame,f).commit();

        }
        if (v.equals(SignOut))
        {
            ((MainActivity)getActivity()).NameSet.setText("You are not signed in");
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.addToBackStack(null);
            ft.replace(R.id.Frame,new main_menu()).commit();
        }
    }
}
