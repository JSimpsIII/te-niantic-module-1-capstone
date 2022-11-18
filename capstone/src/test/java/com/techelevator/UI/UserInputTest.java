package com.techelevator.UI;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserInputTest {

    @Test
    public void getPayment_ReturnsWholeDollarAmount() {
        // arrange
        double expected = 20;
        double amount = 10;
        // act
        double actual = UserInput.getPayment();

        // assert

    }

    @Test
    public void readyToPay() {
    }

    @Test
    public void continueOrNot() {
    }
}
