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
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Helper class for Artificial Players to determine the action of the computer
 * player
 *
 * @author Rachel Wang
 *
 */
public final class AIHelper {

    private boolean BUno = false;

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
     * draw and/or discard a valid card call BUno if second to last card is
     * playable the AI has 90% chance to call BUno correctly
     *
     * @param hand
     * @param discardCard
     * @return the correct play command
     */
    public PlayCommand getPlayCommand(CopyOnWriteArrayList<Card> hand,
                                      Card discardCard) {

        try {
            getValidCard(hand, discardCard);
            //If second to last card, call Uno
            if (hand.size() == 2) {
                Random rand = new Random();
                double chance = rand.nextDouble();
                if (chance > .1) {
                    BUno = true;
                }
            }
        } catch (NoValidCardException ex) {
            return PlayCommand.DRAW;
        } finally { //check if there is a playable card, if so play; else pass
            try {
                getValidCard(hand, discardCard);
                return PlayCommand.PLAYCARD;
            } catch (NoValidCardException ex) {
                return PlayCommand.PASS;
            }
        }
    }

    /**
     * the AI has 90% chance to correctly catch if the previous player forgot to
     * call BUno
     *
     * @param hand
     * @return whether the previous player forgot to call BUno
     *
     */
    public boolean catchBuno(CopyOnWriteArrayList<Card> hand) {
        if (hand.size() == 1) {
            Random rand = new Random();
            double chance = rand.nextDouble();
            if (chance > .1 && BUno == false) {
                return true; // the previous player forgot to call BUno
            }
        }
        return false;
    }
}
