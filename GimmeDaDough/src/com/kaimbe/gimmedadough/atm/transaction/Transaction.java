package com.kaimbe.gimmedadough.atm.transaction;

import com.kaimbe.gimmedadough.atm.ATM;
import com.kaimbe.gimmedadough.atm.Session;
import com.kaimbe.gimmedadough.atm.physical.ATMPanel;
import com.kaimbe.gimmedadough.atm.physical.Cancelled;
import com.kaimbe.gimmedadough.banking.Balances;
import com.kaimbe.gimmedadough.banking.Card;
import com.kaimbe.gimmedadough.banking.Message;
import com.kaimbe.gimmedadough.banking.Receipt;
import com.kaimbe.gimmedadough.banking.Status;

/**
 * 
 * @author Matthew Newell 
 * 
 * a transaction
 */
public abstract class Transaction {
	/**
	 * ATM to use for communication with the customer
	 */
	protected ATM atm;

	/**
	 * Session in which this transaction is being performed
	 */
	protected Session session;

	/**
	 * Customer card for the session this transaction is part of
	 */
	protected Card card;

	/**
	 * PIN entered or re-entered by customer
	 */
	protected int pin;

	/**
	 * Serial number of this transaction
	 */
	protected int serialNumber;

	/**
	 * Message to bank describing this transaction
	 */
	protected Message message;

	/**
	 * Used to return account balances from the bank
	 */
	protected Balances balances;

	/**
	 * List of available transaction types to display as a menu
	 */
	private static final String[] TRANSACTION_TYPES_MENU = { "Withdrawal",
			"Deposit", "Transfer", "Balance Inquiry" };

	/**
	 * Next serial number - used to assign a unique serial number to each
	 * transaction
	 */
	private static int nextSerialNumber = 1;

	/**
	 * The current state of the transaction
	 */
	private int state;

	/**
	 * Getting specifics of the transaction from customer
	 */
	private static final int GETTING_SPECIFICS_STATE = 1;

	/**
	 * Sending transaction to bank
	 */
	private static final int SENDING_TO_BANK_STATE = 2;

	/**
	 * Performing invalid PIN extension
	 */
	private static final int INVALID_PIN_STATE = 3;

	/**
	 * Completing transaction
	 */
	private static final int COMPLETING_TRANSACTION_STATE = 4;

	/**
	 * Printing receipt
	 */
	private static final int PRINTING_RECEIPT_STATE = 5;

	/**
	 * Asking if customer wants to do another transaction
	 */
	private static final int ASKING_DO_ANOTHER_STATE = 6;

	/**
	 * Constructor
	 * 
	 * @param atm
	 *            the ATM used to communicate with customer
	 * @param session
	 *            the session in which this transaction is being performed
	 * @param card
	 *            the customer's card
	 * @param pin
	 *            the PIN entered by the customer
	 */
	protected Transaction(Session session, Card card, int pin) {
		this.session = session;
		this.card = card;
		this.pin = pin;
		this.serialNumber = nextSerialNumber++;
		this.balances = new Balances();

		state = GETTING_SPECIFICS_STATE;
	}

	/**
	 * Create a transaction of an appropriate type by asking the customer what
	 * type of transaction is desired and then returning a newly-created member
	 * of the appropriate subclass
	 * 
	 * @param atm
	 *            the ATM used to communicate with customer
	 * @param session
	 *            the session in which this transaction is being performed
	 * @param card
	 *            the customer's card
	 * @param pin
	 *            the PIN entered by the customer
	 * @return a newly created Transaction object of the appropriate type
	 * @exception Cancelled
	 *                if the customer presses cancel instead of choosing a
	 *                transaction type
	 */
	public static Transaction makeTransaction(Session session, Card card,
			int pin) throws Cancelled {
		int choice = ATMPanel
				.getInstance()
				.getConsole()
				.readMenuChoice("Please choose transaction type",
						TRANSACTION_TYPES_MENU);

		switch (choice) {
		case 0:

			return new Withdrawal(session, card, pin);

		case 1:

			return new Deposit(session, card, pin);

		case 2:

			return new Transfer(session, card, pin);

		case 3:

			return new Inquiry(session, card, pin);

		default:

			return null;
		}
	}

	/**
	 * Peform a transaction. This method depends on the three abstract methods
	 * that follow to perform the operations unique to each type of transaction
	 * in the appropriate way.
	 * 
	 * @return true if customer indicates a desire to do another transaction;
	 *         false if customer does not desire to do another transaction
	 * @exception CardRetained
	 *                if card was retained due to too many invalid PIN's
	 */
	public boolean performTransaction() throws CardRetained {
		String doAnotherMessage = "";
		Status status = null;
		Receipt receipt = null;

		while (true) // Terminates by return in ASKING_DO_ANOTHER_STATE or
						// exception
		{
			switch (state) {
			case GETTING_SPECIFICS_STATE:

				try {
					message = getSpecificsFromCustomer();
					ATMPanel.getInstance().getConsole().display("");
					state = SENDING_TO_BANK_STATE;
				} catch (Cancelled e) {
					doAnotherMessage = "Last transaction was cancelled";
					state = ASKING_DO_ANOTHER_STATE;
				}

				break;

			case SENDING_TO_BANK_STATE:

				status = ATMPanel.getInstance().getBankNetworkManager()
						.sendMessage(message, balances);

				if (status.isInvalidPIN())
					state = INVALID_PIN_STATE;
				else if (status.isSuccess())
					state = COMPLETING_TRANSACTION_STATE;
				else {
					doAnotherMessage = status.getMessage();
					state = ASKING_DO_ANOTHER_STATE;
				}

				break;

			case INVALID_PIN_STATE:

				try {
					status = performInvalidPINExtension();

					// If customer repeatedly enters invalid PIN's, a
					// CardRetained exception is thrown, and this method
					// terminates

					if (status.isSuccess())
						state = COMPLETING_TRANSACTION_STATE;
					else {
						doAnotherMessage = status.getMessage();
						state = ASKING_DO_ANOTHER_STATE;
					}
				} catch (Cancelled e) {
					doAnotherMessage = "Last transaction was cancelled";
					state = ASKING_DO_ANOTHER_STATE;
				}

				break;

			case COMPLETING_TRANSACTION_STATE:

				try {
					receipt = completeTransaction();
					state = PRINTING_RECEIPT_STATE;
				} catch (Cancelled e) {
					doAnotherMessage = "Last transaction was cancelled";
					state = ASKING_DO_ANOTHER_STATE;
				}

				break;

			case PRINTING_RECEIPT_STATE:

				ATMPanel.getInstance().getReceiptPrinter()
						.printReceipt(receipt);
				state = ASKING_DO_ANOTHER_STATE;

				break;

			case ASKING_DO_ANOTHER_STATE:

				if (doAnotherMessage.length() > 0)
					doAnotherMessage += "\n";

				try {
					String[] yesNoMenu = { "Yes", "No" };

					boolean doAgain = ATMPanel
							.getInstance()
							.getConsole()
							.readMenuChoice(
									doAnotherMessage
											+ "Would you like to do another transaction?",
									yesNoMenu) == 0;
					return doAgain;
				} catch (Cancelled e) {
					return false;
				}
			}
		}
	}

	/**
	 * Perform the Invalid PIN Extension - reset session pin to new value if
	 * successful
	 * 
	 * @return status code returned by bank from most recent re-submission of
	 *         transaction
	 * @exception Cancelled
	 *                if customer presses the CANCEL key instead of re-entering
	 *                PIN
	 * @exception CardRetained
	 *                if card was retained due to too many invalid PIN's
	 */
	public Status performInvalidPINExtension() throws Cancelled, CardRetained {
		Status status = null;
		for (int i = 0; i < 3; i++) {
			pin = ATMPanel
					.getInstance()
					.getConsole()
					.readPIN(
							"PIN was incorrect\nPlease re-enter your PIN\n"
									+ "Then press ENTER");
			ATMPanel.getInstance().getConsole().display("");

			message.setPIN(pin);
			status = ATMPanel.getInstance().getBankNetworkManager()
					.sendMessage(message, balances);
			if (!status.isInvalidPIN()) {
				session.setPIN(pin);
				return status;
			}
		}

		ATMPanel.getInstance().getCardReader().retainCard();
		ATMPanel.getInstance()
				.getConsole()
				.display(
						"Your card has been retained\nPlease contact the bank.");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		}
		ATMPanel.getInstance().getConsole().display("");

		throw new CardRetained();
	}

	/**
	 * Get serial number of this transaction
	 * 
	 * @return serial number
	 */
	public int getSerialNumber() {
		return serialNumber;
	}

	/**
	 * Get specifics for the transaction from the customer - each subclass must
	 * implement this appropriately.
	 * 
	 * @return message to bank for initiating this transaction
	 * @exception Cancelled
	 *                if customer cancelled this transaction
	 */
	protected abstract Message getSpecificsFromCustomer() throws Cancelled;

	/**
	 * Complete an approved transaction - each subclass must implement this
	 * appropriately.
	 * 
	 * @return receipt to be printed for this transaction
	 * @exception Cancelled
	 *                if customer cancelled this transaction
	 */
	protected abstract Receipt completeTransaction() throws Cancelled;

	// Local class representing card retained exception

	/**
	 * Exception that is thrown when the customer's card is retained due to too
	 * many invalid PIN entries
	 * 
	 * @author Matthew Newell
	 */
	public static class CardRetained extends Exception {

		/**
	 * 
	 */
		private static final long serialVersionUID = 1L;

		/**
		 * Constructor
		 */
		public CardRetained() {
			super("Card retained due to too many invalid PINs");
		}
	}
}
