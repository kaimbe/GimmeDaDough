package com.kaimbe.guidemo;

import org.joda.money.Money;

import com.kaimbe.gimmedadough.atm.Mediator;
import com.kaimbe.gimmedadough.banking.Card;

public class GUIMediator implements Mediator {

	private static GUIMediator instance = new GUIMediator();
	private MainFrame frame;

	private GUIMediator() {
		// Singleton design pattern
	}

	public static synchronized GUIMediator getInstance() {
		return instance;
	}

	public void setFrame(final MainFrame frame) {
		this.frame = frame;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException("Clone is not allowed.");
	}

	@Override
	public Money getInitialCash() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Card readCard() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ejectCard() {
		// TODO Auto-generated method stub

	}

	@Override
	public void retainCard() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispenseCash(Money amount) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearDisplay() {
		// TODO Auto-generated method stub

	}

	@Override
	public void display(String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public String readInput(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean acceptEnvelope() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void printReceiptLine(String string) {
		// TODO Auto-generated method stub

	}
}
