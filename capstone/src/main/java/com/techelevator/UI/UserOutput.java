package com.techelevator.UI;

import com.techelevator.models.Inventory;
import com.techelevator.models.Purchase;
import com.techelevator.models.products.Product;
import com.techelevator.view.Colors;

import java.util.List;

public class UserOutput {

    public static void displayWelcomeScreen(){

        System.out.println();
        System.out.println(Colors.YELLOW_BACKGROUND + Colors.BLACK + "╔═══════════════════════════╗" + Colors.RESET);
        System.out.println(Colors.YELLOW_BACKGROUND + Colors.BLACK + "║ Welcome to Vend-Matic 800 ║" + Colors.RESET);
        System.out.println(Colors.YELLOW_BACKGROUND + Colors.BLACK + "╚═══════════════════════════╝" + Colors.RESET);
    }

    public static void displayHomeScreenMenu(){
        System.out.println();
        System.out.println(Colors.YELLOW + "╔═══════════╗" + Colors.RESET);
        System.out.println(Colors.YELLOW + "║ Main Menu ║" + Colors.RESET);
        System.out.println(Colors.YELLOW + "╚═══════════╝" + Colors.RESET);
        System.out.println(Colors.BLUE + "1) Display Vending Machine Items" + Colors.RESET);
        System.out.println(Colors.BLUE + "2) Purchase Menu" + Colors.RESET);
        System.out.println(Colors.BLUE + "3) Exit" + Colors.RESET);
        System.out.println();
    }


    public static void displayInventory(List<Product> products) {
        System.out.println();
        System.out.println(Colors.YELLOW + "╔═══════════════════╗" + Colors.RESET);
        System.out.println(Colors.YELLOW + "║ Product Inventory ║" + Colors.RESET);
        System.out.println(Colors.YELLOW + "╚═══════════════════╝" + Colors.RESET);

        for (Product product : products) {

            if(product.getQuantity() == 0){
                System.out.println(Colors.BLUE + product.getRowId() + ") " + product.getName() + ", $" + product.getPrice()
                        + "," + Colors.RESET + Colors.RED + " SOLD OUT" + Colors.RESET);
            } else if (product.getQuantity() > 0) {
                System.out.println(Colors.BLUE + product.getRowId() + ") " + product.getName() + ", $" + product.getPrice()
                        + ", In Stock: " + product.getQuantity() + Colors.RESET);
            } else {
                // This should never be called
                System.out.println("Trying to set quantity to -1.");
            }

        }

    }


    public static void displayPurchaseMenu(double moneyAvailable){
        System.out.println();
        System.out.println(Colors.YELLOW + "╔═══════════════╗" + Colors.RESET);
        System.out.println(Colors.YELLOW + "║ Purchase Menu ║" + Colors.RESET);
        System.out.println(Colors.YELLOW + "╚═══════════════╝" + Colors.RESET);
        System.out.println("Current money provided: $" + String.format("%.2f", moneyAvailable));
        System.out.println();
        System.out.println(Colors.BLUE + "1) Feed Money" + Colors.RESET);
        System.out.println(Colors.BLUE + "2) Select Product" + Colors.RESET);
        System.out.println(Colors.BLUE + "3) Finish Transaction" + Colors.RESET);
        System.out.println();
    }

}
