package com.kaimbe.gimmedadough.banking;

/**
 * 
 * @author Matthew Newell
 * 
 *         represents a card
 * 
 */
public class Card {
	/**
	 * Card number encoded on the card
	 */
	private int number;

	/**
	 * Constructor
	 * 
	 * @param number
	 *            the card number
	 */
	public Card(int number) {
		this.number = number;
	}

	/**
	 * Accessor for number
	 * 
	 * @return the number of the card
	 */
	public int getNumber() {
		return number;
	}
}
