package com.kaimbe.guidemo.physical;

import java.util.logging.Logger;

import org.joda.money.Money;

import com.kaimbe.gimmedadough.atm.physical.CashDispenser;
import com.kaimbe.guidemo.GUIMediator;

public class GUICashDispenser implements CashDispenser {
	private Logger log = Logger.getLogger("ATM Logging");
	private Money cashOnHand;

	public GUICashDispenser() {
		cashOnHand = Money.parse("CAD 0");
		log.info("cash dispenser created");
	}

	@Override
	public void setInitialCash(Money initialCash) {
		cashOnHand = initialCash;
	}

	@Override
	public boolean checkCashOnHand(Money amount) {
		return (amount.isLessThan(cashOnHand) || amount.isEqual(cashOnHand));
	}

	@Override
	public void dispenseCash(Money amount) {
		cashOnHand.minus(amount);

		GUIMediator.getInstance().dispenseCash(amount);

		// Log cash having been dispensed

		log.info(amount.toString() + " dispensed");
	}
}
