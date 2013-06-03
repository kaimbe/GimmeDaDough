package com.kaimbe.gimmedadough.atm;

import org.joda.money.Money;

import com.kaimbe.gimmedadough.banking.Card;

/**
 * 
 * @author Matthew Newell
 * 
 *         interface to deal with mediation between the the model and the view
 * 
 */
public interface Mediator {
	/**
	 * 
	 * @return the amount of initial cash
	 */
	public Money getInitialCash();

	/**
	 * 
	 * @return the card inserted
	 */
	public Card readCard();

	/**
	 * eject the card
	 */
	public void ejectCard();

	/**
	 * retain the card
	 */
	public void retainCard();

	/**
	 * dispense cash
	 * 
	 * @param amount
	 *            the amount to dispense
	 */
	public void dispenseCash(Money amount);

	/**
	 * clear the display
	 */
	public void clearDisplay();

	/**
	 * display a message
	 * 
	 * @param message
	 *            the message to display
	 */
	public void display(String message);

	/**
	 * read input
	 * 
	 * @param i
	 * @param j
	 * @return the input
	 */
	public String readInput(int i, int j);

	/**
	 * accept an envelope
	 * 
	 * @return if it was accepted
	 */
	public boolean acceptEnvelope();

	/**
	 * print a receipt line
	 * 
	 * @param string
	 *            a line of the receipt
	 */
	public void printReceiptLine(String string);
}
