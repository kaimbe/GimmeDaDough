package com.kaimbe.gimmedadough.atm.physical;

import org.joda.money.Money;

import com.kaimbe.gimmedadough.atm.ATMController;

public class CustomerConsole {

	/** Constructor
     */
    public CustomerConsole(ATMController controller)
    {
    	this.controller = controller;
    }
    
    /** Display a message to the customer
     *
     *  @param message the message to display
     */
    public void display(String message)
    {
        controller.getMediator().clearDisplay();
        controller.getMediator().display(message);
    }
    
    /** Read a PIN entered by the customer (echoed as asterisks)
     *
     *  @param prompt the message to display prompting the customer to enter PIN
     *  @return the PIN that was entered
     *  @exception Cancelled if customer presses the CANCEL key before pressing ENTER
     */
    public int readPIN(String prompt) throws Cancelled
    {
    	controller.getMediator().clearDisplay();
    	controller.getMediator().display(prompt);
    	controller.getMediator().display("");
        
        String input = controller.getMediator().readInput(/*Simulation.PIN_MODE*/ 0, 0);
        
        controller.getMediator().clearDisplay();
        
        if (input == null)
            throw new Cancelled();
        else
            return Integer.parseInt(input);
        
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
    	controller.getMediator().clearDisplay();
    	controller.getMediator().display(prompt);
        for (int i = 0; i < menu.length; i ++)
        	controller.getMediator().display((i+1) + ") " + menu[i]);

        String input = 
        		controller.getMediator().readInput(/*Simulation.MENU_MODE*/ 0, menu.length);
            
        controller.getMediator().clearDisplay();
        
        if (input == null)
            throw new Cancelled();
        else
            return Integer.parseInt(input) - 1;
            
    }
    
    /** Read a money amount entered by the customer
     *
     *  @param prompt the message to display prompting the customer to enter amount
     *  @return the amount entered by the customer
     *  @exception Cancelled if customer presses the CANCEL key before pressing ENTER
     */
    public synchronized Money readAmount(String prompt) throws Cancelled
    {
    	controller.getMediator().clearDisplay();
    	controller.getMediator().display(prompt);
    	controller.getMediator().display("");
        
        String input = controller.getMediator().readInput(/*Simulation.AMOUNT_MODE*/ 0, 0);
        
        controller.getMediator().clearDisplay();
        
        if (input == null)
            throw new Cancelled();
        else
        {
        	// TODO: Generalize currency
            return Money.parse("CAD " + input);
        }
        
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
    
    private ATMController controller;

}
