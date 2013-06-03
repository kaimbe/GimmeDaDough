package com.kaimbe.gimmedadough.atm;

import com.kaimbe.gimmedadough.atm.physical.ATMPanel;
import com.kaimbe.gimmedadough.atm.physical.Cancelled;
import com.kaimbe.gimmedadough.atm.transaction.Transaction;
import com.kaimbe.gimmedadough.banking.Card;

/**
 * 
 * @author Matthew Newell 
 * 
 * a session between a customer and the atm
 */
public class Session {
	/**
	 * customers pin
	 */
	private int pin;
	/**
	 * state of the session
	 */
	private int state;
	/**
	 * reading card state
	 */
	private static final int READING_CARD_STATE = 1;
	/**
	 * reading pin state
	 */
	private static final int READING_PIN_STATE = 2;
	/**
	 * choosing transaction state
	 */
	private static final int CHOOSING_TRANSACTION_STATE = 3;
	/**
	 * performing transaction state
	 */
	private static final int PERFORMING_TRANSACTION_STATE = 4;
	/**
	 * ejecting card state
	 */
	private static final int EJECTING_CARD_STATE = 5;
	/**
	 * final state. the session is over.
	 */
	private static final int FINAL_STATE = 6;

	/**
	 * Constructor
	 * 
	 * @param atm
	 *            the ATM on which the session is performed
	 */
	public Session() {
		state = READING_CARD_STATE;
	}

	/**
	 * perform a session
	 */
	public void performSession() {
		Card card = null;
		Transaction currentTransaction = null;

		while (state != FINAL_STATE) {
			switch (state) {
			case READING_CARD_STATE:

				card = ATMPanel.getInstance().getCardReader().readCard();

				if (card != null)
					state = READING_PIN_STATE;
				else {
					ATMPanel.getInstance().getConsole()
							.display("Unable to read card");
					state = EJECTING_CARD_STATE;
				}
				break;

			case READING_PIN_STATE:

				try {
					pin = ATMPanel
							.getInstance()
							.getConsole()
							.readPIN(
									"Please enter your PIN\n"
											+ "Then press ENTER");
					state = CHOOSING_TRANSACTION_STATE;
				} catch (Cancelled e) {
					state = EJECTING_CARD_STATE;
				}
				break;

			case CHOOSING_TRANSACTION_STATE:

				try {
					currentTransaction = Transaction.makeTransaction(this,
							card, pin);
					state = PERFORMING_TRANSACTION_STATE;
				} catch (Cancelled e) {
					state = EJECTING_CARD_STATE;
				}
				break;

			case PERFORMING_TRANSACTION_STATE:

				try {
					boolean doAgain = currentTransaction.performTransaction();
					if (doAgain)
						state = CHOOSING_TRANSACTION_STATE;
					else
						state = EJECTING_CARD_STATE;
				} catch (Transaction.CardRetained e) {
					state = FINAL_STATE;
				}
				break;

			case EJECTING_CARD_STATE:

				ATMPanel.getInstance().getCardReader().ejectCard();
				state = FINAL_STATE;
				break;
			}
		}
	}

	/**
	 * Change the pin recorded for the customer
	 * 
	 * @param pin
	 *            the newly entered pin
	 */
	public void setPIN(int pin) {
		this.pin = pin;
	}
}
