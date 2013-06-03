package com.kaimbe.gimmedadough.atm.io;

import com.kaimbe.gimmedadough.banking.Balances;
import com.kaimbe.gimmedadough.banking.Message;
import com.kaimbe.gimmedadough.banking.Status;

/**
 * 
 * @author Matthew Newell
 * 
 *         interface to deal with networking between the atm and the bank.
 * 
 */
public interface BankNetworkManager {
	/**
	 * Open connection to bank at system startup
	 */
	public void openConnection();

	/**
	 * Close connection to bank at system shutdown
	 */
	public void closeConnection();

	/**
	 * Send a message to bank
	 * 
	 * @param message
	 *            the message to send
	 * @param balances
	 *            (out) balances in customer's account as reported by bank
	 * @return status code returned by bank
	 */
	public Status sendMessage(Message message, Balances balances);
}
