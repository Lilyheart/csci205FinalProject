/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: James Kelly, Scott Little, Rachel Wang, Lily Romano
* Date: Nov 28, 2018
* Time: 12:03:10 AM
*
* Project: csci205FinalProject
* Package: unogame
* File: ScorePanel
* Description:
*
* ****************************************
 */
package unogame;

import deck.PlayerHand;
import java.util.LinkedList;

/**
 * Score panel for tracking the scores and identify the potential winner
 *
 * @author Rachel Wang
 */
public class ScorePanel {

    private LinkedList<PlayerHand> playerList;
    private int[] scores;
    private final int TOPSCORE = 500;

    /**
     * Constructor for the score panel
     *
     * @param playerList
     */
    public ScorePanel(LinkedList<PlayerHand> playerList) {
        scores = new int[playerList.size()];
        for (int i = 0; i < playerList.size(); i++) {
            scores[i] = 0;
        }
        this.playerList = playerList;
    }

    /**
     * update scores as playing and returns false if no one has won yet and
     * returns true once someone wins.
     *
     * @param winnerPlayerIndex
     * @return if there is a winner or not
     */
    public boolean updateScore(int winnerPlayerIndex) {

        int awardScore = 0;
        for (int i = 0; i < playerList.size(); i++) { //for each player
            PlayerHand playerHand = this.playerList.get(i);
            for (int j = 0; j < playerHand.getDeckSize(); j++) { //for each card
                awardScore += playerHand.getCopyOfHand().get(j).getType().getCardPointValue();
            }
        }
        this.scores[winnerPlayerIndex] += awardScore;
        if (this.scores[winnerPlayerIndex] >= TOPSCORE) {
            return true;
        }
        return false;
    }

    /**
     * get the score for a player
     *
     * @param playerIndex
     * @return the score of the player
     */
    public int getScores(int playerIndex) {
        return scores[playerIndex];
    }

}
