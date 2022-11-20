package com.techelevator.UI;

import com.techelevator.models.Inventory;
import com.techelevator.models.Purchase;
import com.techelevator.models.products.Product;

import java.util.List;

public class UserOutput {

    public static void displayWelcomeScreen(){

        System.out.println();
        System.out.println("*************************");
        System.out.println("Welcome to Vend-Matic 800");
        System.out.println("*************************");
    }

    public static void displayHomeScreenMenu(){
        System.out.println();
        System.out.println("*********************");
        System.out.println("Main Home Screen");
        System.out.println("*********************");
        System.out.println();
        System.out.println("1) Display Vending Machine Items");
        System.out.println("2) Purchase Menu");
        System.out.println("3) Exit");
        System.out.println();
    }


    public static void displayInventory(List<Product> products) {
        System.out.println();
        System.out.println("*********************");
        System.out.println("Products");
        System.out.println("*********************");
        System.out.println();

        for (Product product : products) {

            if(product.getQuantity() == 0){
                System.out.println(product.getRowId() + ") " + product.getName() + ", $" + product.getPrice()
                        + ", SOLD OUT");
            } else if (product.getQuantity() > 0) {
                System.out.println(product.getRowId() + ") " + product.getName() + ", $" + product.getPrice()
                        + ", In Stock: " + product.getQuantity());
            } else {
                // This should never be called
                System.out.println("Trying to set quantity to -1.");
            }

        }

    }


    public static void displayPurchaseMenu(double moneyAvailable){
        System.out.println();
        System.out.println("*********************");
        System.out.println("Purchase Menu");
        System.out.println("*********************");
        System.out.println();
        System.out.println("Current money provided: $" + String.format("%.2f", moneyAvailable));
        System.out.println("1) Feed Money");
        System.out.println("2) Select Product");
        System.out.println("3) Finish Transaction");
    }

}
