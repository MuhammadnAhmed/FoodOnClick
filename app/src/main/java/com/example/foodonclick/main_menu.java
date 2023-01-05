package com.example.foodonclick;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodonclick.R;

public class main_menu extends Fragment implements View.OnClickListener {

    Button SignIn;
    Button AdminSignIn;
    @Override
    public View onCreateView(LayoutInflater inf, ViewGroup v, Bundle b) {
        View view = inf.inflate(R.layout.main_menu, v, false);

        SignIn=view.findViewById(R.id.SignInmain);
        SignIn.setOnClickListener(this);
        AdminSignIn=view.findViewById(R.id.AdminSignInmain);
        AdminSignIn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.equals(SignIn))
        {
            if (((MainActivity)getActivity()).getName().equals("You are not signed in")) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.addToBackStack(null);
                ft.replace(R.id.Frame,new sign_in()).commit();
            }
            else {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.addToBackStack(null);
                ft.replace(R.id.Frame,new user_menu()).commit();
            }


        }
        else if (v.equals(AdminSignIn))
        {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.addToBackStack(null);
            ft.replace(R.id.Frame,new sign_in()).commit();

        }




    }
}
