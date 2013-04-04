package com.kaimbe.gimmedadough.atm.physical;

import org.joda.money.Money;

public interface CashDispenser {
	
	/** Set the amount of cash initially on hand 
    *
    *  @param initialCash the amount of money in the dispenser
    */
   public void setInitialCash(Money initialCash);
   
   /** See if there is enough cash on hand to satisfy a request
   *
   *  @param amount the amount of cash the customer wants
   *  @return true if at least this amount of money is available
   */
  public boolean checkCashOnHand(Money amount);
  
  /** Dispense cash to a customer
  *
  *  @param amount the amount of cash to dispense
  *
  *  Precondition: amount is <= cash on hand
  */
 public void dispenseCash(Money amount);

}
