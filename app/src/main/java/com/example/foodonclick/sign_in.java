package com.example.foodonclick;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodonclick.MainActivity;
import com.example.foodonclick.R;
import com.example.foodonclick.main_menu;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.ContentValues.TAG;

public class sign_in extends Fragment implements View.OnClickListener {
    Button SignIn;

    TextView NeedAccount,Email,Password_signin;
    FirebaseAuth firebaseAuth;

    @Override
    public View onCreateView(LayoutInflater inf, ViewGroup v, Bundle b) {
        View view = inf.inflate(R.layout.sign_in, v, false);
        SignIn = view.findViewById(R.id.SignInButtonId_signin);
        SignIn.setOnClickListener(this);
        NeedAccount=view.findViewById(R.id.NeedAccountTextId);
        NeedAccount.setOnClickListener(this);
        Email=view.findViewById(R.id.EmailInputId_signin);
        Password_signin=view.findViewById(R.id.PasswordInputId_signin);
        firebaseAuth = FirebaseAuth.getInstance();

        return view;
    }
    @Override
    public void onClick(View v) {
        if (v.equals(SignIn))
        {
            if (Email.getText().toString().length() != 0 &&
                    Password_signin.getText().toString().length() != 0
            )
            {
                firebaseAuth.signInWithEmailAndPassword(Email.getText().toString(),
                        Password_signin.getText().toString().trim())
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    ((MainActivity)getActivity()).setName();
                                    if (Email.getText().toString().equals("admin@gmail.com")){
                                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                                        ft.addToBackStack(null);
                                        ft.replace(R.id.Frame,new admin_menu()).commit();
                                    }
                                    else{
                                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                                        ft.addToBackStack(null);
                                        ft.replace(R.id.Frame,new user_menu()).commit();
                                    }


                                } else {
                                    Toast.makeText(getActivity(),"Username or password is incorrect",Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });
            }
            else {
                Toast.makeText(getActivity(),"Please enter all the fields",Toast.LENGTH_SHORT).show();
            }


        }

        if (v.equals(NeedAccount))
        {
            FragmentManager FM = getFragmentManager();
            FragmentTransaction FT = FM.beginTransaction();
            FT.addToBackStack(null);
            FT.replace(R.id.Frame,new sign_up()).commit();
        }
    }


}
