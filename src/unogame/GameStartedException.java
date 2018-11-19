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
 * Thrown when a command is issued that is only acceptable when a game has
 * started yet the game hadn't actually been started yet.
 *
 * @author Lily Romano
 */
public class GameStartedException extends Exception {

    /**
     * Creates a new instance of <code>GameStartedException</code> without
     * detail message.
     */
    public GameStartedException() {
    }

    /**
     * Constructs an instance of <code>GameStartedException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public GameStartedException(String msg) {
        super(msg);
    }
}
