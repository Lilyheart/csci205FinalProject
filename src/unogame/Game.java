/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: James Kelly, Scott Little, Rachel Wang, Lily Romano
* Date: Nov 14, 2018
* Time: 11:56:13 AM
*
* Project: csci205FinalProject
* Package: unogame
* File: Game
* Description:
*
* ****************************************
 */
package unogame;

import deck.DiscardDeck;
import deck.DrawDeck;
import deck.EmptyDeckException;
import deck.PlayerHand;
import deck.card.Card;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author Lily Romano
 */
public class Game {

    /**
     * The Draw Deck for the game
     */
    protected DrawDeck theDrawDeck;

    /**
     * The Draw Deck for the game
     */
    protected DiscardDeck theDiscardDeck;

    /**
     * An {@code ArrayList} of player
     */
    private ArrayList<PlayerHand> players;

    /**
     * The total number of players. Also used to set the player's idNum.
     */
    private static int numPlayers;

    /**
     * True if the game has started, otherwise false
     */
    private Boolean isGameStarted;

    /**
     * An explicit constructor for a new game.
     */
    public Game() {
        //Instantiate variables
        players = new ArrayList<>();
        numPlayers = 0;
        isGameStarted = false;

        //create draw decks
        theDrawDeck = new DrawDeck();
        theDiscardDeck = new DiscardDeck();
        //shuffle draw deck
        theDrawDeck.shuffle();

    }

    /**
     *
     * @return
     */
    public int getNumHumanPlayers() {
        int result = 0;

        for (PlayerHand h : players) {
            if (!h.isComputerPlayer()) {
                result++;
            }
        }
        return result;
    }

    /**
     *
     * @return
     */
    public int getNumComputerPlayers() {
        int result = 0;

        for (PlayerHand h : players) {
            if (h.isComputerPlayer()) {
                result++;
            }
        }
        return result;
    }

    /**
     *
     * @param playerID
     * @return
     */
    public CopyOnWriteArrayList<Card> getPlayersHandCopy(int playerIndex) {
        int playerID = playerIndex + 1;
        return players.get(playerID).getCopyOfHand();
    }

    public void startGame() {
        isGameStarted = true;
        try {
            theDiscardDeck.addCard(theDrawDeck.popNextCard());
        } catch (EmptyDeckException ex) {
            //Unable to play game if unable to create discard pile
            System.out.println(ex);
            System.exit(-1);
        }
    }

    /**
     * Makes a new human player
     *
     * @param isComputerPlayer true of the player is a computer player,
     * otherwise false
     * @throws deck.EmptyDeckException
     */
    public void makePlayer(Boolean isComputerPlayer) throws EmptyDeckException, GameNotStartedException {
        //TODO [Exception Handling]
        if (isGameStarted) {
            String player = isComputerPlayer ? "computer" : "human";
            throw new GameNotStartedException(
                    "Attempting to create a " + player + " player after the game has started");
        }
        numPlayers++;
        PlayerHand newPlayer = new PlayerHand(numPlayers, isComputerPlayer);

        for (int i = 0; i < PlayerHand.NEWHANDCARDNUM; i++) {
            newPlayer.addCard(theDrawDeck.popNextCard());
        }

        players.add(newPlayer);
    }
}
