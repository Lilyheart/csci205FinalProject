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
    private DrawDeck theDrawDeck;

    /**
     * An {@code ArrayList} of player
     */
    private ArrayList<PlayerHand> players;

    /**
     * The total number of players. Also used to set the player's idNum.
     */
    private static int numPlayers;

    /**
     * An explicit constructor for a new game.
     */
    public Game() {
        //Instantiate variables
        players = new ArrayList<>();
        numPlayers = 0;

        //create draw deck
        theDrawDeck = new DrawDeck();
        //shuffle draw deck
        theDrawDeck.shuffle();

    }

    /**
     * Makes a new human player
     *
     * @param isComputerPlayer true of the player is a computer player,
     * otherwise false
     * @throws deck.EmptyDeckException
     */
    public void makePlayer(Boolean isComputerPlayer) throws EmptyDeckException {
        //TODO [Exception Handling]

        numPlayers++;
        PlayerHand newPlayer = new PlayerHand(numPlayers, isComputerPlayer);

        for (int i = 0; i < PlayerHand.NEWHANDCARDNUM; i++) {
            newPlayer.addCard(theDrawDeck.removeNextCard());
        }

        players.add(newPlayer);
    }

    /**
     * Prints the draw deck to the {@code System.out}.
     */
    public void printTheDrawDeck() {
        System.out.println(theDrawDeck);
    }

    /**
     *
     * @return
     */
    public int numHumanPlayers() {
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
    public int numComputerPlayers() {
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
    public CopyOnWriteArrayList<Card> getPlayersHandCopy(int playerID) {
        return players.get(playerID).getCopyOfHand();
    }

}
