package com.kaimbe.gimmedadough.atm;

import com.kaimbe.gimmedadough.atm.physical.ATMPanel;
import com.kaimbe.gimmedadough.atm.physical.Cancelled;
import com.kaimbe.gimmedadough.atm.transaction.Transaction;
import com.kaimbe.gimmedadough.banking.Card;

public class Session {
	private int pin;
	private int state;
	
	private static final int READING_CARD_STATE = 1;
	private static final int READING_PIN_STATE = 2;
	private static final int CHOOSING_TRANSACTION_STATE = 3;
	private static final int PERFORMING_TRANSACTION_STATE = 4;
	private static final int EJECTING_CARD_STATE = 5;
	private static final int FINAL_STATE = 6;
	

	 /** Constructor
    *
    *  @param atm the ATM on which the session is performed
    */
   public Session()
   {
       state = READING_CARD_STATE;
   }

   /** Perform the Session Use Case
    */
   public void performSession()
   {
       Card card = null;
       Transaction currentTransaction = null;
       
       while (state != FINAL_STATE)
       {
           switch(state)
           {
               case READING_CARD_STATE:
               
                   card = ATMPanel.getInstance().getCardReader().readCard();
                   
                   if (card != null)
                       state = READING_PIN_STATE;
                   else
                   {
                       ATMPanel.getInstance().getConsole().display("Unable to read card");
                       state = EJECTING_CARD_STATE;
                   }
                   break;
                   
               case READING_PIN_STATE:
               
                   try
                   {
                       pin = ATMPanel.getInstance().getConsole().readPIN(
                           "Please enter your PIN\n" +
                           "Then press ENTER");
                       state = CHOOSING_TRANSACTION_STATE;
                   }
                   catch(Cancelled e)
                   {
                       state = EJECTING_CARD_STATE;
                   }
                   break;
               
               case CHOOSING_TRANSACTION_STATE:
               
                   try
                   {
                       currentTransaction = 
                           Transaction.makeTransaction(this, card, pin);
                       state = PERFORMING_TRANSACTION_STATE;
                   }
                   catch(Cancelled e)
                   {
                       state = EJECTING_CARD_STATE;
                   }
                   break;
                   
               case PERFORMING_TRANSACTION_STATE:
               
                   try
                   {
                       boolean doAgain = 
                           currentTransaction.performTransaction();
                       if (doAgain)
                           state = CHOOSING_TRANSACTION_STATE;
                       else
                           state = EJECTING_CARD_STATE;
                   }
                   catch(Transaction.CardRetained e)
                   {
                       state = FINAL_STATE;
                   }
                   break;
                   
               case EJECTING_CARD_STATE:
               
            	   ATMPanel.getInstance().getCardReader().ejectCard();
                   state = FINAL_STATE;
                   break;
           }
       }
   }
   
   /** Change the pin recorded for the customer (if invalid pin extension
    *  was performed by a transaction
    *
    *  @param pin the newly entered pin
    */
   public void setPIN(int pin)
   {
       this.pin = pin;
   }
}
