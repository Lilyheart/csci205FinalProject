/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: James Kelly, Scott Little, Rachel Wang, Lily Romano
* Date: Nov 16, 2018
* Time: 11:36:56 AM
*
* Project: csci205FinalProject
* Package: unogame
* File: GameCLIHelper
* Description:
*
* ****************************************
 */
package unogame;

/**
 * A helper file for the Game CLI
 *
 * @author Lily Romano
 */
public final class GameCLIHelper {

    /**
     * Displays the game board as of the current game state
     *
     * @author Lily Romano
     * @param g The {@code Game} to display
     */
    public static void displayGameBoard(Game g) {
        //Assumes Player is player 1 and computers are remaining players
        System.out.println("===================================================");
        String drawDeck = "| TODO";
        System.out.printf("| Draw Deck: %s  Discard Deck: TODO %n", drawDeck);
        System.out.println("| Other Players\n|");
        for (int i = 0; i < g.numComputerPlayers(); i++) {
            System.out.printf("| Player %d: Cards ", i + 2);
            for (int j = 0; j < g.getPlayersHandCopy(i).size(); j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        System.out.println("|");
        System.out.println("| Your Hand\n|");
        System.out.println("===================================================");
    }

}
