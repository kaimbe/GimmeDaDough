package com.kaimbe.gimmedadough.gui;

import com.kaimbe.gimmedadough.atm.ATM;

public class ATMGui {
	
	private ATM atm;
	
	public ATMGui(ATM xerxes) {
		new MainFrame();
	}

	public void setATM(ATM atm) {
		this.atm = atm;
	}

}
