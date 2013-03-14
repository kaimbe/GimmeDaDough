package com.kaimbe.gimmedadough.atm;

import java.net.InetAddress;

import com.kaimbe.gimmedadough.atm.physical.*;
import com.kaimbe.gimmedadough.io.*;

public class ATM implements Runnable{
	private int id;
	private int branch;
	private int institution;
	private InetAddress bankAddress;
	
	private Log log;
	private CardReader cardReader;
	private CashDispenser cashDispenser;
	private CustomerConsole customerConsole;
	private EnvelopeReceiver envelopeReceiver;
	private BankNetworkManager bankNetworkManager;
	private ControlPanel controlPanel;
	private ReceiptPrinter receiptPrinter;
	
    private int state;
    private boolean switchOn;
    private boolean cardInserted; 

    private static final int OFF_STATE = 0;
    private static final int IDLE_STATE = 1;
    private static final int SERVING_CUSTOMER_STATE = 2;
    
	public ATM(int id, int branch, int institution, InetAddress bankAddress) {
		this.id = id;
		this.branch = branch;
		this.institution = institution;
		this.bankAddress = bankAddress;
		
		// Create objects corresponding to component parts

        log = new Log();
        cardReader = new CardReader(this);
        cashDispenser = new CashDispenser(log);
        customerConsole = new CustomerConsole();
        envelopeReceiver = new EnvelopeReceiver(log);
        bankNetworkManager = new BankNetworkManager(log, bankAddress);
        controlPanel = new ControlPanel(this);
        receiptPrinter = new ReceiptPrinter();  
    
        // Set up initial conditions when ATM first created
        
        state = OFF_STATE;
        switchOn = false;
        cardInserted = false;  
	}

	@Override
	public void run() {
		Session currentSession = null;
        
        while (true)
        {
            switch(state)
            {
                case OFF_STATE:
                
                    customerConsole.display("Not currently available");

                    synchronized(this)
                    {
                        try
                        { 
                            wait();
                        }
                        catch(InterruptedException e)
                        { }
                    }
                    
                    if (switchOn)
                    {
                        performStartup();
                        state = IDLE_STATE;
                    }
                                            
                    break;
                    
                case IDLE_STATE:
                
                    customerConsole.display("Please insert your card");
                    cardInserted = false;
                                        
                    synchronized(this)
                    {
                        try
                        { 
                            wait();
                        }
                        catch(InterruptedException e)
                        { }
                    }       
                    
                    if (cardInserted)
                    {
                        currentSession = new Session(this);
                        state = SERVING_CUSTOMER_STATE;
                    }
                    else if (! switchOn)
                    {
                        performShutdown();
                        state = OFF_STATE;
                    }
                    
                    break;
            
                case SERVING_CUSTOMER_STATE:
                                    
                    // The following will not return until the session has
                    // completed
                    
                    currentSession.performSession();
                    
                    state = IDLE_STATE;
                    
                    break;
                
            }
        }
		
	}
	
    public synchronized void switchOn()
    {
        switchOn = true;
        notify();
    }
   
    public synchronized void switchOff()
    {
        switchOn = false;
        notify();
    }
   
    public synchronized void cardInserted()
    {
        cardInserted = true;
        notify();
    }

	private void performShutdown() {
		Money initialCash = controlPanel.getInitialCash();
        cashDispenser.setInitialCash(initialCash);
        bankNetworkManager.openConnection();     
	}

	private void performStartup() {
		bankNetworkManager.closeConnection();
	}
}
