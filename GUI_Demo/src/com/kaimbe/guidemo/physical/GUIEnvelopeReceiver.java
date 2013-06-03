package com.kaimbe.guidemo.physical;

import java.util.logging.Logger;

import com.kaimbe.gimmedadough.atm.physical.Cancelled;
import com.kaimbe.gimmedadough.atm.physical.EnvelopeReceiver;
import com.kaimbe.guidemo.GUIMediator;

public class GUIEnvelopeReceiver implements EnvelopeReceiver {
	private Logger log = Logger.getLogger("ATM Logging");

	public GUIEnvelopeReceiver() {

	}

	@Override
	public void acceptEnvelope() throws Cancelled {
		boolean inserted = GUIMediator.getInstance().acceptEnvelope();
		if (inserted)
			log.info("Envelope inserted");
		else
			throw new Cancelled();
	}

}
