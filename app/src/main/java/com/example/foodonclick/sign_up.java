package com.example.foodonclick;

import android.app.UiAutomation;
import android.graphics.EmbossMaskFilter;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodonclick.MainActivity;
import com.example.foodonclick.user;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class sign_up extends Fragment implements View.OnClickListener {
    Button SignUp;
    EditText Email,Password_signup,ConfirmPassword_signup,Name_signup;
    FirebaseAuth mAuth;
    DatabaseReference DR;
    FirebaseUser firebaseUser;


    @Override
    public View onCreateView(LayoutInflater inf, ViewGroup v, Bundle b) {
        View view = inf.inflate(R.layout.sign_up, v, false);
        SignUp = view.findViewById(R.id.SignUpButtonId);
        SignUp.setOnClickListener(this);
        Email =view.findViewById(R.id.EmailInputId_signup);
        Password_signup=view.findViewById(R.id.PasswordInputId_signup);
        ConfirmPassword_signup=view.findViewById(R.id.ConfirmPasswordInputId);
        Name_signup=view.findViewById(R.id.NameInputId_signup);
        mAuth = FirebaseAuth.getInstance();
        DR = FirebaseDatabase.getInstance().getReference("user");
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.equals(SignUp))

            firebaseUser = mAuth.getCurrentUser();
        {
            if (Email.getText().toString().length() != 0 && Password_signup.getText().toString().length() != 0
                    && ConfirmPassword_signup.getText().toString().length() != 0){

                if (Password_signup.getText().toString().trim().equals(ConfirmPassword_signup.getText().toString().trim()) )
                {

                    firebaseUser = mAuth.getCurrentUser();
                    mAuth.createUserWithEmailAndPassword(Email.getText().toString(),
                            Password_signup.getText().toString().trim())
                            .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {


                                        user u = new user(Name_signup.getText().toString(), Email.getText().toString().trim(),
                                                Password_signup.getText().toString().trim());
                                        DR.child(firebaseUser.getUid()).setValue(u);
                                        ((MainActivity)getActivity()).setName();
                                        if (((MainActivity)getActivity()).getName().equals("admin")){
                                            FragmentManager FM = getFragmentManager();
                                            FragmentTransaction FT = FM.beginTransaction();
                                            FT.addToBackStack(null);
                                            FT.replace(R.id.Frame, new admin_menu()).commit();
                                        }
                                        else {
                                            FragmentManager FM = getFragmentManager();
                                            FragmentTransaction FT = FM.beginTransaction();
                                            FT.addToBackStack(null);
                                            FT.replace(R.id.Frame, new user_menu()).commit();
                                        }

                                    }

                                    else {
                                        Toast.makeText(getActivity(),"Registration failed",Toast.LENGTH_SHORT).show();

                                    }

                                    // ...
                                }
                            });
                }
                else {
                    Toast.makeText(getActivity(),"Password does not match", Toast.LENGTH_LONG).show();
                }
            }
            else {
                Toast.makeText(getActivity(),"Please enter all the fields", Toast.LENGTH_LONG).show();
            }




        }

    }
}
