package com.kaimbe.gimmedadough.atm.physical;

import java.util.logging.Logger;

public class EnvelopeReceiver {

	/** Constructor
    *
    *  @param log the log in which to record receiving an envelope
    */
   public EnvelopeReceiver(Logger log)
   {
       this.log = log;
   }
   
   /** Accept an envelope from customer.
    *
    *  @exception CustomerConsole.Cancelled if operation timed out or the
    *             customer cancelled it
    */
   public void acceptEnvelope() throws CustomerConsole.Cancelled
   {
	   /*
       boolean inserted = Simulation.getInstance().acceptEnvelope();
       if (inserted)
           log.logEnvelopeAccepted();
       else
           throw new CustomerConsole.Cancelled();
           */
   }
   
   /** Log in which to record receiving an envelope
    */
   private Logger log;

}
