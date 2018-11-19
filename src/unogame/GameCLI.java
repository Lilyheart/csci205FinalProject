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
import deck.card.Card;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * A CLI version of the BUno game.
 *
 * @author Lily Romano
 */
public class GameCLI {

    /**
     * Total number of computer players
     */
    private final static int NUM_OF_COMPUTER_PLAYERS = 3;

    /**
     * Plays a command line version of the game
     */
    private static Game unoGame;

    private final static int HUMAN_PLAYER = 1;

    private static Scanner keyboard = new Scanner(System.in);

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
        unoGame = new Game();
        setNewDefaultGame(unoGame);

        unoGame.startGame();

        //TODO [Basic Game] Change this infinite loop
        while (true) {
            playerTurn();
        }
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

    private static void playerTurn() {
        displayGameBoard();

        int playCommand = getPlayCommand();
        switch (playCommand) {
            case 1: //Play Card
                int playCard = getPlayCard();
                try {
                    unoGame.playCard(HUMAN_PLAYER, playCard - 1);
                } catch (EmptyDeckException ex) {
                    //playCard is already limited to only cards in the hand.  This should never be hit.
                    System.out.println(ex);
                    System.exit(-1);
                }
                break;
            case 2: //Draw Card
                //TODO [Basic Game]
                break;
        }

    }

    /**
     * Displays the game board as of the current game state
     *
     * @author Lily Romano
     * @param g The {@code Game} to display
     */
    public static void displayGameBoard() {
        //Assumes Player is player 1 and computers are remaining players
        System.out.println("===================================================");

        //Display information about various decks
        String drawDeck;
        if (unoGame.theDiscardDeck.getDeckSize() == 0) {
            drawDeck = "No Draw Deck";
        }
        else {
            drawDeck = "*";
        }
        String discardDeck = easyCardDescription(
                unoGame.theDiscardDeck.peekBottomCard());
        System.out.printf("| Draw Deck: %s  Discard Deck: %s%n|%n", drawDeck,
                          discardDeck);

        //Display information about the other players
        if (unoGame.getNumComputerPlayers() > 0) {
            System.out.println("| Other Players");
            for (int i = 1; i <= unoGame.getNumComputerPlayers(); i++) {
                System.out.printf("|   Player %d: Cards ", i);
                for (int j = 0; j < unoGame.getPlayersHandCopy(i + 1).size(); j++) {
                    System.out.print("*");
                }
                System.out.println();
            }
            System.out.println("|");
        }
        System.out.println("| Your Hand\n|");
        displayPlayersHand();

        System.out.println("===================================================");
    }

    private static void displayPlayersHand() {
        CopyOnWriteArrayList<Card> playerHand = unoGame.getPlayersHandCopy(
                HUMAN_PLAYER);
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
    }

    private static int getPlayCommand() {
        if (unoGame.getPlayersHandCopy(HUMAN_PLAYER).size() == 0) {
            System.out.println(
                    ">>Select an option: \n  1. Draw a card    2. Call Uno");
            return getKeyboardInt(2);
        }
        else {
            System.out.println(
                    ">>Select an option: \n  1. Play a card     2. Draw a card    3. Call Uno");
            return getKeyboardInt(3);
        }
    }

    private static int getPlayCard() {
        System.out.println(
                ">>Select an card to play: \n");

        return getKeyboardInt(unoGame.getPlayersHandCopy(HUMAN_PLAYER).size());
    }

    protected static String easyCardDescription(Card card) {
        return properCase(
                card.getColor().toString() + " " + card.getType().toString());
    }

    protected static String properCase(String string) {
        String[] words = string.split("\\s+");
        String result = "";
        for (int i = 0; i < words.length; i++) {
            String s = words[i];
            words[i] = s.toUpperCase().charAt(0) + s.substring(1, s.length()).toLowerCase();
        }
        return String.join(" ", words);
    }

    private static int getKeyboardInt(int maxValue) {
        int response = -1;

        while (response < 1 || response > maxValue) {
            if (keyboard.hasNextInt()) {
                response = keyboard.nextInt();
                if (response < 1 || response > unoGame.getPlayersHandCopy(
                        HUMAN_PLAYER).size()) {
                    System.out.print(">> Input a valid option");
                }
            }
            else {
                System.out.print(">> You didn't input a number.  "
                                 + "Please input a number");
            }
        }

        return response;
    }

}
