/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: James Kelly, Scott Little, Rachel Wang, Lily Romano
* Date: Nov 26, 2018
* Time: 10:43:00 AM
*
* Project: csci205FinalProject
* Package: unogame
* File: NoValidCardException
* Description:
*
* ****************************************
 */
package unogame;

/**
 * Exception thrown when no card in hand is playable
 *
 * @author Rachel Wang
 */
public class NoValidCardException extends Exception {

    /**
     * Creates a new instance of <code>NoValidCard</code> without detail
     * message.
     */
    public NoValidCardException() {
    }

    /**
     * Constructs an instance of <code>NoValidCard</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public NoValidCardException(String msg) {
        super(msg);
    }
}
