package com.kaimbe.gimmedadough.atm.physical;

import com.kaimbe.gimmedadough.atm.ATM;
import com.kaimbe.gimmedadough.atm.ATMController;
import com.kaimbe.gimmedadough.banking.Card;

public class CardReader {
	/** Constructor
    *
    *  @param atm the ATM that owns this card reader
    */
   public CardReader(ATM atm, ATMController controller)
   {
       this.atm = atm;
       this.controller = controller;
   }
   
   // In a real ATM, code would be needed to sense insertion of a card into the
   // slot and notify the ATM - simulated in this case by a button in the GUI
   
   /** Read a card that has been partially inserted into the reader
    *
    *  @return Card object representing information on the card if read
    *          successfully, null if not read successfully
    */
   public Card readCard()
   {
       return controller.getMediator().readCard();
   }
   
   /** Eject the card that is currently inside the reader.  
    */
   public void ejectCard()
   {
	   controller.getMediator().ejectCard();
   }
   
   /** Retain the card that is currently inside the reader for action by the
    *  bank.
    */
   public void retainCard()
   {
	   controller.getMediator().retainCard();
   }
   
   /** The ATM to which this card reader belongs
    */
   private ATM atm;
   
   private ATMController controller;

}
