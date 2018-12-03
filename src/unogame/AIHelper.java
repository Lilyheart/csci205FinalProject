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

import deck.PlayerHand;
import deck.card.Card;
import deck.card.CardColor;
import deck.card.CardType;
import java.util.Random;
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
     * get a valid card to play and play the "best" playable card
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
        int maxValue = 0;
        int maxID = 0;

        for (int i = 0; i < hand.size(); i++) {
            //Only test color and value of number cards
            //Keep track of maxID and maxValue of the playable card
            int curValue = hand.get(i).getType().getCardPointValue();
            //Testing color, play the card if matches
            if (hand.get(i).getColor() == discardColor) {
                isCardPlayable = true;

                if (maxValue < curValue) {
                    maxValue = curValue;
                    maxID = i;
                }
            }
            //Testing type, play the card if matches
            if (hand.get(i).getType() == discardType) {
                isCardPlayable = true;
                if (maxValue < curValue) {
                    maxValue = curValue;
                    maxID = i;
                }
            }
        }

        if (isCardPlayable) {
            return maxID; //AI will always play the card with the highest value
        }
        else {
            throw new NoValidCardException();
        }
    }

    /**
     * call BUno if second to last card is playable the AI has 90% chance to
     * call BUno correctly
     *
     * @param hand
     * @return true if it's ready to call Buno; false otherwise
     */
    public static boolean isTimeForBuno(PlayerHand hand) {
        //If second to last card, call Uno
        if (hand.getDeckSize() == 2) {
            Random rand = new Random();
            double chance = rand.nextDouble();
            if (chance > .1) {
                return true;
            }
        }
        return false;
    }

    /**
     * draw and/or discard a valid card
     *
     * @param hand
     * @param discardCard
     * @return the correct play command: Draw/Play/Pass
     */
    public static PlayCommand getPlayCommand(CopyOnWriteArrayList<Card> hand,
                                             Card discardCard) {

        try {
            getValidCard(hand, discardCard);
        } catch (NoValidCardException ex) {
            return PlayCommand.DRAW;
        }
        return PlayCommand.PLAYCARD;
    }

    /**
     * the AI has 90% chance to Buno the active player
     *
     * @param hand
     * @return true if Buno the active player
     *
     */
    public static boolean isTimeToBunoActivePlayer(PlayerHand activePlayerHand) {
        if (activePlayerHand.getDeckSize() == 1) {
            Random rand = new Random();
            double chance = rand.nextDouble();
            if (chance > .1) {
                return true;
            }
        }
        return false;
    }
}
