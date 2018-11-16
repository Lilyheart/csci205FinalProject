/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: James Kelly, Scott Little, Rachel Wang, Lily Romano
* Date: Nov 16, 2018
* Time: 10:02:38 AM
*
* Project: csci205FinalProject
* Package: deck
* File: PlayerHand
* Description:
*
* ****************************************
 */
package deck;

import deck.card.Card;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * An BUnoDeck file to store a player's hand
 *
 * @author Lily Romano
 */
public class PlayerHand extends BUnoDeck {

    /**
     * The ID number of the player
     */
    private final int idNum;

    /**
     * True if the player is a computer player, false if it is a human player
     */
    private final boolean isComputerPlayer;

    /**
     * The number of cards in a new hand
     */
    public final static int NEWHANDCARDNUM = 7;

    /**
     * A more readable variable to signify if human player
     */
    public static final Boolean HUMAN = false;

    /**
     * A more readable variable to signify if computer player
     */
    public static final Boolean COMPUTER = true;

    /**
     * An explicit constructor to create a new player's hand.
     *
     * @author Lily Romano
     *
     * @param idNum ID number of the player
     * @param isComputerPlayer True if the player is a computer player, false if
     * it is a human player
     */
    public PlayerHand(int idNum, boolean isComputerPlayer) {
        this.idNum = idNum;
        this.isComputerPlayer = isComputerPlayer;
    }

    /**
     * Returns a copy of the hand to ensure the hand isn't accidentally edited
     *
     * @author Lily Romano
     *
     * @return a copy of the hand to ensure the hand isn't accidentally edited
     */
    public CopyOnWriteArrayList<Card> getCopyOfHand() {
        return new CopyOnWriteArrayList<>(deck);
    }

    /**
     * Returns true if the player is a computer, otherwise false.
     *
     * @author Lily Romano
     *
     * @return true if the player is a computer, otherwise false.
     */
    public boolean isComputerPlayer() {
        return isComputerPlayer;
    }

}
