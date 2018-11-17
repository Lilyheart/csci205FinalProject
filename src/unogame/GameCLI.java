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
import java.util.Scanner;

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

    static Scanner keyboard = new Scanner(System.in);

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
        setNewDefaultGame(unoGame);

        unoGame.startGame();

        GameCLIDisplayBoard.displayGameBoard(unoGame);

        //TODO [Rules] This only plays the card.  Can't draw if no match or renege etc
        int cardPosition = getPlayCommand(unoGame);
        int cardIndex = cardPosition - 1;

        unoGame.playCard(1, cardIndex);

        GameCLIDisplayBoard.displayGameBoard(unoGame);
    }

    private static void setNewDefaultGame(Game g) throws EmptyDeckException {
        try {
            //create player
            g.makePlayer(PlayerHand.HUMAN);
            //create computer players
            for (int i = 0; i < NUM_OF_COMPUTER_PLAYERS; i++) {
                g.makePlayer(PlayerHand.COMPUTER);
            }
        } catch (GameNotStartedException ex) {
            //Unable to play game if unable to add players - should never be hit
            System.out.println(ex);
            System.exit(-1);
        }
    }

    private static int getPlayCommand(Game g) {

        System.out.println(
                "TESTING ONLY MESSAGE: Type the number of the card you wish to play.  Rules don't matter > ");

        int response = -1;
        while (response < 1 || response > g.getPlayersHandCopy(1).size()) {
            if (keyboard.hasNextInt()) {
                response = keyboard.nextInt();
                if (response < 1 || response > g.getPlayersHandCopy(1).size()) {
                    System.out.println(">> Input a valid option.");
                }
            }
            else {
                System.out.println(">> You didn't input a number.  "
                                   + "Please input a number");
            }

        }

        return response;
    }
}
