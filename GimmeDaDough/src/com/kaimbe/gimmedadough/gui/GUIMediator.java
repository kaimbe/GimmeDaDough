package com.kaimbe.gimmedadough.gui;

import org.joda.money.Money;

import com.kaimbe.gimmedadough.atm.ATM;
import com.kaimbe.gimmedadough.banking.Card;
import com.kaimbe.gimmedadough.mediation.Mediator;

public class GUIMediator implements Mediator{
	private ATM atm;
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

	public void setATM(ATM atm) {
		this.atm = atm;
		
	}

}
