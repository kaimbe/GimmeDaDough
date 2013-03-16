package com.kaimbe.gimmedadough.mediation;

import org.joda.money.Money;

import com.kaimbe.gimmedadough.banking.Card;

public interface Mediator {
	public void test();

	public Money getInitialCash();

	public Card readCard();

	public void ejectCard();

	public void retainCard();

	public void dispenseCash(Money amount);

	public void clearDisplay();

	public void display(String message);

	public String readInput(int i, int j);

	public boolean acceptEnvelope();

	public void printReceiptLine(String string);
}
