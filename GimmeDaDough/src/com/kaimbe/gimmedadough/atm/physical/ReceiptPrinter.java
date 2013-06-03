package com.kaimbe.gimmedadough.atm.physical;

import com.kaimbe.gimmedadough.banking.Receipt;

/**
 * 
 * @author Matthew Newell
 * 
 *         interface that represnets a receipt printer
 * 
 */
public interface ReceiptPrinter {
	/**
	 * Print a receipt
	 * 
	 * @param receipt
	 *            object containing the information to be printed
	 */
	public void printReceipt(Receipt receipt);

}
