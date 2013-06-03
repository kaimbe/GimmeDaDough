package com.kaimbe.gimmedadough.atm;

/**
 * 
 * @author Matthew Newell controls an atm. uses the singleton design pattern.
 */
public class ATMController {
	/**
	 * the one and only instance
	 */
	private static ATMController instance = new ATMController();
	/**
	 * the atm
	 */
	private ATM atm;

	/**
	 * constructor
	 */
	private ATMController() {
		// Singleton design pattern
	}

	/**
	 * 
	 * @return the one and only instance
	 */
	public static synchronized ATMController getInstance() {
		return instance;
	}

	/**
	 * 
	 * @param atm
	 *            the atm to set
	 */
	public void setATM(final ATM atm) {
		this.atm = atm;
	}

	/**
	 * prevent cloning
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException("Clone is not allowed.");
	}

	/**
	 * turn the atm on
	 */
	public void switchOn() {
		atm.switchOn();
	}

	/**
	 * turn the atm off
	 */
	public void switchOff() {
		atm.switchOff();
	}

	/**
	 * tell the atm that a card has been inserted
	 */
	public void cardInserted() {
		atm.cardInserted();
	}
}
