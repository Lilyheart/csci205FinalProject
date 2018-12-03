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
* Description: Score panel for tracking the scores and identify the potential winner
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

    /**
     * the playerList
     */
    private final LinkedList<PlayerHand> playerList;

    /**
     * An integer array that holds the scores.
     */
    private final int[] scores;

    /**
     * The score when a game ends
     */
    private final int TOPSCORE = 500;

    /**
     * Constructor for the score panel.
     *
     * @author Rachel Wang
     *
     * @param playerList A reference of the playerList in the {@code Game class}
     */
    public ScorePanel(LinkedList<PlayerHand> playerList) {
        //Link the playerList from the game to the scorepanel
        this.playerList = playerList;

        //Create an array of scores the list of playerList
        scores = new int[playerList.size()];
    }

    /**
     * Update scores and returns true if game is over and false if another round
     * should begin
     *
     * @author Rachel Wang
     *
     * @param winnerPlayerIndex the index of the winning player
     * @return true if game is over and false if another round should begin
     */
    public boolean updateScores(int winnerPlayerIndex) {
        int awardScore = 0;

        //Score hands
        for (int i = 0; i < playerList.size(); i++) { //for each player
            PlayerHand playerHand = this.playerList.get(i);
            for (int j = 0; j < playerHand.getDeckSize(); j++) { //for each card
                awardScore += playerHand.getCopyOfHand().get(j).getType().getCardPointValue();
            }
        }

        //Add score to scores array
        this.scores[winnerPlayerIndex] += awardScore;
        //Determine if game is over

        return (this.scores[winnerPlayerIndex] >= TOPSCORE);
    }

    /**
     * Returns the score for a player at the index
     *
     * @author Rachel Wang
     *
     * @param playerIndex the index of the player (first player = 0)
     * @return the score of the player
     */
    public int getScore(int playerIndex) {
        return scores[playerIndex];
    }

}
