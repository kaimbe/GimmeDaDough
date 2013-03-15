package com.kaimbe.gimmedadough;

import com.kaimbe.gimmedadough.atm.ATM;
import com.kaimbe.gimmedadough.atm.ATMController;
import com.kaimbe.gimmedadough.mediation.ATMProxy;
import com.kaimbe.gimmedadough.mediation.CLIMediator;

public class GimmeDaDough {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// In this case, a command line interface is used. Other implementations may be used here. i.e. GUI, or interface with physical hardware
		ATMProxy proxy = new ATMProxy();
		CLIMediator mediator = new CLIMediator();
		
		ATMController controller = new ATMController(proxy, mediator);
		
		ATM xerxes = new ATM(01, 00001, 001, null, controller);
		
		new Thread(xerxes).start();
	}
}
