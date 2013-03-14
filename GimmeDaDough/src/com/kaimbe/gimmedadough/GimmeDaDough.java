package com.kaimbe.gimmedadough;

import com.kaimbe.gimmedadough.atm.ATM;
import com.kaimbe.gimmedadough.cli.CommandLineInterface;

public class GimmeDaDough {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ATM xerxes = new ATM(01, 00001, 001, null);
		
		CommandLineInterface cli = new CommandLineInterface(xerxes);
		
		new Thread(xerxes).start();
	}
}
