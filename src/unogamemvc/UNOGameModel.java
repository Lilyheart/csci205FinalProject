/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: James Kelly, Scott Little, Rachel Wang, Lily Romano
* Date: Nov 9, 2018
* Time: 12:40:08 PM
*
* Project: csci205FinalProject
* Package: view
* File: UNOGameModel
* Description:
*
* ****************************************
 */
package unogamemvc;

import deck.PlayerHand;
import unogame.Game;
import unogame.GameNotStartedException;
import unogame.RoundOverException;

/**
 * A GUI Card Prototype MVC model Main GUI
 *
 * @author Lily Romano
 */
public class UNOGameModel {

    /**
     * Total number of computer players
     */
    private final int NUM_OF_COMPUTER_PLAYERS = 3;

    /**
     * The Player idNum for the human player
     */
    private final int HUMAN_PLAYER = 1;

    private Game unoGame;

    private InvalidPlayPopup invalidPlayPopup;
    private GameOverPopup gameOverPopup;
    private RoundOverPopup roundOverPopup;

    private boolean isComputerTurn;

    /**
     * An explicit constructor for the UNO Main GUI Model
     */
    public UNOGameModel() {
        //TODO [!Finalize]
        boolean isDEBUG = true;
        unoGame = new Game(isDEBUG);
        makeNewDefaultGame();
        unoGame.startGame();
        //TODO [GUI] get starting player
        isComputerTurn = false;
        this.invalidPlayPopup = new InvalidPlayPopup();
        this.gameOverPopup = new GameOverPopup();
        this.roundOverPopup = new RoundOverPopup(unoGame);

    }

    public boolean isIsComputerTurn() {
        return isComputerTurn;
    }

    public void setIsComputerTurn(boolean isComputerTurn) {
        this.isComputerTurn = isComputerTurn;
    }

    public int getNUM_OF_COMPUTER_PLAYERS() {
        return NUM_OF_COMPUTER_PLAYERS;
    }

    public Game getUnoGame() {
        return unoGame;
    }

    public int getHUMAN_PLAYER() {
        return HUMAN_PLAYER;
    }

    public InvalidPlayPopup getInvalidPlayPopup() {
        return invalidPlayPopup;
    }

    /**
     * Creates a new default game
     */
    private void makeNewDefaultGame() {
        try {
            //create player
            unoGame.makePlayer(PlayerHand.HUMAN);
            //create computer players
            for (int i = 0; i < NUM_OF_COMPUTER_PLAYERS; i++) {
                unoGame.makePlayer(PlayerHand.COMPUTER);
            }
        } catch (GameNotStartedException ex) {
            //TODO Except
            //Unable to play game if unable to add players - should never be hit
            System.out.println("Error: " + ex);
            System.exit(-1);
        }
    }

    /**
     * Takes a card from the draw deck and adds it to the players hand.
     */
    public void tryToDrawCardAction() {
        //TODO [Basic Game]
        boolean isDrawSuccessful = false;
        //TODO [Basic Game] If there are no cards in the discard or draw piles this loop will be infinite
        while (!isDrawSuccessful) {
            unoGame.drawCard(HUMAN_PLAYER);
            isDrawSuccessful = true;
        }
    }

    public boolean tryToPlayCardAction(int playCardIndex) {
        if (!unoGame.isLegalPlay(unoGame.getPlayersHandCopy(
                HUMAN_PLAYER).get(playCardIndex))) {
            invalidPlayPopup.display();
            return false;
        }
        else {
            unoGame.playCard(HUMAN_PLAYER, playCardIndex);
            return true;

        }
    }

    public void checkAndRunEndOfTurn() throws RoundOverException {
        //TODO [???] s/b in Game
        //TODO HANDLE
        int winningPlayerID = 0;
        boolean isEndOfGame = false;
        //Loop through all players
        for (int i = 1; i <= unoGame.getNumComputerPlayers() + unoGame.getNumHumanPlayers(); i++) {
            if (unoGame.getPlayersHandCopy(i).size() == 0) {
                winningPlayerID = i;
            }
        }

        if (winningPlayerID > 0) {
            //If any player's hand size is zero
            isEndOfGame = unoGame.updateScorePanel(winningPlayerID - 1);
            if (isEndOfGame) {
                //If score > 500, end game
                gameOverPopup.display();
            }
            else {
                roundOverPopup.display();
                unoGame.startRound();
            }
            throw new RoundOverException();
        }
    }
}
