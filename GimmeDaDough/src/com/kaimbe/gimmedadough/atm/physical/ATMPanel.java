package com.kaimbe.gimmedadough.atm.physical;

import com.kaimbe.gimmedadough.atm.io.BankNetworkManager;

public class ATMPanel {
	
	private static ATMPanel instance = new ATMPanel();
	
	private CardReader cardReader;
	private CashDispenser cashDispenser;
	private Console console;
	private EnvelopeReceiver envelopeReceiver;
	private BankNetworkManager bankNetworkManager;
	private MaintenancePanel maintenancePanel;
	private ReceiptPrinter receiptPrinter;
	
	private ATMPanel() {
		
	}
	
	public static synchronized ATMPanel getInstance() {
		return instance;
	}
	
	/**
	 * @return the cardReader
	 */
	public CardReader getCardReader() {
		return cardReader;
	}

	/**
	 * @param cardReader the cardReader to set
	 */
	public void setCardReader(CardReader cardReader) {
		this.cardReader = cardReader;
	}

	/**
	 * @return the cashDispenser
	 */
	public CashDispenser getCashDispenser() {
		return cashDispenser;
	}

	/**
	 * @param cashDispenser the cashDispenser to set
	 */
	public void setCashDispenser(CashDispenser cashDispenser) {
		this.cashDispenser = cashDispenser;
	}

	/**
	 * @return the display
	 */
	public Console getConsole() {
		return console;
	}

	/**
	 * @param display the display to set
	 */
	public void setConsole(Console display) {
		this.console = display;
	}

	/**
	 * @return the envelopeReceiver
	 */
	public EnvelopeReceiver getEnvelopeReceiver() {
		return envelopeReceiver;
	}

	/**
	 * @param envelopeReceiver the envelopeReceiver to set
	 */
	public void setEnvelopeReceiver(EnvelopeReceiver envelopeReceiver) {
		this.envelopeReceiver = envelopeReceiver;
	}

	/**
	 * @return the bankNetworkManager
	 */
	public BankNetworkManager getBankNetworkManager() {
		return bankNetworkManager;
	}

	/**
	 * @param bankNetworkManager the bankNetworkManager to set
	 */
	public void setBankNetworkManager(BankNetworkManager bankNetworkManager) {
		this.bankNetworkManager = bankNetworkManager;
	}

	/**
	 * @return the maintenancePanel
	 */
	public MaintenancePanel getMaintenancePanel() {
		return maintenancePanel;
	}

	/**
	 * @param maintenancePanel the maintenancePanel to set
	 */
	public void setMaintenancePanel(MaintenancePanel maintenancePanel) {
		this.maintenancePanel = maintenancePanel;
	}

	/**
	 * @return the receiptPrinter
	 */
	public ReceiptPrinter getReceiptPrinter() {
		return receiptPrinter;
	}

	/**
	 * @param receiptPrinter the receiptPrinter to set
	 */
	public void setReceiptPrinter(ReceiptPrinter receiptPrinter) {
		this.receiptPrinter = receiptPrinter;
	}
}
