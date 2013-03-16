package com.kaimbe.gimmedadough.atm.physical;

import java.util.Enumeration;

import com.kaimbe.gimmedadough.atm.ATMController;
import com.kaimbe.gimmedadough.banking.Receipt;

public class ReceiptPrinter {
	/** Constructor
     */
    public ReceiptPrinter(ATMController controller)
    { 
    	this.controller = controller;
    }
    
    /** Print a receipt
     *
     *  @param receipt object containing the information to be printed
     */
    public void printReceipt(Receipt receipt)
    {
        Enumeration receiptLines = receipt.getLines();
        
        // Animate the printing of the receipt

        while (receiptLines.hasMoreElements())
        {
            controller.getMediator().printReceiptLine(
                ((String) receiptLines.nextElement()));
        }
    }
    
    private ATMController controller;
}
