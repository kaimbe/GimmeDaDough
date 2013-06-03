package com.kaimbe.guidemo.physical;

import java.util.logging.Logger;

import org.joda.money.Money;

import com.kaimbe.gimmedadough.atm.physical.MaintenancePanel;
import com.kaimbe.guidemo.GUIMediator;

public class GUIMaintenancePanel implements MaintenancePanel {

	private Logger log = Logger.getLogger("ATM Logging");

	public GUIMaintenancePanel() {
		log.info("maintenance panel created");
	}

	@Override
	public Money getInitialCash() {
		return GUIMediator.getInstance().getInitialCash();
	}
}
