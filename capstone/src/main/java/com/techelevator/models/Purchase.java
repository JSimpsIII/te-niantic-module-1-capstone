package com.techelevator.models;

import com.techelevator.UI.UserInput;

public class Purchase {
    private static double moneyProvided = 0;

    public static double getMoneyProvided() {
        return moneyProvided;
    }

    public Purchase(double moneyProvided) {
        this.moneyProvided += UserInput.getPayment();
    }

    public double feedMoney() {
        moneyProvided += UserInput.getPayment();
        return moneyProvided;
    }

}
