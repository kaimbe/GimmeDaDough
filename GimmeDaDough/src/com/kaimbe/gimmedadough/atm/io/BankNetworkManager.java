package com.kaimbe.gimmedadough.atm.io;

import java.net.InetAddress;
import java.util.logging.Logger;

import com.kaimbe.gimmedadough.banking.Balances;
import com.kaimbe.gimmedadough.banking.Message;
import com.kaimbe.gimmedadough.banking.Status;

public class BankNetworkManager {

	/** Constructor
    *
    *  @param log the log in which to record sending of messages and responses
    *  @param bankAddress the network address of the bank
    */
   public BankNetworkManager(Logger log, InetAddress bankAddress)
   {
       this.log = log;
       this.bankAddress = bankAddress;
   }
   
   /** Open connection to bank at system startup
    */
   public void openConnection()
   {
       // Since the network is simulated, we don't have to do anything
   }
   
   /** Close connection to bank at system shutdown
    */
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
   public Status sendMessage(Message message, Balances balances)
   {
	return null;
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
   
   private Logger log;
   
   // Network address of the bank
   
   private InetAddress bankAddress;

}
