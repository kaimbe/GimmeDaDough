package com.kaimbe.gimmedadough.atm.transaction;

import org.joda.money.Money;

import com.kaimbe.gimmedadough.atm.Session;
import com.kaimbe.gimmedadough.atm.physical.ATMPanel;
import com.kaimbe.gimmedadough.atm.physical.Cancelled;
import com.kaimbe.gimmedadough.banking.AccountInformation;
import com.kaimbe.gimmedadough.banking.Card;
import com.kaimbe.gimmedadough.banking.Message;
import com.kaimbe.gimmedadough.banking.Receipt;


public class Inquiry extends Transaction{
	/** Constructor
    *
    *  @param atm the ATM used to communicate with customer
    *  @param session the session in which the transaction is being performed
    *  @param card the customer's card
    *  @param pin the PIN entered by the customer
    */
   public Inquiry(Session session, Card card, int pin)
   {
       super(session, card, pin);
   }
   
   /** Get specifics for the transaction from the customer
    *
    *  @return message to bank for initiating this transaction
    *  @exception GUIConsole.Cancelled if customer cancelled this transaction
    */
   protected Message getSpecificsFromCustomer() throws Cancelled
   {
       from = ATMPanel.getInstance().getConsole().readMenuChoice(
           "Account to inquire from",
           AccountInformation.ACCOUNT_NAMES);
       return new Message(Message.INQUIRY, 
                       card, pin, serialNumber, from, -1, Money.parse("CAD 0"));
   }
   
   /** Complete an approved transaction
    *
    *  @return receipt to be printed for this transaction
    */
   protected Receipt completeTransaction()
   {
       return new Receipt(this.atm, this.card, this, this.balances) {
           {
               detailsPortion = new String[2];
               detailsPortion[0] = "INQUIRY FROM: " + 
                                   AccountInformation.ACCOUNT_ABBREVIATIONS[from];
               detailsPortion[1] = "";
           }
       };
   }
   
   /** Account to inquire about
    */
   private int from;
}
