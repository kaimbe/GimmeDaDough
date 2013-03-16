package com.kaimbe.gimmedadough.atm.physical;

import java.util.logging.Logger;

import org.joda.money.Money;

public class CashDispenser {

	 /** Constructor
    *
    *  @param log the log in which to record dispensing cash
    */
   public CashDispenser(Logger log)
   {
       this.log = log;
       
       //cashOnHand = new Money(0);
   }
   
   /** Set the amount of cash initially on hand 
    *
    *  @param initialCash the amount of money in the dispenser
    */
   public void setInitialCash(Money initialCash)
   {
       cashOnHand = initialCash;
   }
   
   /** See if there is enough cash on hand to satisfy a request
    *
    *  @param amount the amount of cash the customer wants
    *  @return true if at least this amount of money is available
    */
   public boolean checkCashOnHand(Money amount)
   {
	return false;
       //return amount.lessEqual(cashOnHand);
   }
   
   /** Dispense cash to a customer
    *
    *  @param amount the amount of cash to dispense
    *
    *  Precondition: amount is <= cash on hand
    */
   public void dispenseCash(Money amount)
   {
       //cashOnHand.subtract(amount);
       
       //Simulation.getInstance().dispenseCash(amount);
       
       // Log cash having been dispensed
       
       //log.logCashDispensed(amount);
   }

   /** Log into which cash amounts dispensed will be recorded
    */ 
   private Logger log;
   
   /** Current cash on hand
    */
   private Money cashOnHand;

}
