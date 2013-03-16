package com.kaimbe.gimmedadough.atm.physical;

import java.util.logging.Logger;

import com.kaimbe.gimmedadough.atm.ATMController;

public class EnvelopeReceiver {

	/** Constructor
    *
    *  @param log the log in which to record receiving an envelope
    */
   public EnvelopeReceiver(Logger log, ATMController controller)
   {
       this.log = log;
       this.controller = controller;
   }
   
   /** Accept an envelope from customer.
    *
    *  @exception CustomerConsole.Cancelled if operation timed out or the
    *             customer cancelled it
    */
   public void acceptEnvelope() throws CustomerConsole.Cancelled
   {
       boolean inserted = controller.getMediator().acceptEnvelope();
       if (inserted)
           log.info("Envelope inserted");
       else
           throw new CustomerConsole.Cancelled();
   }
   
   /** Log in which to record receiving an envelope
    */
   private Logger log;
   
   private ATMController controller;
}
