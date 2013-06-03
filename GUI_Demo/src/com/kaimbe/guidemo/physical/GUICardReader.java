package com.kaimbe.guidemo.physical;

import java.util.logging.Logger;

import com.kaimbe.gimmedadough.atm.physical.CardReader;
import com.kaimbe.gimmedadough.banking.Card;
import com.kaimbe.guidemo.GUIMediator;

public class GUICardReader implements CardReader {
	private Logger log = Logger.getLogger("ATM Logging");

	public GUICardReader() {
		log.info("card reader created");
	}

	@Override
	public Card readCard() {
		return GUIMediator.getInstance().readCard();
	}

	@Override
	public void ejectCard() {
		GUIMediator.getInstance().ejectCard();
	}

	@Override
	public void retainCard() {
		GUIMediator.getInstance().retainCard();
	}
}
