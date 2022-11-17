package com.techelevator.application;

import com.techelevator.UI.UserInput;
import com.techelevator.UI.UserOutput;

public class VendingMachine
{
    public void run()
    {
        while(true)
        {
            // display home screen
            UserOutput.displayWelcomeScreen();

            // get user selection
            UserOutput.displayHomeScreenMenu();
        }
    }    
}
