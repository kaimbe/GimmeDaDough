package com.kaimbe.gimmedadough.gui;

import java.net.InetAddress;

import com.kaimbe.gimmedadough.atm.ATM;
import com.kaimbe.gimmedadough.atm.ATMInfo;
import com.kaimbe.gimmedadough.atm.physical.ATMPanel;
import com.kaimbe.gimmedadough.gui.io.GUIBankNetworkManager;
import com.kaimbe.gimmedadough.gui.physical.*;

public class GimmeDaDough {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Info about the ATM to be created
		ATMInfo atmInfo = new ATMInfo("01", "00001", "001");
		InetAddress bankAddress = null; // TODO: comment this
		
		ATMPanel.getInstance().setBankNetworkManager(new GUIBankNetworkManager(bankAddress));
		ATMPanel.getInstance().setCardReader(new GUICardReader());
		ATMPanel.getInstance().setCashDispenser(new GUICashDispenser());
		ATMPanel.getInstance().setConsole(new GUIConsole());
		ATMPanel.getInstance().setEnvelopeReceiver(new GUIEnvelopeReceiver());
		ATMPanel.getInstance().setMaintenancePanel(new GUIMaintenancePanel());
		ATMPanel.getInstance().setReceiptPrinter(new GUIReceiptPrinter());
		
		ATM xerxes = null;
		try {
			xerxes = new ATM(atmInfo);
		} catch (NullPointerException e) {
			System.err.println("Problem starting the ATM");
		}
		
		new Thread(xerxes).start();
		
	}
}