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
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Creates and manages an unoGame
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
    private final LinkedList<PlayerHand> players;

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
     *
     * @author Lily Romano
     */
    public Game() {
        //Instantiate variables
        players = new LinkedList<>();
        numPlayers = 0;
        isGameStarted = false;

        //create draw decks
        theDrawDeck = new DrawDeck();
        theDiscardDeck = new DiscardDeck();
        //shuffle draw deck
        theDrawDeck.shuffle();

    }

    /**
     * Returns the total number of human players.
     *
     * @author Lily Romano
     *
     * @return the total number of human players.
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
     * Returns the total number of computer players.
     *
     * @author Lily Romano
     *
     * @return the total number of computer players.
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
     * Returns a copy of the player's hand.
     *
     * @author Lily Romano
     *
     * @param playerID The playerID of the player's hand to return.
     * @return a copy of the player's hand.
     */
    public CopyOnWriteArrayList<Card> getPlayersHandCopy(int playerID) {
        int playerIndex = playerID - 1;
        return players.get(playerIndex).getCopyOfHand();
    }

    /**
     * Starts a new game by popping the top card of the draw deck onto the
     * discard deck
     *
     * @author Lily Romano
     */
    public void startGame() {
        isGameStarted = true;
        try {
            theDiscardDeck.addCard(theDrawDeck.popTopCard());
        } catch (EmptyDeckException ex) {
            //Unable to play game if unable to create discard pile
            System.out.println(ex);
            System.exit(-1);
        }
    }

    /**
     * Makes a new human player
     *
     * @author Lily Romano
     *
     * @param isComputerPlayer true of the player is a computer player,
     * otherwise false
     * @throws deck.EmptyDeckException
     * @throws unogame.GameNotStartedException
     */
    public void makePlayer(Boolean isComputerPlayer) throws EmptyDeckException, GameNotStartedException {
        //TODO [Exception Handling]
        numPlayers++;

        if (isGameStarted) {
            String player = isComputerPlayer ? "computer" : "human";
            throw new GameNotStartedException(
                    "Attempting to create a " + player + " player after the game has started");
        }
        PlayerHand newPlayer = new PlayerHand(numPlayers, isComputerPlayer);

        for (int i = 0; i < PlayerHand.NEWHANDCARDNUM; i++) {
            newPlayer.addCard(theDrawDeck.popTopCard());
        }

        players.add(newPlayer);
    }

    /**
     * Plays a card from a player's hand
     *
     * @author Lily Romano
     *
     * @param playerID the player ID [Starting from 1]
     * @param cardIndex the index of the card to play
     * @throws EmptyDeckException
     */
    public void playCard(int playerID, int cardIndex) throws EmptyDeckException {
        //TODO [Basic Game] Add Rules
        int playerIndex = playerID - 1;
        theDiscardDeck.addCard(
                players.get(playerIndex).popCardAtIndex(cardIndex));
    }

    /**
     * Draws a card from the draw deck and adds it to the player's hand
     *
     * @author Lily Romano
     *
     * @param playerID the player ID [Starting from 1]
     * @throws EmptyDeckException
     */
    public void drawCard(int playerID) throws EmptyDeckException {
        int playerIndex = playerID - 1;
        players.get(playerIndex).addCard(theDrawDeck.popTopCard());
    }

    /**
     * Shuffles the discard pile into the draw deck and turns over the top card.
     *
     * @author Lily Romano
     */
    public void shuffleDiscardToDrawDeck() {
        theDrawDeck.addCards(theDiscardDeck.removeAllCards());
        try {
            theDiscardDeck.addCard(theDrawDeck.popTopCard());
        } catch (EmptyDeckException ex) {
            //Unable to play game if unable to create discard pile
            System.out.println(ex);
            System.exit(-1);
        }
    }

    /**
     * Tests to see if card is a legal play
     *
     * @param playerCard the {@code Card to test}
     * @return True if the play is legal, otherwise false
     */
    public boolean isLegalPlay(Card playerCard) {
        boolean isLegal = false;
        Card discardCard = theDiscardDeck.peekBottomCard();
        System.out.println(playerCard.getColor() + " " + playerCard.getType());

        //test color
        if (playerCard.getColor() == discardCard.getColor()) {
            isLegal = true;
        }
        //test number
        else if (playerCard.getType() == discardCard.getType()) {
            isLegal = true;
        }

        return isLegal;
    }
}
