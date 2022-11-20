package com.techelevator;

import com.techelevator.application.*;

public class VendingMachineCLI 
{
	//main method starting the application
	public static void main(String[] args) throws Exception {
		VendingMachine vendingMachine = new VendingMachine();
		vendingMachine.run();
	}
}
