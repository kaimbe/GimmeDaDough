package com.kaimbe.guidemo;

import java.net.InetAddress;

import com.kaimbe.gimmedadough.atm.ATM;
import com.kaimbe.gimmedadough.atm.ATMInformation;
import com.kaimbe.gimmedadough.atm.physical.ATMPanel;
import com.kaimbe.guidemo.io.GUIBankNetworkManager;
import com.kaimbe.guidemo.physical.GUICardReader;
import com.kaimbe.guidemo.physical.GUICashDispenser;
import com.kaimbe.guidemo.physical.GUIConsole;
import com.kaimbe.guidemo.physical.GUIEnvelopeReceiver;
import com.kaimbe.guidemo.physical.GUIMaintenancePanel;
import com.kaimbe.guidemo.physical.GUIReceiptPrinter;

public class GimmeDaDough {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// create and start the back end

		// Info about the ATM to be created
		ATMInformation atmInfo = new ATMInformation("01", "00001", "001");
		InetAddress bankAddress = null; // TODO: comment this

		ATMPanel.getInstance().setBankNetworkManager(
				new GUIBankNetworkManager(bankAddress));
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

		// create and start the front end

		/* Set the Nimbus look and feel */
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the
		 * default look and feel. For details see
		 * http://download.oracle.com/javase
		 * /tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainFrame().setVisible(true);
			}
		});
	}
}