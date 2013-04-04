package com.kaimbe.gimmedadough.atm.physical;

import com.kaimbe.gimmedadough.gui.physical.GUIDisplay;

public interface EnvelopeReceiver {
	 /** Accept an envelope from customer.
    *
    *  @exception GUIDisplay.Cancelled if operation timed out or the
    *             customer cancelled it
    */
   public void acceptEnvelope() throws Cancelled;
}
