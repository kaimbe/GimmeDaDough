package com.kaimbe.gimmedadough.cli;

import com.kaimbe.gimmedadough.atm.ATM;

public class CommandLineInterface {

	public CommandLineInterface(ATM atm) {
		System.out.println("Welcome to one of" + atm.getATMInfo().getInstitution() + "'s ATM's! Select an option to begin: ");
		System.out.println("1) Turn ATM on");
	}
}
