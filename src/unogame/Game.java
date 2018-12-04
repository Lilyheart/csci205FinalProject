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

import deck.AIintelligenceLevel;
import deck.DiscardDeck;
import deck.DrawDeck;
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
     * True if the game has started, otherwise false
     */
    private Boolean isGameStarted;

    /**
     * The ScorePanel object to maintain scores
     */
    private ScorePanel scorePanel;

    /**
     * Maintains if BUno was called on the last turn played
     */
    private boolean isBUnoLastTurnPlayed;

    /**
     * Determines if debug is printed to the console
     */
    private boolean printDEBUG;

    /**
     * An explicit constructor for a new game.
     *
     * @author Lily Romano
     */
    public Game() {
        //create game without debugging
        this(false);
    }

    /**
     * An explicit constructor for a new game.
     *
     * @author Lily Romano
     *
     * @param printDEBUG true if debugging comments should print to the console
     */
    public Game(boolean printDEBUG) {
        players = new LinkedList<>();
        isGameStarted = false;
        this.printDEBUG = printDEBUG;
    }

    /**
     * Returns the total number of players.
     *
     * @author Lily Romano
     *
     * @return the total number of players.
     */
    public int getNumPlayers() {
        return players.size();
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
     * Returns a copy of the player's hand as an array list
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
     * Returns a copy of the player's hand as a PlayerHand object
     *
     * @author Lily Romano
     *
     * @param playerID The playerID of the player's hand to return.
     * @return a copy of the player's hand.
     */
    protected PlayerHand getPlayerCopy(int playerID) {
        int playerIndex = playerID - 1;
        return players.get(playerIndex);
    }

    /**
     * Updates the scores on the {@code ScorePanel}
     *
     * @author aw029
     *
     * @param winnerPlayerIndex
     * @return true of the game is over, false otherwise
     */
    public boolean updateScorePanel(int winnerPlayerIndex) {
        return scorePanel.updateScores(winnerPlayerIndex);
    }

    /**
     * Gets the score of a player
     *
     * @author aw029
     *
     * @param playerIndex
     * @return
     */
    public int getScore(int playerIndex) {
        return scorePanel.getScore(playerIndex);
    }

// TODO [!BUno]
//    public Boolean getAndClearIsBUnoLastTurnPlayed() {
//        Boolean originalValue = isBUnoLastTurnPlayed;
//        isBUnoLastTurnPlayed = false;
//
//        return originalValue;
//    }
//
//    public void setIsBUnoLastTurnPlayed(Boolean isBUnoLastTurnPlayed) {
//        this.isBUnoLastTurnPlayed = isBUnoLastTurnPlayed;
//    }
//
    /**
     * Returns the {@code Card} on the discard pile
     *
     * @author Lily Romano
     *
     * @return the {@code Card} on the discard pile
     */
    public Card getDiscardCardCard() {
        return theDiscardDeck.peekBottomCard();
    }

    /**
     * Return printDEBUG status
     *
     * @author Lily Romano
     *
     * @return true if debug messages should be printed, otherwise false.
     */
    public boolean isPrintDEBUG() {
        return printDEBUG;
    }

    /**
     * Makes a new player
     *
     * @author Lily Romano
     *
     * @param isComputerPlayer true of the player is a computer player,
     * otherwise false
     * @throws unogame.GameStartedException
     */
    public void makePlayer(Boolean isComputerPlayer) throws GameStartedException {
        if (isGameStarted) {
            String player = isComputerPlayer ? "computer" : "human";
            throw new GameStartedException(
                    "Attempting to create a " + player + " player after the game has started");
        }

        PlayerHand newPlayer = new PlayerHand(isComputerPlayer);

        players.add(newPlayer);
    }

    /**
     * Makes a new computer player
     *
     * @author Lily Romano
     *
     * @param isComputerPlayer true of the player is a computer player,
     * otherwise false
     * @param intelligenceLevel the {@code AIintelligenceLevel} of the computer
     * player
     * @throws unogame.GameStartedException
     */
    public void makeComputerPlayer(Boolean isComputerPlayer,
                                   AIintelligenceLevel intelligenceLevel) throws GameStartedException {
        if (isGameStarted) {
            String player = isComputerPlayer ? "computer" : "human";
            throw new GameStartedException(
                    "Attempting to create a " + player + " player after the game has started");
        }

        PlayerHand newPlayer = new PlayerHand(isComputerPlayer,
                                              intelligenceLevel);

        players.add(newPlayer);
    }

    /**
     * Starts a new game by popping the top card of the draw deck onto the
     * discard deck
     *
     * @author Lily Romano
     */
    public void startGame() {
        isGameStarted = true;
        scorePanel = new ScorePanel(players);
        try {
            startRound();
        } catch (GameNotStartedException ex) {
            //isGameStarted variable is set above, this exception wil never happen here.
            System.out.println("Error: " + ex);
            System.exit(-1);
        }
    }

    /**
     * Starts a new round by recreating all the decks, shuffling and dealing
     * cards
     *
     * @author Lily Romano
     * @author James Kelly
     * @throws unogame.GameNotStartedException
     */
    public void startRound() throws GameNotStartedException {
        if (!isGameStarted) {
            throw new GameNotStartedException(
                    "Attempting to start a round before the game has started");
        }
        //create draw decks
        theDrawDeck = new DrawDeck();
        theDiscardDeck = new DiscardDeck();
        //shuffle draw deck
        theDrawDeck.shuffle();

        //set hands
        for (PlayerHand hand : players) {
            hand.removeAllCards();
            for (int i = 0; i < PlayerHand.NEWHANDCARDNUM; i++) {
                hand.addCard(theDrawDeck.popTopCard());
            }
        }

        //set up discard pile
        theDiscardDeck.addCard(theDrawDeck.popTopCard());

        if (printDEBUG) {
            System.out.println("New Round: \n" + this);
        }
    }

    /**
     * Plays a card from a player's hand
     *
     * @author Lily Romano
     *
     * @param playerID the player ID [Starting from 1]
     * @param cardIndex the index of the card to play
     */
    public void playCard(int playerID, int cardIndex) {
        int playerIndex = playerID - 1;

        if (printDEBUG) {
            System.out.printf(
                    "= Play Card on playerIndex %d: Discard card was %-15s == Playing card %-15s\n",
                    playerIndex, getDiscardCardCard(),
                    getPlayersHandCopy(playerID).get(cardIndex));
        }

        theDiscardDeck.addCard(
                players.get(playerIndex).popCardAtIndex(cardIndex));
    }

    /**
     * Draws a card from the draw deck and adds it to the player's hand
     *
     * @author Lily Romano
     *
     * @param playerID the player ID [Starting from 1]
     */
    public void drawCard(int playerID) {
        int playerIndex = playerID - 1;

        players.get(playerIndex).addCard(theDrawDeck.popTopCard());

        if (printDEBUG) {
            System.out.printf("= Draw Card on playerIndex %d: %-15s\n",
                              playerIndex,
                              getPlayerCopy(playerID).peekBottomCard());
        }
    }

    /**
     * Shuffles the discard pile into the draw deck and turns over the top card.
     *
     * @author Lily Romano
     */
    public void shuffleDiscardToDrawDeck() {
        theDrawDeck.addCards(theDiscardDeck.removeAllCards());
        theDiscardDeck.addCard(theDrawDeck.popTopCard());
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

    /**
     * Returns a well formatted string representing the state of the game.
     *
     * @author Lily Romano
     *
     * @return a well formatted string representing the state of the game.
     */
    @Override
    public String toString() {
        String result;

        result = "GAME STATUS: " + "isGameStarted=" + isGameStarted + ", isBUnoLastTurnPlayed=" + isBUnoLastTurnPlayed + ", Discard=" + getDiscardCardCard() + "\n";
        for (PlayerHand player : players) {
            result += " - " + player.toString() + "\n";
        }
        return result;
    }

}
