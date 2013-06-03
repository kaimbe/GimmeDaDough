package com.kaimbe.guidemo.physical;

import java.util.Enumeration;
import java.util.logging.Logger;

import com.kaimbe.gimmedadough.atm.physical.ReceiptPrinter;
import com.kaimbe.gimmedadough.banking.Receipt;
import com.kaimbe.guidemo.GUIMediator;

public class GUIReceiptPrinter implements ReceiptPrinter {

	private Logger log = Logger.getLogger("ATM Logging");

	public GUIReceiptPrinter() {
		log.info("receipt printer created");
	}

	@Override
	public void printReceipt(Receipt receipt) {
		Enumeration<?> receiptLines = receipt.getLines();

		while (receiptLines.hasMoreElements()) {
			GUIMediator.getInstance().printReceiptLine(
					((String) receiptLines.nextElement()));
		}
	}
}
