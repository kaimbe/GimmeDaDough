package com.kaimbe.gimmedadough.atm;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

import org.joda.money.Money;

import com.kaimbe.gimmedadough.atm.physical.ATMPanel;

/**
 * 
 * @author Matthew Newell
 * 
 *         Class that represents the ATM. Handles all possible states of the
 *         ATM. Run as a thread. The front end of the system should be run in a
 *         separate thread as to avoid concurrency issues.
 * 
 *         The classes in this part of the system are purely the back end of the
 *         system. In order for the back end to fully function, a front end must
 *         be implemented.
 * 
 *         Things that need to be implemented in the front end of the system (to
 *         communicate properly with the back end):
 * 
 *         implement a mediator. implement the a network manager. implement the
 *         physical package. A main class that creates an ATMInfo object, an ATM
 *         object, starts the ATM thread, and constructs and runs the front end
 *         of the system.
 * 
 *         See the DEMO project for an example of how to do this.
 * 
 *         The system was designed this way to allow different front ends to be
 *         implemented, without having to alter the back end of the system. So a
 *         jar file of the back end of the system may be used in the front end.
 * 
 *         It should be noted that the back end of the system uses the
 *         joda-money library to deal with currency.
 */
public class ATM implements Runnable {
	/**
	 * info about this atm
	 */
	private ATMInformation atmInfo;
	/**
	 * the log for this atm. logs are stored in the logs directory.
	 */
	private Logger log;
	/**
	 * the current state of this atm
	 */
	private int state;
	/**
	 * flag for the atm being on or off
	 */
	private boolean switchOn;
	/**
	 * flag for a card being inserted or not
	 */
	private boolean cardInserted;
	/**
	 * off state
	 */
	private static final int OFF_STATE = 0;
	/**
	 * idle state
	 */
	private static final int IDLE_STATE = 1;
	/**
	 * serving customer state
	 */
	private static final int SERVING_CUSTOMER_STATE = 2;

	/**
	 * constructor
	 * 
	 * @param atmInfo
	 */
	public ATM(ATMInformation atmInfo) {
		this.atmInfo = atmInfo;

		// Create the ATM Logger
		try {
			Handler ATMHandler = new FileHandler("logs/" + atmInfo.getId()
					+ "_ATM_Log.log");
			log = Logger.getLogger("ATM Logging");
			log.addHandler(ATMHandler);
			log.setUseParentHandlers(false);
		} catch (SecurityException e) {
			System.err.println("Problem with the logger");
		} catch (IOException e) {
			System.err.println("Problem with the logger");
		}

		// Set up initial conditions
		state = OFF_STATE;
		switchOn = false;
		cardInserted = false;

		log.info("ATM Created");
	}

	@Override
	public void run() {
		Session currentSession = null;

		while (true) {
			switch (state) {
			case OFF_STATE:

				ATMPanel.getInstance().getConsole().display("Not available");

				synchronized (this) {
					try {
						wait();
					} catch (InterruptedException e) {
						log.severe(e.getMessage());
					}
				}

				if (switchOn) {
					performStartup();
					state = IDLE_STATE;
				}

				break;

			case IDLE_STATE:

				ATMPanel.getInstance().getConsole()
						.display("Please insert your card");
				cardInserted = false;

				synchronized (this) {
					try {
						wait();
					} catch (InterruptedException e) {
						log.severe(e.getMessage());
					}
				}

				if (cardInserted) {
					currentSession = new Session();
					state = SERVING_CUSTOMER_STATE;
				} else if (!switchOn) {
					performShutdown();
					state = OFF_STATE;
				}

				break;

			case SERVING_CUSTOMER_STATE:

				currentSession.performSession();
				state = IDLE_STATE;

				break;
			}
		}
	}

	/**
	 * turn the atm on
	 */
	public synchronized void switchOn() {
		switchOn = true;
		notify();
	}

	/**
	 * turn the atm off
	 */
	public synchronized void switchOff() {
		switchOn = false;
		notify();
	}

	/**
	 * tell the atm a card has been inserted
	 */
	public synchronized void cardInserted() {
		cardInserted = true;
		notify();
	}

	/**
	 * start the atm
	 */
	private void performStartup() {
		Money initialCash = ATMPanel.getInstance().getMaintenancePanel()
				.getInitialCash();
		ATMPanel.getInstance().getCashDispenser().setInitialCash(initialCash);
		ATMPanel.getInstance().getBankNetworkManager().openConnection();
	}

	/**
	 * shut down the atm
	 */
	private void performShutdown() {
		ATMPanel.getInstance().getBankNetworkManager().closeConnection();
	}

	/**
	 * @return the atmInfo
	 */
	public ATMInformation getAtmInfo() {
		return atmInfo;
	}

	/**
	 * @param atmInfo
	 *            the atmInfo to set
	 */
	public void setAtmInfo(ATMInformation atmInfo) {
		this.atmInfo = atmInfo;
	}
}
