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
import unogame.GameStartedException;
import unogame.NoValidCardException;
import unogame.RoundOverException;

/**
 * A GUI Card Prototype MVC model Main GUI
 *
 * @author Lily Romano
 */
public class UNOGameModel {

    /**
     * The game object which runs the game
     */
    private Game unoGame;

    /**
     * Total number of computer players
     */
    private final int NUM_OF_COMPUTER_PLAYERS = 3;

    /**
     * The Player idNum for the main player to be drawn at the bottom of the
     * play board.
     */
    private final int MAIN_PLAYER_ID = 1;

    /**
     * Stores if it's a computer player's turn. Used by the controller to
     * restrict what can be clicked.
     */
    private boolean isComputerTurn;

    /**
     * An explicit constructor for the UNO Main GUI Model
     */
    public UNOGameModel() {
        boolean isDEBUG = true;
        unoGame = new Game(isDEBUG);
        makeNewDefaultGame();
        unoGame.startGame();
        isComputerTurn = false;
    }

    /**
     * Returns if it's the computer's turn.
     *
     * @return true if it's the computer's turn, otherwise false
     */
    public boolean isIsComputerTurn() {
        return isComputerTurn;
    }

    /**
     * Sets if it's the computer's turn.
     *
     * @param isComputerTurn true if it's the computer's turn, otherwise false
     */
    public void setIsComputerTurn(boolean isComputerTurn) {
        this.isComputerTurn = isComputerTurn;
    }

    /**
     * Returns a reference to the game object.
     *
     * @return a reference to the game object.
     */
    public Game getUnoGame() {
        return unoGame;
    }

    /**
     * Returns the player ID of the active player
     *
     * @return
     */
    public int getMAIN_PLAYER_ID() {
        return MAIN_PLAYER_ID;
    }

    /**
     * Creates a new default game with one human player and three computer
     * players
     */
    private void makeNewDefaultGame() {
        try {
            //create player
            unoGame.makePlayer(PlayerHand.HUMAN);

            //create computer players
            for (int i = 0; i < NUM_OF_COMPUTER_PLAYERS; i++) {
                unoGame.makePlayer(PlayerHand.COMPUTER);
            }

        } catch (GameStartedException ex) {
            //Unable to play game if unable to add players - should never be hit
            System.out.println("Error: " + ex);
            System.exit(-1);
        }
    }

    /**
     * Takes a card from the draw deck and adds it to the players hand.
     */
    public void tryToDrawCardAction() {

        try {
            unoGame.drawCard(MAIN_PLAYER_ID);
        } catch (NoValidCardException ex) {
            MessagePopup.display("No cards remaining!",
                                 "No cards left to draw from! \n\nRound is over");
        }
    }

    /**
     * Attempt to play a card. Displays a message if it's not legal. If it's
     * legal, the card is play and turn moves to the next player.
     *
     * @param playCardIndex
     * @return true if the play was legal, otherwise false.
     */
    public boolean tryToPlayCardAction(int playCardIndex) {
        if (!unoGame.isLegalPlay(unoGame.getPlayersHandCopy(MAIN_PLAYER_ID).get(
                playCardIndex))) {
            MessagePopup.display("Invalid Card!",
                                 "Play by the rules! \n\nEnter a valid card or click on the deck to draw.");
            return false;
        }
        else {
            unoGame.playCard(MAIN_PLAYER_ID, playCardIndex);
            return true;

        }
    }

    /**
     * Checks to see if it's the end of a round. If it is, it processes the end
     * of round tasks
     *
     * @throws RoundOverException
     */
    public void checkAndRunEndOfTurn() throws RoundOverException {
        //NOTE should be moved into Game.java and handled seperatly.
        int winningPlayerID = 0;
        boolean isEndOfGame = false;

        //Loop through all players
        for (int i = 1; i <= unoGame.getNumComputerPlayers() + unoGame.getNumHumanPlayers(); i++) {
            if (unoGame.getPlayersHandCopy(i).size() == 0) {
                winningPlayerID = i;
            }
        }

        //If there is a winner
        if (winningPlayerID > 0) {
            //score it
            isEndOfGame = unoGame.updateScorePanel(winningPlayerID - 1);
            //check to see if it's the end of game.
            if (isEndOfGame) {
                GameOverPopup.display();
            }
            //otherwise process the end of the round
            else {
                MessagePopup.display("Round is over!", "Round over!");
                try {
                    unoGame.startRound();
                } catch (GameNotStartedException ex) {
                    //Should never be hit
                    System.out.println("Error: " + ex);
                    System.exit(-1);
                }
            }

            //Throw exception to be caught by controller.  Neccessary to pass between threads.
            throw new RoundOverException(
                    "The round is over and the player" + winningPlayerID + "has won.");
        }
    }
}
