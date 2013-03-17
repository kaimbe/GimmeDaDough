package com.kaimbe.gimmedadough;

import com.kaimbe.gimmedadough.atm.ATM;
import com.kaimbe.gimmedadough.atm.ATMController;
import com.kaimbe.gimmedadough.atm.ATMInfo;
import com.kaimbe.gimmedadough.atm.physical.ATMProxy;
import com.kaimbe.gimmedadough.gui.ATMGui;
import com.kaimbe.gimmedadough.gui.GUIMediator;

public class GimmeDaDough {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Info about the ATM to be created
		ATMInfo atmInfo = new ATMInfo("01", "00001", "001");
		
		ATMProxy proxy = new ATMProxy();
		GUIMediator mediator = new GUIMediator();
		
		ATMController controller = new ATMController(proxy, mediator);
		
		ATM xerxes = new ATM(atmInfo, controller, null);
		
		ATMGui gui = new ATMGui(xerxes);
		
		mediator.setATM(xerxes);
		
		new Thread(xerxes).start();
	}
}
