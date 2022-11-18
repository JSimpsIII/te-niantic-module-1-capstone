package com.techelevator.models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PurchaseTest {

    Purchase purchase;

    @Before
    public void setup() { purchase = new Purchase(); }

    @Test
    public void feedMoney_ShouldAdd_GivenMoney_ToMoneyAvailable() {

        // arrange
        double expected = 10;

        //act
        double actual = purchase.feedMoney(10);

        //assert
        assertEquals("Because feeding money into vending machine adds to money available", expected, actual, 0.01);

    }

    @Test
    public void makeSelection_ShouldReturn_TotalCost_GivenItemId() {

        //arrange
        Inventory inventory = new Inventory();
        inventory.loadInventory();

        double gumExpected = 0.75;
        double drinkExpected = 1.5;
        double candyExpected = 1.75;
        double chipExpected = 3.65;

        //act
        double gumActual = purchase.makeSelection(inventory, "D4");
        purchase.setTotalCost(0);
        double drinkActual = purchase.makeSelection(inventory, "C4");
        purchase.setTotalCost(0);
        double candyActual = purchase.makeSelection(inventory, "B4");
        purchase.setTotalCost(0);
        double chipActual = purchase.makeSelection(inventory, "A4");

        //assert
        assertEquals("Because selecting item ID should return total cost", gumExpected, gumActual, 0.001);
        assertEquals("Because selecting item ID should return total cost", drinkExpected, drinkActual, 0.001);
        assertEquals("Because selecting item ID should return total cost", candyExpected, candyActual, 0.001);
        assertEquals("Because selecting item ID should return total cost", chipExpected, chipActual, 0.001);

    }

    @Test
    public void transaction_ShouldSubtract_TotalCost_From_MoneyAvailable() {
        // arrange
        Purchase.setMoneyAvailable(10);
        purchase.setTotalCost(3.05);
        double expected = 6.95;

        //act
        double actual = purchase.transaction();

        //assert
        assertEquals("Because total cost is subtracted from money available", expected, actual, 0.01);
    }

    @Test
    public void change_ShouldReturn_CoinsGiven() {
        // arrange
        Inventory inventory = new Inventory();
        inventory.loadInventory();
        Purchase.setMoneyAvailable(2.15);

        double changeDue = 2.15;
        int numQuarters = 8;
        int numDimes = 1;
        int numNickels = 1;

        String expected = "\nTotal amount of change to give: $" + changeDue + "\nNumber of quarters to give: "
                + numQuarters + "\nNumber of dimes to give: " + numDimes
                + "\nNumber of nickels to give: " + numNickels;

        //act
        String actual = purchase.change();

        //assert
        assertEquals("Because the user should receive the correct amount of change", expected, actual);
    }

    @After
    public void clear(){
        Purchase.setMoneyAvailable(0);
    }
}
