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
import unogame.playhelpers.CLIHelper;

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

    /**
     * The Player idNum for the human player
     */
    private final static int HUMAN_PLAYER = 1;

    /**
     * A scanner for the keyboard input
     */
    private static Scanner keyboard;

    /**
     * Runs the CLI version of the UNO game. Assumes one human player and set
     * number of computer players
     *
     * @param args the command line arguments
     * @throws deck.EmptyDeckException
     */
    public static void main(String[] args) throws EmptyDeckException {
        //TODO [Exception Handling]
        keyboard = new Scanner(System.in);

        //create new game
        unoGame = new Game();
        setNewDefaultGame();

        unoGame.startGame();

        //TODO [Basic Game] Change this infinite loop
        while (true) {
            //Add whitespace to console screen
            for (int i = 0; i < 20; ++i) {
                System.out.println();
            }

            //Process player's turn
            humanPlayerTurn();

            //Process computer's turn
            //TODO [Basic Game] Computer's turn
        }
    }

    /**
     * Creates a new default game
     *
     * @throws EmptyDeckException
     */
    private static void setNewDefaultGame() throws EmptyDeckException {
        try {
            //create player
            unoGame.makePlayer(PlayerHand.HUMAN);
            //create computer players
            for (int i = 0; i < NUM_OF_COMPUTER_PLAYERS; i++) {
                unoGame.makePlayer(PlayerHand.COMPUTER);
            }
        } catch (GameNotStartedException ex) {
            //Unable to play game if unable to add players - should never be hit
            System.out.println(ex);
            System.exit(-1);
        }
    }

    /**
     * Completes a human player's turn
     */
    private static void humanPlayerTurn() {
        displayGameBoard();

        PlayCommand playCommand = getPlayCommand();
        switch (playCommand) {
            case PLAYCARD: //Play Card
                boolean cardIsLegal = false;
                while (!cardIsLegal) {
                    int playCardIndex = getPlayCard();
                    if (!unoGame.isLegalPlay(unoGame.getPlayersHandCopy(
                            HUMAN_PLAYER).get(playCardIndex))) {
                        System.out.println("Invalid play");
                        continue;
                    }
                    try {
                        unoGame.playCard(HUMAN_PLAYER, playCardIndex);
                        cardIsLegal = true;
                    } catch (EmptyDeckException ex) {
                        //playCard is already limited to only cards in the hand.  This should never be hit.
                        System.out.println(ex);
                        System.exit(-1);
                    }
                }
                break;
            case DRAW: //Draw Card
                //TODO [Basic Game]
                boolean isDrawSuccessful = false;
                //TODO [Basic Game] If there are no cards in the discard or draw piles this loop will be infinite
                while (!isDrawSuccessful) {
                    try {
                        unoGame.drawCard(HUMAN_PLAYER);
                        isDrawSuccessful = true;
                    } catch (EmptyDeckException ex) {
                        unoGame.shuffleDiscardToDrawDeck();
                    }
                }
                break;
            case BUNO:
                //TODO [Basic Game] Call Buno
                break;
        }

    }

    /**
     * Displays the game board as of the current game state
     *
     * @author Lily Romano
     */
    public static void displayGameBoard() {
        //Assumes Player is player 1 and computers are remaining players
        System.out.println("===================================================");

        //Display information about various decks
        String drawDeck;
        String discardDeck;
        if (unoGame.theDrawDeck.getDeckSize() == 0) {
            drawDeck = "No Draw Deck";
        }
        else {
            drawDeck = "*";
        }
        if (unoGame.theDiscardDeck.getDeckSize() == 0) {
            discardDeck = "*";
        }
        else {
            discardDeck = CLIHelper.easyCardDescription(
                    unoGame.theDiscardDeck.peekBottomCard());
        }

        System.out.printf("| Draw Deck: %s  Discard Deck: %s%n|%n", drawDeck,
                          discardDeck);

        //Display information about the other players
        if (unoGame.getNumComputerPlayers() > 0) {
            System.out.println("| Other Players");
            for (int i = 1; i <= unoGame.getNumComputerPlayers(); i++) {
                System.out.printf("|   Player %d: Cards ", i);
                for (Card playersHandCopy : unoGame.getPlayersHandCopy(i + 1)) {
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

    /**
     * Displays the cards in the player's hand
     */
    private static void displayPlayersHand() {
        CopyOnWriteArrayList<Card> playerHand = unoGame.getPlayersHandCopy(
                HUMAN_PLAYER);
        System.out.print("|");
        for (int i = 0; i < playerHand.size(); i++) {
            String cardText = String.format("%1$-" + 20 + "s",
                                            CLIHelper.easyCardDescription(
                                                    playerHand.get(i)));
            System.out.printf("  %d) %s", i + 1, cardText);
            int numCols = 3;
            if (i % numCols == (numCols - 1) || i == playerHand.size() - 1) {
                System.out.print("\n|");
            }
        }
        System.out.println();
    }

    /**
     * Gets what type of play the player wishes to make
     *
     * @return an integer representing the play command
     */
    private static PlayCommand getPlayCommand() {
        //TODO [Basic Game] Set up enums instead of int?
        if (unoGame.getPlayersHandCopy(HUMAN_PLAYER).isEmpty()) {
            System.out.println(
                    ">>Select an option: \n  1. Draw a card    2. Call Uno");
            int keyboardInt = CLIHelper.getKeyboardInt(2);
            switch (keyboardInt) {
                case 1:
                    return PlayCommand.DRAW;
                case 2:
                    return PlayCommand.BUNO;
            }
        }
        //else
        System.out.println(
                ">>Select an option: \n  1. Play a card     2. Draw a card    3. Call Uno");

        int keyboardInt = CLIHelper.getKeyboardInt(3);
        switch (keyboardInt) {
            case 1:
                return PlayCommand.PLAYCARD;
            case 2:
                return PlayCommand.DRAW;
            case 3:
            default:
                return PlayCommand.BUNO;
        }
    }

    /**
     * Gets what card the player wishes to play
     *
     * @return an the index of the card in the player's hand.
     */
    private static int getPlayCard() {
        String discardDeck;
        if (unoGame.theDiscardDeck.getDeckSize() == 0) {
            discardDeck = "*";
        }
        else {
            discardDeck = CLIHelper.easyCardDescription(
                    unoGame.theDiscardDeck.peekBottomCard());
        }

        System.out.println("===================================================");
        System.out.println(
                "|\n| >>Select an card to play: ");
        System.out.println("|    Discard Pile: " + discardDeck + "\n| ");

        displayPlayersHand();
        System.out.println("===================================================");

        return CLIHelper.getKeyboardInt(
                unoGame.getPlayersHandCopy(HUMAN_PLAYER).size()) - 1;
    }
}
