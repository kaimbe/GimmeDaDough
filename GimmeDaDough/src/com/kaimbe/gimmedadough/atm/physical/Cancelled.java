package com.kaimbe.gimmedadough.atm.physical;

/**
 * Exception thrown when the user presses the cancel key while the ATM is
 * waiting for some action
 * 
 * @author Matthew Newell
 */
public class Cancelled extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 */
	public Cancelled() {
		super("Cancelled by customer");
	}
}