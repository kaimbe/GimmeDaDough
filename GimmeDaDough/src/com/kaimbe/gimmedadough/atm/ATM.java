package com.kaimbe.gimmedadough.atm;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

import org.joda.money.Money;

import com.kaimbe.gimmedadough.atm.physical.*;

public class ATM implements Runnable {
	private ATMInfo atmInfo;
	private Logger log;
	
	private int state;
	private boolean switchOn;
	private boolean cardInserted;

	private static final int OFF_STATE = 0;
	private static final int IDLE_STATE = 1;
	private static final int SERVING_CUSTOMER_STATE = 2;

	public ATM(ATMInfo atmInfo) {
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

		// Set up initial conditions when ATM first created
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

				ATMPanel.getInstance().getConsole().display("Not currently available");

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

				ATMPanel.getInstance().getConsole().display("Please insert your card");
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

	public synchronized void switchOn() {
		switchOn = true;
		notify();
	}

	public synchronized void switchOff() {
		switchOn = false;
		notify();
	}

	public synchronized void cardInserted() {
		cardInserted = true;
		notify();
	}

	private void performStartup() {
		Money initialCash = ATMPanel.getInstance().getMaintenancePanel().getInitialCash();
		ATMPanel.getInstance().getCashDispenser().setInitialCash(initialCash);
		ATMPanel.getInstance().getBankNetworkManager().openConnection();
	}

	private void performShutdown() {
		ATMPanel.getInstance().getBankNetworkManager().closeConnection();
	}

	/**
	 * @return the atmInfo
	 */
	public ATMInfo getAtmInfo() {
		return atmInfo;
	}

	/**
	 * @param atmInfo the atmInfo to set
	 */
	public void setAtmInfo(ATMInfo atmInfo) {
		this.atmInfo = atmInfo;
	}
}
