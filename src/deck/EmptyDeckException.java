/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: James Kelly, Scott Little, Rachel Wang, Lily Romano
* Date: Nov 16, 2018
* Time: 10:14:10 AM
*
* Project: csci205FinalProject
* Package: cardpile
* File: EmptyDeckException
* Description:
*
* ****************************************
 */
package deck;

/**
 *
 * @author Lily Romano
 */
public class EmptyDeckException extends Exception {

    /**
     * Constructs an instance of <code>EmptyDeckException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public EmptyDeckException(String msg) {
        super(msg);
    }
}
