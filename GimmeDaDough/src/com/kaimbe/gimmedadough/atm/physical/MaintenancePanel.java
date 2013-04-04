package com.kaimbe.gimmedadough.atm.physical;

import org.joda.money.Money;

public interface MaintenancePanel {
	 /** Get the amount of cash in the cash dispenser from the operator at start up
    *
    *  @return dollar value of the bills in the cash dispenser (# of bills x $20)
    */
   
   public Money getInitialCash();
}
