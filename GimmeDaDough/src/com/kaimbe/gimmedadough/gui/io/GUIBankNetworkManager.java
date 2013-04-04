package com.kaimbe.gimmedadough.gui.io;

import java.net.InetAddress;
import java.util.logging.Logger;

import com.kaimbe.gimmedadough.atm.io.BankNetworkManager;
import com.kaimbe.gimmedadough.banking.Balances;
import com.kaimbe.gimmedadough.banking.Message;
import com.kaimbe.gimmedadough.banking.Status;

public class GUIBankNetworkManager implements BankNetworkManager{

	/** Constructor
    *
    *  @param log the log in which to record sending of messages and responses
    *  @param bankAddress the network address of the bank
    */
   public GUIBankNetworkManager(InetAddress bankAddress)
   {
       this.bankAddress = bankAddress;
   }
   
   /** Open connection to bank at system startup
    */
   @Override
   public void openConnection()
   {
       // Since the network is simulated, we don't have to do anything
   }
   
   /** Close connection to bank at system shutdown
    */
   @Override
   public void closeConnection()
   {
       // Since the network is simulated, we don't have to do anything
   }
   
   /** Send a message to bank
    *
    *  @param message the message to send
    *  @param balances (out) balances in customer's account as reported
    *         by bank
    *  @return status code returned by bank
    */
   @Override
   public Status sendMessage(Message message, Balances balances)
   {
	   log.info("send message to bank");
	return null; //TODO: Fix this
       // Log sending of the message
       
       //log.logSend(message);
       
       // Simulate the sending of the message - here is where the real code
       // to actually send the message over the network would go
       
       //Status result = Simulation.getInstance().sendMessage(message, balances);
       
       // Log the response gotten back
       
      // log.logResponse(result);
       
       //return result;
   }
   
   // Log into which to record messages
   
   private Logger log = Logger.getLogger("ATM Logging");
   
   // Network address of the bank
   
   private InetAddress bankAddress;

}
