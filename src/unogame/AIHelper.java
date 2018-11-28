/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: James Kelly, Scott Little, Rachel Wang, Lily Romano
* Date: Nov 26, 2018
* Time: 9:41:49 AM
*
* Project: csci205FinalProject
* Package: unogame
* File: AIHelper
* Description:
*
* ****************************************
 */
package unogame;

import deck.card.Card;
import deck.card.CardColor;
import deck.card.CardType;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Help class for Artificial Players to determine the action of the computer
 * player
 *
 * @author Rachel Wang
 *
 */
public final class AIHelper {

    /**
     * get a valid card to play
     *
     * @param hand
     * @param discardCard
     * @return
     * @throws NoValidCardException
     */
    public int getValidCard(CopyOnWriteArrayList<Card> hand,
                            Card discardCard) throws NoValidCardException {
        CardType discardType = discardCard.getType();
        CardColor discardColor = discardCard.getColor();
        boolean isCardPlayable = false;

        //Testing color and type of number cards
        for (int i = 0; i < hand.size(); i++) {
            //Only test color and value of number cards

            //Testing color, play the card if matches
            if (hand.get(i).getColor() == discardColor) {
                isCardPlayable = true;
                return i;
            }
            //Testing type, play the card if matches
            if (hand.get(i).getType() == discardType) {
                isCardPlayable = true;
                return i;
            }
        }
        if (!isCardPlayable) {
            throw new NoValidCardException();
        }

        //Should never hit this point
        return -1;
    }

    /**
     * get the correct play command
     *
     * @param hand
     * @param discardCard
     * @return
     */
    public PlayCommand getPlayCommand(CopyOnWriteArrayList<Card> hand,
                                      Card discardCard) {
        //If one card left, call Uno
        if (hand.size() == 1) {
            return PlayCommand.BUNO;
        }
        try {
            getValidCard(hand, discardCard);
        } catch (NoValidCardException ex) {
            return PlayCommand.DRAW;
        } finally {
            return PlayCommand.PLAYCARD;
        }

    }
}
