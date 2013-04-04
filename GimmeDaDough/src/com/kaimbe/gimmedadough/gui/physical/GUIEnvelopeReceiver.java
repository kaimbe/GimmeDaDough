package com.kaimbe.gimmedadough.gui.physical;

import java.util.logging.Logger;

import com.kaimbe.gimmedadough.atm.physical.Cancelled;
import com.kaimbe.gimmedadough.atm.physical.EnvelopeReceiver;
import com.kaimbe.gimmedadough.gui.GUIMediator;

public class GUIEnvelopeReceiver implements EnvelopeReceiver{

	/** Constructor
    *
    *  @param log the log in which to record receiving an envelope
    */
   public GUIEnvelopeReceiver()
   {
	   
   }
   
   /** Accept an envelope from customer.
    *
    *  @exception GUIDisplay.Cancelled if operation timed out or the
    *             customer cancelled it
    */
   @Override
   public void acceptEnvelope() throws Cancelled
   {
       boolean inserted = GUIMediator.getInstance().acceptEnvelope();
       if (inserted)
           log.info("Envelope inserted");
       else
           throw new Cancelled();
   }
   
   /** Log in which to record receiving an envelope
    */
   private Logger log = Logger.getLogger("ATM Logging");
   
}
