package com.kaimbe.gimmedadough.atm.physical;

import org.joda.money.Money;

public class CustomerConsole {

	/** Constructor
     */
    public CustomerConsole()
    {
    }
    
    /** Display a message to the customer
     *
     *  @param message the message to display
     */
    public void display(String message)
    {
        //Simulation.getInstance().clearDisplay();
        //Simulation.getInstance().display(message);
    }
    
    /** Read a PIN entered by the customer (echoed as asterisks)
     *
     *  @param prompt the message to display prompting the customer to enter PIN
     *  @return the PIN that was entered
     *  @exception Cancelled if customer presses the CANCEL key before pressing ENTER
     */
    public int readPIN(String prompt) throws Cancelled
    {
		return 0;
    	/*
        Simulation.getInstance().clearDisplay();
        Simulation.getInstance().display(prompt);
        Simulation.getInstance().display("");
        
        String input = Simulation.getInstance().readInput(Simulation.PIN_MODE, 0);
        
        Simulation.getInstance().clearDisplay();
        
        if (input == null)
            //throw new Cancelled();
        else
            return Integer.parseInt(input);
        */
    }
    
    /** Display a menu of options and return choice made by customer
     *
     *  @param prompt message to display before the list of options
     *  @param menu the options
     *  @return the number of the option chosen (0 .. # of options - 1)
     *  Note: the options are numbered 1 .. # of options when displayed for the
     *  customer - but the menu array indices and the final result returned are in
     *  the range 0 .. # of options - 1
     *
     *  @exception Cancelled if customer presses the CANCEL key before choosing option
     */
    public synchronized int readMenuChoice(String prompt, String[] menu) throws Cancelled
    {
		return 0;
    	/*
        Simulation.getInstance().clearDisplay();
        Simulation.getInstance().display(prompt);
        for (int i = 0; i < menu.length; i ++)
            Simulation.getInstance().display((i+1) + ") " + menu[i]);

        String input = 
            Simulation.getInstance().readInput(Simulation.MENU_MODE, menu.length);
            
        Simulation.getInstance().clearDisplay();
        
        if (input == null)
            throw new Cancelled();
        else
            return Integer.parseInt(input) - 1;
            */
    }
    
    /** Read a money amount entered by the customer
     *
     *  @param prompt the message to display prompting the customer to enter amount
     *  @return the amount entered by the customer
     *  @exception Cancelled if customer presses the CANCEL key before pressing ENTER
     */
    public synchronized Money readAmount(String prompt) throws Cancelled
    {
		return null;
    	/*
        Simulation.getInstance().clearDisplay();
        Simulation.getInstance().display(prompt);
        Simulation.getInstance().display("");
        
        String input = Simulation.getInstance().readInput(Simulation.AMOUNT_MODE, 0);
        
        Simulation.getInstance().clearDisplay();
        
        if (input == null)
            throw new Cancelled();
        else
        {
            int dollars = Integer.parseInt(input) / 100;
            int cents = Integer.parseInt(input) % 100;
            return new Money(dollars, cents);
        }
        */
    }
    
    /** Exception thrown when the user presses the cancel key while the ATM is
     *  waiting for some action
     */
    public static class Cancelled extends Exception
    {
        /** Constructor
         */
        public Cancelled()
        {
            super("Cancelled by customer");
        }
    }

}
