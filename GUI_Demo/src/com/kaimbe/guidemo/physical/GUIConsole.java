package com.kaimbe.guidemo.physical;

import java.util.logging.Logger;

import org.joda.money.Money;

import com.kaimbe.gimmedadough.atm.physical.Cancelled;
import com.kaimbe.gimmedadough.atm.physical.Console;
import com.kaimbe.guidemo.GUIMediator;

public class GUIConsole implements Console {
	private Logger log = Logger.getLogger("ATM Logging");

	public GUIConsole() {
		log.info("console created");
	}

	@Override
	public void display(String message) {
		GUIMediator.getInstance().clearDisplay();
		GUIMediator.getInstance().display(message);
	}

	@Override
	public int readPIN(String prompt) throws Cancelled {
		GUIMediator.getInstance().clearDisplay();
		GUIMediator.getInstance().display(prompt);
		GUIMediator.getInstance().display("");

		String input = GUIMediator.getInstance().readInput(/*
															 * Simulation.PIN_MODE
															 */0, 0);

		GUIMediator.getInstance().clearDisplay();

		if (input == null)
			throw new Cancelled();
		else
			return Integer.parseInt(input);

	}

	@Override
	public synchronized int readMenuChoice(String prompt, String[] menu)
			throws Cancelled {
		GUIMediator.getInstance().clearDisplay();
		GUIMediator.getInstance().display(prompt);
		for (int i = 0; i < menu.length; i++)
			GUIMediator.getInstance().display((i + 1) + ") " + menu[i]);

		String input = GUIMediator.getInstance().readInput(/*
															 * Simulation.MENU_MODE
															 */0, menu.length);

		GUIMediator.getInstance().clearDisplay();

		if (input == null)
			throw new Cancelled();
		else
			return Integer.parseInt(input) - 1;

	}

	@Override
	public synchronized Money readAmount(String prompt) throws Cancelled {
		GUIMediator.getInstance().clearDisplay();
		GUIMediator.getInstance().display(prompt);
		GUIMediator.getInstance().display("");

		String input = GUIMediator.getInstance().readInput(/*
															 * Simulation.
															 * AMOUNT_MODE
															 */0, 0);

		GUIMediator.getInstance().clearDisplay();

		if (input == null)
			throw new Cancelled();
		else {
			// TODO: Generalize currency
			return Money.parse("CAD " + input);
		}

	}
}
