package com.kaimbe.gimmedadough.banking;

import java.util.Date;
import java.util.Enumeration;

import com.kaimbe.gimmedadough.atm.ATM;
import com.kaimbe.gimmedadough.atm.transaction.Transaction;

public abstract class Receipt {
	/** Constructor.  This base class constructor will create the portions of the
     *  receipt that are common to all types of transaction, and the subclass
     *  constructor will create the details portion unique to each type.
     *
     *  @param atm the ATM where the transaction was done
     *  @param card the card that was used
     *  @param transaction the Transaction object for the transaction
     *  @param balances Balances object giving final balances for account used
     */
     
    protected Receipt(ATM atm, Card card, Transaction transaction, Balances balances)
    {
    	Integer institution = (Integer) atm.getInstitution();
    	Integer branch = (Integer) atm.getBranch();
        // Heading portion of the receipt
        
        headingPortion = new String[4];
        headingPortion[0] = new Date().toString();
        headingPortion[1] = institution.toString();
        headingPortion[2] = "ATM #" + atm.getId() + " " + branch.toString();
        headingPortion[3] = "CARD " + card.getNumber() + 
                     " TRANS #" + transaction.getSerialNumber();

        // The constructor for each subclass will fill in the details array
        // appropriately
        
        // Balances portion of the receipt
        
        balancesPortion = new String[2];
        balancesPortion[0] = "TOTAL BAL: " + balances.getTotal();
        balancesPortion[1] = "AVAILABLE: " + balances.getAvailable();
    }

    /** Get the individual lines to be printed.  Each call to the nextElement()
     *  of the enumeration gets one line (as a String)
     */
     
    public Enumeration getLines()
    {
        return new Enumeration() {
        
            // The current portion of the receipt being printed
            
            private int portion = 0;
            
            // The index of the line in the current portion
            
            private int index = 0;
            
            public boolean hasMoreElements()
            {
                return portion < 2 || index < balancesPortion.length;
            }
            
            public Object nextElement()
            {
                String line = null;
                
                switch (portion)
                {
                    case 0:
                    
                        line = headingPortion[index ++];
                        if (index >= headingPortion.length)
                        {
                            portion ++;
                            index = 0;
                        }
                        break;
                    
                    case 1:
                    
                        line = detailsPortion[index ++];
                        if (index >= detailsPortion.length)
                        {
                            portion ++;
                            index = 0;
                        }
                        break;
                        
                    case 2:
                    
                        line = balancesPortion[index ++];
                        break;
                }
                return line;
            }
        };
    }


    // Instance variables


    /** Heading portion of the receipt - common to all forms of receipt
     */
    private String [] headingPortion;
    
    /** Transaction details portion of the receipt - specific to each type of
     *  transaction, and therefore filled in by subclasses
     */
    protected String [] detailsPortion;
    
    /** Ending balances portion of the receipt - common to all forms of receipt
     */
    private String [] balancesPortion;  
}
