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

        System.out.println("Please select an item: ");
        String selection = input.nextLine();

        return selection;

    }

    public static BigDecimal getPayment() {

        System.out.println("Pleae enter payment amount: $");
        String amount = input.nextLine();

        return new BigDecimal(amount);

    }

}
