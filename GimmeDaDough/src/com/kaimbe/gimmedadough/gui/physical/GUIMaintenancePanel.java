package com.kaimbe.gimmedadough.gui.physical;

import org.joda.money.Money;

import com.kaimbe.gimmedadough.atm.physical.MaintenancePanel;
import com.kaimbe.gimmedadough.gui.GUIMediator;

public class GUIMaintenancePanel implements MaintenancePanel{

	/** Constructor
    *
    *  @param atm the ATM this panel is part of
    */
   public GUIMaintenancePanel()
   {
       
   }
   
   // In a real ATM, code would be needed to sense a change in the state of the
   // switch and notify the ATM - simulated in this case by a button in the GUI
   
   /** Get the amount of cash in the cash dispenser from the operator at start up
    *
    *  @return dollar value of the bills in the cash dispenser (# of bills x $20)
    */
   @Override
   public Money getInitialCash()
   {
       return GUIMediator.getInstance().getInitialCash();
   }
}
