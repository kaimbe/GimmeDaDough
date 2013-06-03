package com.kaimbe.gimmedadough.atm.physical;

/**
 * 
 * @author Matthew Newell 
 * 
 * interface that represents an envelope receiver
 */
public interface EnvelopeReceiver {
	/**
	 * Accept an envelope from customer.
	 * 
	 * @exception Cancelled
	 *                if operation timed out or the customer cancelled it
	 */
	public void acceptEnvelope() throws Cancelled;
}
