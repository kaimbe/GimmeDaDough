package com.kaimbe.gimmedadough.atm.transaction;

import org.joda.money.Money;

import com.kaimbe.gimmedadough.atm.Session;
import com.kaimbe.gimmedadough.atm.physical.ATMPanel;
import com.kaimbe.gimmedadough.atm.physical.Cancelled;
import com.kaimbe.gimmedadough.banking.AccountInformation;
import com.kaimbe.gimmedadough.banking.Card;
import com.kaimbe.gimmedadough.banking.Message;
import com.kaimbe.gimmedadough.banking.Receipt;

public class Withdrawal extends Transaction{
	/** Constructor
    *
    *  @param atm the ATM used to communicate with customer
    *  @param session the session in which the transaction is being performed
    *  @param card the customer's card
    *  @param pin the PIN entered by the customer
    */
   public Withdrawal(Session session, Card card, int pin)
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
       from = ATMPanel.getInstance().getConsole().readMenuChoice(
           "Account to withdraw from",
           AccountInformation.ACCOUNT_NAMES);

       String [] amountOptions = { "$20", "$40", "$60", "$100", "$200" };
       Money [] amountValues = { 
                                 Money.parse("CAD 20"), Money.parse("CAD 40"), Money.parse("CAD 60"),
                                 Money.parse("CAD 80"), Money.parse("CAD 100")
                               };
                                 
       String amountMessage = "";
       boolean validAmount = false;
       
       while (! validAmount)
       {
           amount = amountValues [ 
               ATMPanel.getInstance().getConsole().readMenuChoice(
                   amountMessage + "Amount of cash to withdraw", amountOptions) ];
                           
           validAmount = ATMPanel.getInstance().getCashDispenser().checkCashOnHand(amount);

           if (! validAmount)
               amountMessage = "Insufficient cash available\n";
       }
       
       return new Message(Message.WITHDRAWAL, 
                          card, pin, serialNumber, from, -1, amount);

   }
   
   /** Complete an approved transaction
    *
    *  @return receipt to be printed for this transaction
    */
   protected Receipt completeTransaction()
   {
	   ATMPanel.getInstance().getCashDispenser().dispenseCash(amount);
       return new Receipt(this.atm, this.card, this, this.balances) {
           {
               detailsPortion = new String[2];
               detailsPortion[0] = "WITHDRAWAL FROM: " + 
                                   AccountInformation.ACCOUNT_ABBREVIATIONS[from];
               detailsPortion[1] = "AMOUNT: " + amount.toString();
           }
       };
   }
   
   /** Account to withdraw from
    */ 
   private int from;
   
   /** Amount of money to withdraw
    */
   private Money amount;           
}
