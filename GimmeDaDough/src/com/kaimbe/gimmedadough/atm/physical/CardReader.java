package com.kaimbe.gimmedadough.atm.physical;

import com.kaimbe.gimmedadough.banking.Card;

/**
 * 
 * @author Matthew Newell
 * 
 *         interface that represents a card reader
 * 
 */
public interface CardReader {

	/**
	 * Read a card that has been partially inserted into the reader
	 * 
	 * @return Card object representing information on the card if read
	 *         successfully, null if not read successfully
	 */
	public Card readCard();

	/**
	 * Eject the card that is currently inside the reader.
	 */
	public void ejectCard();

	/**
	 * Retain the card that is currently inside the reader for action by the
	 * bank.
	 */
	public void retainCard();
}
