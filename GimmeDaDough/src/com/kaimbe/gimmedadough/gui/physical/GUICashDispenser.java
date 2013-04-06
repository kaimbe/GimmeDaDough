package com.kaimbe.gimmedadough.gui.physical;

import java.util.logging.Logger;

import org.joda.money.Money;

import com.kaimbe.gimmedadough.atm.physical.CashDispenser;
import com.kaimbe.gimmedadough.gui.GUIMediator;

public class GUICashDispenser implements CashDispenser{
	

	 /** Constructor
    *
    *  @param log the log in which to record dispensing cash
    */
   public GUICashDispenser()
   {
       cashOnHand = Money.parse("CAD 0");
       log.info("cash dispenser created");
   }
   
   /** Set the amount of cash initially on hand 
    *
    *  @param initialCash the amount of money in the dispenser
    */
   @Override
   public void setInitialCash(Money initialCash)
   {
       cashOnHand = initialCash;
   }
   
   /** See if there is enough cash on hand to satisfy a request
    *
    *  @param amount the amount of cash the customer wants
    *  @return true if at least this amount of money is available
    */
   @Override
   public boolean checkCashOnHand(Money amount)
   {
       return (amount.isLessThan(cashOnHand) || amount.isEqual(cashOnHand));
   }
   
   /** Dispense cash to a customer
    *
    *  @param amount the amount of cash to dispense
    *
    *  Precondition: amount is <= cash on hand
    */
   @Override
   public void dispenseCash(Money amount)
   {
       cashOnHand.minus(amount);
       
       GUIMediator.getInstance().dispenseCash(amount);
       
       // Log cash having been dispensed
       
       log.info(amount.toString() + " dispensed");
   }

   /** Log into which cash amounts dispensed will be recorded
    */ 
   private Logger log = Logger.getLogger("ATM Logging");
   
   /** Current cash on hand
    */
   private Money cashOnHand;
}
