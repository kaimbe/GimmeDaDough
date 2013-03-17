package com.kaimbe.gimmedadough;

import com.kaimbe.gimmedadough.atm.ATM;
import com.kaimbe.gimmedadough.atm.ATMController;
import com.kaimbe.gimmedadough.atm.ATMInfo;
import com.kaimbe.gimmedadough.atm.physical.ATMProxy;
import com.kaimbe.gimmedadough.cli.CLIMediator;

public class GimmeDaDough {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Info about the ATM to be created
		ATMInfo atmInfo = new ATMInfo("01", "00001", "001");
		
		// In this case, a command line interface is used. Other implementations may be used here. i.e. GUI, or interface with physical hardware
		ATMProxy proxy = new ATMProxy();
		CLIMediator mediator = new CLIMediator();
		
		ATMController controller = new ATMController(proxy, mediator);
		
		ATM xerxes = new ATM(atmInfo, controller, null);
		
		new Thread(xerxes).start();
	}
}
