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

/**
 * Helper class for Artificial Players to determine the action of the computer
 * player
 *
 * @author Rachel Wang
 */
public final class AIHelper {

    /**
     * Finds a valid card to play and play the "best" playable card
     *
     * @author Rachel Wang
     *
     * @param playersHand the {@code PlayerHand} in question
     * @param discardCard The top card of the discard pile
     * @return the index of the playable card in the {@code hand}.
     * @throws NoValidCardException When there is no valid card to play
     */
    public static int findValidCard(PlayerHand playersHand, Card discardCard) throws NoValidCardException {
        CopyOnWriteArrayList<Card> hand = playersHand.getCopyOfHand();
        boolean isCardPlayable = false;

        //Get the information about the discard card
        CardType discardType = discardCard.getType();
        CardColor discardColor = discardCard.getColor();

        //[AI Brain] - Always play the large point value card to remove it from hand
        int maxValue = 0;
        int maxID = 0;

        //Testing color and type of number cards
        for (int i = 0; i < hand.size(); i++) {
            //Keep track of maxID and maxValue of the playable card
            int curValue = hand.get(i).getType().getCardPointValue();

            //Test color
            if (hand.get(i).getColor() == discardColor) {
                isCardPlayable = true;

                if (maxValue < curValue) {
                    maxValue = curValue;
                    maxID = i;
                }
            }

            //Test type
            if (hand.get(i).getType() == discardType) {
                isCardPlayable = true;
                if (maxValue < curValue) {
                    maxValue = curValue;
                    maxID = i;
                }
            }
        }

        //return if the id of the max point value card if playable cards were found
        if (isCardPlayable) {
            return maxID;
        }
        else {
            throw new NoValidCardException();
        }
    }

    /**
     * Determines if it needs to draw a card or if has a playable card.
     *
     * @author Rachel Wang
     *
     * @param playersHand the {@code PlayerHand} in question
     * @param discardCard The top card of the discard pile
     * @return the desired {@code PlayCommand}.
     */
    public static PlayCommand determinePlayCommand(PlayerHand playersHand,
                                                   Card discardCard) {

        try {
            findValidCard(playersHand, discardCard);
        } catch (NoValidCardException e) {
            return PlayCommand.NOPLAYABLECARD;
        }
        return PlayCommand.PLAYABLECARD;
    }

    /**
     * Call BUno if second to last card is playable. The AI has 90% chance to
     * call BUno correctly
     *
     * @author Rachel Wang
     *
     * @param playersHand the {@code PlayerHand} in question
     * @return true if it's ready to call BUno; false otherwise
     */
    public static boolean checkIsTimeForBuno(PlayerHand playersHand) {

        //If second to last card, call Uno
        if (playersHand.getDeckSize() == 2) {
            Random rand = new Random();
            double chance = rand.nextDouble();
            if (chance < playersHand.getAIintelligence().getBunoCatchPercent()) {
                return true;
            }
        }
        return false;
    }

    /**
     * the AI has 90% chance to BUno the active player
     *
     * @author Rachel Wang
     * @param activePlayersHand the {@code PlayerHand} of the active player
     * @return true if BUno the active player
     *
     */
    public static boolean checkIsTimeToBunoActivePlayer(
            PlayerHand activePlayersHand) {
        if (activePlayersHand.getDeckSize() == 1) {
            Random rand = new Random();
            double chance = rand.nextDouble();
            if (chance < activePlayersHand.getAIintelligence().getBunoCatchPercent()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Processes the computer's turn using the AI
     *
     * @param unoGame the {@code Game} object
     * @param playerIndex the index of the player from the {code players}
     * ArrayList in the {@code unoGame}
     */
    public static void computerTurn(Game unoGame, int playerIndex) {
        PlayerHand hand = unoGame.getPlayersCopy(playerIndex);
        Card discardCard = unoGame.getTheDiscardDeck().peekBottomCard();

        PlayCommand playcommand = determinePlayCommand(hand, discardCard);

        switch (playcommand) {
            case NOPLAYABLECARD:
                unoGame.drawCard(playerIndex);
                break;

            case PLAYABLECARD:
                //TODO [!Finalize] Does this mirror a player method?
                boolean isbuno = AIHelper.checkIsTimeForBuno(hand);

                if (isbuno) {
                    unoGame.setIsBUnoLastTurnPlayed(true);
                }

                int cardToPlay;
                try {
                    cardToPlay = AIHelper.findValidCard(
                            hand, discardCard);
                    unoGame.playCard(playerIndex, cardToPlay);
                } catch (NoValidCardException ex) {
                    /*case PLAYABLECARD is only possible when a valid card is
                     * locatable, exception cannot be thrown and if it is,
                     * there an unrecoverable error */
                    System.exit(-1);
                }

                break;
        }
    }
}
