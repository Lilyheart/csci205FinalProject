/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: James Kelly, Scott Little, Rachel Wang, Lily Romano
* Date: Nov 16, 2018
* Time: 5:58:27 PM
*
* Project: csci205FinalProject
* Package: unogame
* File: GameStartedException
* Description:
*
* ****************************************
 */
package unogame;

/**
 * Thrown when the round is over
 *
 * @author Lily Romano
 */
public class RoundOverException extends Exception {

    /**
     * Constructs an instance of <code>RoundOverException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public RoundOverException(String msg) {
        super(msg);
    }
}
