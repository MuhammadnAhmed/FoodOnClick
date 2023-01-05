package com.example.foodonclick;

public class user {

    private String Name, Email, Password;

    public user(String name, String email, String password) {

        Name = name;
        Email = email;
        Password = password;
    }

    public user(){

    }

    public String getName() { return Name; }

    public String getEmail() {
        return Email;
    }


    public String getPassword() {
        return Password;
    }
}
