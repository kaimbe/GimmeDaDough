package com.kaimbe.gimmedadough.atm.physical;

import com.kaimbe.gimmedadough.gui.physical.GUIConsole;

public interface EnvelopeReceiver {
	 /** Accept an envelope from customer.
    *
    *  @exception GUIConsole.Cancelled if operation timed out or the
    *             customer cancelled it
    */
   public void acceptEnvelope() throws Cancelled;
}
