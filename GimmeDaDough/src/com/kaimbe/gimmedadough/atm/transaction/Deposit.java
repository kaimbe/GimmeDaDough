package com.kaimbe.gimmedadough.atm.transaction;

import org.joda.money.Money;

import com.kaimbe.gimmedadough.atm.Session;
import com.kaimbe.gimmedadough.atm.physical.ATMPanel;
import com.kaimbe.gimmedadough.atm.physical.Cancelled;
import com.kaimbe.gimmedadough.banking.AccountInformation;
import com.kaimbe.gimmedadough.banking.Card;
import com.kaimbe.gimmedadough.banking.Message;
import com.kaimbe.gimmedadough.banking.Receipt;


public class Deposit extends Transaction{
	/** Constructor
    *
    *  @param atm the ATM used to communicate with customer
    *  @param session the session in which the transaction is being performed
    *  @param card the customer's card
    *  @param pin the PIN entered by the customer
    */
   public Deposit(Session session, Card card, int pin)
   {
       super(session, card, pin);
   }
   
   /** Get specifics for the transaction from the customer
    *
    *  @return message to bank for initiating this transaction
    *  @exception Cancelled if customer cancelled this transaction
    */
   protected Message getSpecificsFromCustomer() throws Cancelled
   {
       to = ATMPanel.getInstance().getConsole().readMenuChoice(
           "Account to deposit to",
           AccountInformation.ACCOUNT_NAMES);

       amount = ATMPanel.getInstance().getConsole().readAmount("Amount to deposit");
       
       return new Message(Message.INITIATE_DEPOSIT,
                          card, pin, serialNumber, -1, to, amount);
   }
   
   /** Complete an approved transaction
    *
    *  @return receipt to be printed for this transaction
    *  @exception Cancelled if customer cancelled or 
    *             transaction timed out
    */
   protected Receipt completeTransaction() throws Cancelled
   {
	   ATMPanel.getInstance().getEnvelopeReceiver().acceptEnvelope();
	   ATMPanel.getInstance().getBankNetworkManager().sendMessage(
           new Message(Message.COMPLETE_DEPOSIT,
                       card, pin, serialNumber, -1, to, amount), 
           balances);
           
       return new Receipt(this.atm, this.card, this, this.balances) {
           {
               detailsPortion = new String[2];
               detailsPortion[0] = "DEPOSIT TO: " + 
                                   AccountInformation.ACCOUNT_ABBREVIATIONS[to];
               detailsPortion[1] = "AMOUNT: " + amount.toString();
           }
       };
   }
   
   /** Account to deposit to
    */ 
   private int to;
   
   /** Amount of money to deposit
    */
   private Money amount;
}
