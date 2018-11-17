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
* File: GameCLIDisplayBoard
* Description:
*
* ****************************************
 */
package unogame;

import deck.card.Card;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * A helper file for the Game CLI
 *
 * @author Lily Romano
 */
public final class GameCLIDisplayBoard {

    /**
     * Displays the game board as of the current game state
     *
     * @author Lily Romano
     * @param g The {@code Game} to display
     */
    public static void displayGameBoard(Game g) {
        //Assumes Player is player 1 and computers are remaining players
        System.out.println("===================================================");

        //Display information about various decks
        String drawDeck;
        if (g.theDiscardDeck.getDeckSize() == 0) {
            drawDeck = "No Draw Deck";
        }
        else {
            drawDeck = "*";
        }
        String discardDeck = easyCardDescription(
                g.theDiscardDeck.peekBottomCard());
        System.out.printf("| Draw Deck: %s  Discard Deck: %s%n|%n", drawDeck,
                          discardDeck);

        //Display information about the other players
        if (g.getNumComputerPlayers() > 0) {
            System.out.println("| Other Players");
            for (int i = 1; i <= g.getNumComputerPlayers(); i++) {
                System.out.printf("|   Player %d: Cards ", i);
                for (int j = 0; j < g.getPlayersHandCopy(i + 1).size(); j++) {
                    System.out.print("*");
                }
                System.out.println();
            }
            System.out.println("|");
        }
        System.out.println("| Your Hand\n|");
        CopyOnWriteArrayList<Card> playerHand = g.getPlayersHandCopy(1);
        System.out.print("|");
        for (int i = 0; i < playerHand.size(); i++) {
            String cardText = String.format("%1$-" + 20 + "s",
                                            easyCardDescription(
                                                    playerHand.get(i)));
            System.out.printf("  %d) %s", i + 1, cardText);
            int numCols = 3;
            if (i % numCols == (numCols - 1) || i == playerHand.size() - 1) {
                System.out.print("\n|");
            }
        }
        System.out.println();
        System.out.println("===================================================");
    }

    private static String easyCardDescription(Card card) {
        return properCase(
                card.getColor().toString() + " " + card.getType().toString());
    }

    private static String properCase(String string) {
        String[] words = string.split("\\s+");
        String result = "";
        for (int i = 0; i < words.length; i++) {
            String s = words[i];
            words[i] = s.toUpperCase().charAt(0) + s.substring(1, s.length()).toLowerCase();
        }
        return String.join(" ", words);
    }
}
