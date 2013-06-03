package com.kaimbe.gimmedadough.banking;

import org.joda.money.Money;

/**
 * 
 * @author Matthew Newell
 * 
 *         a message to be sent from the atm to the bank (over a network)
 */
public class Message {
	/**
	 * messageCode value for a cash withdrawal message
	 */
	public static final int WITHDRAWAL = 0;

	/**
	 * messageCode value for a message initiating a deposit
	 */
	public static final int INITIATE_DEPOSIT = 1;

	/**
	 * messageCode value for a message completing a deposit (envelope received).
	 */
	public static final int COMPLETE_DEPOSIT = 2;

	/**
	 * messageCode value for a transfer between accounts message
	 */
	public static final int TRANSFER = 3;

	/**
	 * messageCode value for an inquiry message
	 */
	public static final int INQUIRY = 4;

	/**
	 * Code identifying the type of message - one of the above values
	 */
	private int messageCode;

	/**
	 * The customer's card
	 */
	private Card card;

	/**
	 * PIN entered by customer
	 */
	private int pin;

	/**
	 * Serial number of the transaction
	 */
	private int serialNumber;

	/**
	 * "From" account specified by customer - if needed (else - 1)
	 */
	private int fromAccount;

	/**
	 * "To" account specified by customer - if needed (else - 1)
	 */
	private int toAccount;

	/**
	 * Transaction amount specified by customer - if needed (else $0.00)
	 */
	private Money amount;

	/**
	 * Constructor
	 * 
	 * @param messageCode
	 *            identifying the type of message
	 * @param card
	 *            the customer's card
	 * @param pin
	 *            the PIN entered by the customer
	 * @param serialNumber
	 *            serial number of the transaction
	 * @param fromAccount
	 *            the type of the "from" account of the transaction - can be -1
	 *            if the particular type of transaction does not have a "from"
	 *            account (e.g. a deposit)
	 * @param toAccount
	 *            the type of the "to" account of the transaction - can be -1 if
	 *            the particular type of transaction does not have a "to"
	 *            account (e.g. a withdrawal)
	 * @param amount
	 *            the amount of the transaction - can be null if the particular
	 *            type of transaction does not have an amount (e.g. an inquiry)
	 */
	public Message(int messageCode, Card card, int pin, int serialNumber,
			int fromAccount, int toAccount, Money amount) {
		this.messageCode = messageCode;
		this.card = card;
		this.pin = pin;
		this.serialNumber = serialNumber;
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.amount = amount;
	}

	/**
	 * Create a printable string representing this message
	 * 
	 * @return string representation
	 */
	public String toString() {
		String result = "";

		switch (messageCode) {
		case WITHDRAWAL:

			result += "WITHDRAW";
			break;

		case INITIATE_DEPOSIT:

			result += "INIT_DEP";
			break;

		case COMPLETE_DEPOSIT:

			result += "COMP_DEP";
			break;

		case TRANSFER:

			result += "TRANSFER";
			break;

		case INQUIRY:

			result += "INQUIRY ";
			break;
		}

		result += " CARD# " + card.getNumber();
		result += " TRANS# " + serialNumber;
		if (fromAccount >= 0)
			result += " FROM  " + fromAccount;
		else
			result += " NO FROM";
		if (toAccount >= 0)
			result += " TO  " + toAccount;
		else
			result += " NO TO";
		if (!(amount.isLessThan(Money.parse("CAD 0")) || amount.isEqual(Money
				.parse("CAD 0"))))
			result += " " + amount;
		else
			result += " NO AMOUNT";

		return result;
	}

	/**
	 * Set PIN to a new value (if original pin was invalid and customer is asked
	 * to re-enter PIN)
	 * 
	 * @param pin
	 *            the new pin to set
	 */
	public void setPIN(int pin) {
		this.pin = pin;
	}

	/**
	 * Accessor for message code
	 * 
	 * @return the code identifying the type of message
	 */
	public int getMessageCode() {
		return messageCode;
	}

	/**
	 * Accessor for customer's card
	 * 
	 * @return the customer's card
	 */
	public Card getCard() {
		return card;
	}

	/**
	 * Accessor for PIN
	 * 
	 * @return the PIN entered by the customer
	 */
	public int getPIN() {
		return pin;
	}

	/**
	 * Accessor for transaction serial number
	 * 
	 * @return the serial number of the transaction
	 */
	public int getSerialNumber() {
		return serialNumber;
	}

	/**
	 * Accessor for "from" account
	 * 
	 * @return the type of the "from" account
	 */
	public int getFromAccount() {
		return fromAccount;
	}

	/**
	 * Accessor for "to" account
	 * 
	 * @return the type of the "to" account
	 */
	public int getToAccount() {
		return toAccount;
	}

	/**
	 * Accessor for acmount
	 * 
	 * @return the transaction amount
	 */
	public Money getAmount() {
		return amount;
	}
}
