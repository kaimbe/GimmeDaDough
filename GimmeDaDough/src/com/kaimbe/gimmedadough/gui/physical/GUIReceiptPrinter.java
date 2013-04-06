package com.kaimbe.gimmedadough.gui.physical;

import java.util.Enumeration;
import java.util.logging.Logger;

import com.kaimbe.gimmedadough.atm.physical.ReceiptPrinter;
import com.kaimbe.gimmedadough.banking.Receipt;
import com.kaimbe.gimmedadough.gui.GUIMediator;

public class GUIReceiptPrinter implements ReceiptPrinter{
	
	private Logger log = Logger.getLogger("ATM Logging");
	/** Constructor
     */
    public GUIReceiptPrinter()
    { 
    	log.info("receipt printer created");
    }
    
    /** Print a receipt
     *
     *  @param receipt object containing the information to be printed
     */
    @Override
    public void printReceipt(Receipt receipt)
    {
        Enumeration<?> receiptLines = receipt.getLines();
        
        // Animate the printing of the receipt

        while (receiptLines.hasMoreElements())
        {
            GUIMediator.getInstance().printReceiptLine(
                ((String) receiptLines.nextElement()));
        }
    }
}
