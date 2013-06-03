package com.kaimbe.guidemo.io;

import java.net.InetAddress;
import java.util.logging.Logger;

import com.kaimbe.gimmedadough.atm.io.BankNetworkManager;
import com.kaimbe.gimmedadough.banking.Balances;
import com.kaimbe.gimmedadough.banking.Message;
import com.kaimbe.gimmedadough.banking.Status;

public class GUIBankNetworkManager implements BankNetworkManager {

	// Log into which to record messages

	private Logger log = Logger.getLogger("ATM Logging");

	// Network address of the bank

	private InetAddress bankAddress;

	public GUIBankNetworkManager(InetAddress bankAddress) {
		this.bankAddress = bankAddress;
		log.info("bank network manager created");
	}

	@Override
	public void openConnection() {
		// code goes here to open a connection with the bank
	}

	@Override
	public void closeConnection() {
		// code goes here to close a connection with the bank
	}

	@Override
	public Status sendMessage(Message message, Balances balances) {
		log.info("send message to bank");

		// Log sending of the message

		log.info(message.toString());

		// here is where the real code
		// to actually send the message over the network would go

		// Log the response gotten back

		// log.info(result);

		return null; // if a message was actually sent, this is where the
						// response would return
	}
}
