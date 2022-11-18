package com.techelevator.models;

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
    public void transaction() {
    }

    @Test
    public void change() {
    }
}
