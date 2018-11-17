/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: James Kelly, Scott Little, Rachel Wang, Lily Romano
* Date: Nov 16, 2018
* Time: 5:58:11 PM
*
* Project: csci205FinalProject
* Package: unogame
* File: GameNotStartedException
* Description:
*
* ****************************************
 */
package unogame;

/**
 *
 * @author lilyheart
 */
public class GameNotStartedException extends Exception {

    /**
     * Creates a new instance of <code>GameNotStartedException</code> without
     * detail message.
     */
    public GameNotStartedException() {
    }

    /**
     * Constructs an instance of <code>GameNotStartedException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public GameNotStartedException(String msg) {
        super(msg);
    }
}
