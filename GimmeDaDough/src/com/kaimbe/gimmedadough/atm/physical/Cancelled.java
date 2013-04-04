package com.kaimbe.gimmedadough.atm.physical;

/** Exception thrown when the user presses the cancel key while the ATM is
 *  waiting for some action
 */
public class Cancelled extends Exception
{
    /** Constructor
     */
    public Cancelled()
    {
        super("Cancelled by customer");
    }
}