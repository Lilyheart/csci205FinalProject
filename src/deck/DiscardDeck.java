/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: James Kelly, Scott Little, Rachel Wang, Lily Romano
* Date: Nov 16, 2018
* Time: 10:03:04 AM
*
* Project: csci205FinalProject
* Package: deck
* File: DiscardDeck
* Description:
*
* ****************************************
 */
package deck;

/**
 * A DiscardDeck file for the deck GUI
 *
 * @author Lily Romano
 */
public class DiscardDeck extends BUnoDeck {
    //TODO [Final Project] If no specific methods are added here, decide if it should be merged into DrawDeck and the file renamed

    /**
     * Returns a well formatted string representing the deck.
     *
     * @author Lily Romano
     *
     * @return a well formatted string representing the deck.
     */
    @Override
    public String toString() {
        return "Discard " + super.toString();
    }
}
