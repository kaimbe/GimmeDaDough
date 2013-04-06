package com.kaimbe.gimmedadough.gui.physical;

import java.util.logging.Logger;

import com.kaimbe.gimmedadough.atm.physical.CardReader;
import com.kaimbe.gimmedadough.banking.Card;
import com.kaimbe.gimmedadough.gui.GUIMediator;

public class GUICardReader implements CardReader{
	private Logger log = Logger.getLogger("ATM Logging");
	
	/** Constructor
    *
    *  
    */
   public GUICardReader()
   {
       log.info("card reader created");
   }
   
   // In a real ATM, code would be needed to sense insertion of a card into the
   // slot and notify the ATM - simulated in this case by a button in the GUI
   
   /** Read a card that has been partially inserted into the reader
    *
    *  @return Card object representing information on the card if read
    *          successfully, null if not read successfully
    */
   @Override
   public Card readCard()
   {
       return GUIMediator.getInstance().readCard();
   }
   
   /** Eject the card that is currently inside the reader.  
    */
   @Override
   public void ejectCard()
   {
	   GUIMediator.getInstance().ejectCard();
   }
   
   /** Retain the card that is currently inside the reader for action by the
    *  bank.
    */
   @Override
   public void retainCard()
   {
	   GUIMediator.getInstance().retainCard();
   }
}
