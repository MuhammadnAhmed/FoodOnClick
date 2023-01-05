package com.example.foodonclick;

public class menu {
    private String ItemName,ItemPrice;


    public menu(String itemName, String itemPrice) {
        ItemName = itemName;
        ItemPrice = itemPrice;
    }


    public menu(){}


    public String getItemName() {
        return ItemName;
    }

    public String getItemPrice() {
        return ItemPrice;
    }
}
