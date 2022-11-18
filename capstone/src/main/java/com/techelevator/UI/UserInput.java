package com.techelevator.UI;

import java.math.BigDecimal;
import java.util.Scanner;

public class UserInput {

    private static Scanner input = new Scanner(System.in);

    public static String getMenuSelection(){

        System.out.println("Please make a selection: ");
        String selection = input.nextLine();

        return selection;
    }

    public static String getItemSelection() {

        System.out.println("Please enter item ID: ");
        String selection = input.nextLine();

        return selection;

    }

    public static double getPayment() {
        System.out.println("Please Enter Whole Dollar Amount: ");
        double amount = Double.parseDouble(input.nextLine());
        while (amount % 1 != 0 || amount < 0) {
            System.out.println("Money inserted needs to be whole dollar amount");
            System.out.println("Please Enter Whole Dollar Amount: ");
            amount = Double.parseDouble(input.nextLine());
        }
        return amount;

    }

    public static String readyToPay() {
        System.out.println();
        System.out.println("Are you ready to make a purchase(Y/N) or go back (Menu)?");
        String selection = input.nextLine();

        return selection;
    }


    public static String continueOrNot() {
        System.out.println("Do you want to add more money(Y/N)?");
        String selection = input.nextLine();

        return selection;
    }
}
