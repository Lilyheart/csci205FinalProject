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
package unogame.helpers;

import deck.card.Card;
import deck.card.CardColor;
import deck.card.CardType;
import java.util.concurrent.CopyOnWriteArrayList;
import unogame.NoValidCardException;
import unogame.PlayCommand;

/**
 * Helper class for Artificial Players to determine the action of the computer
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
    public static int getValidCard(CopyOnWriteArrayList<Card> hand,
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
     * draw and/or discard a valid card call BUno if second to last card is
     * playable the AI has 90% chance to call BUno correctly
     *
     * @param hand
     * @param discardCard
     * @return the correct play command
     */
    public static PlayCommand getPlayCommand(CopyOnWriteArrayList<Card> hand,
                                             Card discardCard) {

        System.out.println("H: " + hand);

        try {
            getValidCard(hand, discardCard);
        } catch (NoValidCardException ex) {
            return PlayCommand.DRAW;
        }

        return PlayCommand.PLAYCARD;

    }
}
