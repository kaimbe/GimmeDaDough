package com.kaimbe.gimmedadough.gui.physical;

import org.joda.money.Money;

import com.kaimbe.gimmedadough.atm.physical.Cancelled;
import com.kaimbe.gimmedadough.atm.physical.Console;
import com.kaimbe.gimmedadough.gui.GUIMediator;

public class GUIDisplay implements Console{

	/** Constructor
     */
    public GUIDisplay()
    {
    	
    }
    
    /** Display a message to the customer
     *
     *  @param message the message to display
     */
    @Override
    public void display(String message)
    {
    	GUIMediator.getInstance().clearDisplay();
    	GUIMediator.getInstance().display(message);
    }
    
    /** Read a PIN entered by the customer (echoed as asterisks)
     *
     *  @param prompt the message to display prompting the customer to enter PIN
     *  @return the PIN that was entered
     *  @exception Cancelled if customer presses the CANCEL key before pressing ENTER
     */
    @Override
    public int readPIN(String prompt) throws Cancelled
    {
    	GUIMediator.getInstance().clearDisplay();
    	GUIMediator.getInstance().display(prompt);
    	GUIMediator.getInstance().display("");
        
        String input = GUIMediator.getInstance().readInput(/*Simulation.PIN_MODE*/ 0, 0);
        
        GUIMediator.getInstance().clearDisplay();
        
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
    @Override
    public synchronized int readMenuChoice(String prompt, String[] menu) throws Cancelled
    {
    	GUIMediator.getInstance().clearDisplay();
    	GUIMediator.getInstance().display(prompt);
        for (int i = 0; i < menu.length; i ++)
        	GUIMediator.getInstance().display((i+1) + ") " + menu[i]);

        String input = 
        		GUIMediator.getInstance().readInput(/*Simulation.MENU_MODE*/ 0, menu.length);
            
        GUIMediator.getInstance().clearDisplay();
        
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
    @Override
    public synchronized Money readAmount(String prompt) throws Cancelled
    {
    	GUIMediator.getInstance().clearDisplay();
    	GUIMediator.getInstance().display(prompt);
    	GUIMediator.getInstance().display("");
        
        String input = GUIMediator.getInstance().readInput(/*Simulation.AMOUNT_MODE*/ 0, 0);
        
        GUIMediator.getInstance().clearDisplay();
        
        if (input == null)
            throw new Cancelled();
        else
        {
        	// TODO: Generalize currency
            return Money.parse("CAD " + input);
        }
        
    }
}
