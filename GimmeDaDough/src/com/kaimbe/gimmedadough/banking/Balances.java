package com.kaimbe.gimmedadough.banking;

import org.joda.money.Money;

/**
 * 
 * @author Matthew Newell
 * 
 *         an accounts balances
 * 
 */
public class Balances {
	/**
	 * Current total balance in the account
	 */
	private Money total;

	/**
	 * Current available balance in the account
	 */
	private Money available;

	/**
	 * Constructor. Create an object whose values will be filled in later, when
	 * returning a value to the creator.
	 */
	public Balances() {
	}

	/**
	 * Mutator. Fill in values
	 * 
	 * @param total
	 *            the total balance in the account
	 * @param available
	 *            the available balance
	 */
	public void setBalances(Money total, Money available) {
		this.total = total;
		this.available = available;
	}

	/**
	 * Accessor for total balance
	 * 
	 * @return total balance in the account
	 */
	public Money getTotal() {
		return total;
	}

	/**
	 * Accessor for available balance
	 * 
	 * @return available balance
	 */
	public Money getAvailable() {
		return available;
	}
}
