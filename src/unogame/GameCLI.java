/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: James Kelly, Scott Little, Rachel Wang, Lily Romano
* Date: Nov 16, 2018
* Time: 11:32:56 AM
*
* Project: csci205FinalProject
* Package: unogame
* File: GameCLI
* Description:
*
* ****************************************
 */
package unogame;

import deck.EmptyDeckException;
import deck.PlayerHand;

/**
 * A CLI version of the BUno game.
 *
 * @author Lily Romano
 */
public class GameCLI {

    /**
     * Total number of computer players
     */
    public final static int NUM_OF_COMPUTER_PLAYERS = 3;

    /**
     * Plays a command line version of the game
     */
    Game unoGame;

    /**
     * Runs the CLI version of the UNO game. Assumes one human player and set
     * number of computer players
     *
     * @param args the command line arguments
     * @throws deck.EmptyDeckException
     */
    public static void main(String[] args) throws EmptyDeckException {
        //TODO [Exception Handling]

        //create new game
        Game unoGame = new Game();
        //create player
        unoGame.makePlayer(PlayerHand.HUMAN);
        //create computer players
        for (int i = 0; i < NUM_OF_COMPUTER_PLAYERS; i++) {
            unoGame.makePlayer(PlayerHand.COMPUTER);
        }

        //display hand [own method]
        GameCLIHelper.displayGameBoard(unoGame);
    }
}
