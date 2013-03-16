package com.kaimbe.gimmedadough.atm.physical;

import org.joda.money.Money;

import com.kaimbe.gimmedadough.atm.ATM;
import com.kaimbe.gimmedadough.atm.ATMController;

public class ManagementPanel {

	/** Constructor
    *
    *  @param atm the ATM this panel is part of
    */
   public ManagementPanel(ATM atm, ATMController controller)
   {
       this.atm = atm;
       this.controller = controller;
   }
   
   // In a real ATM, code would be needed to sense a change in the state of the
   // switch and notify the ATM - simulated in this case by a button in the GUI
   
   /** Get the amount of cash in the cash dispenser from the operator at start up
    *
    *  @return dollar value of the bills in the cash dispenser (# of bills x $20)
    */
   
   public Money getInitialCash()
   {
       return controller.getMediator().getInitialCash();
   }
   
   /** ATM this panel is part of.  The ATM object will be notified when
    * the switch on the panel is turned on or off
    */
   private ATM atm;
   
   private ATMController controller;

}
