package com.kaimbe.gimmedadough.atm;


public class ATMController {
	private static ATMController instance = new ATMController();
	private ATM atm;
	
	private ATMController() {
		// Singleton design pattern
	}
	
	public static synchronized ATMController getInstance() {
		return instance;
	}
	

	public void setATM(final ATM atm) {
		this.atm = atm;
	}
	
	
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException("Clone is not allowed.");
	}

	
	public void switchOn() {
		atm.switchOn();
	}

	
	public void switchOff() {
		atm.switchOff();
	}

	
	public void cardInserted() {
		atm.cardInserted();
	}
}
