/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: James Kelly, Scott Little, Rachel Wang, Lily Romano
* Date: Nov 9, 2018
* Time: 12:40:08 PM
*
* Project: csci205FinalProject
* Package: view
* File: UNOGameModel
* Description:
*
* ****************************************
 */
package unogamemvc;

import deck.EmptyDeckException;
import deck.PlayerHand;
import deck.card.Card;
import unogame.Game;
import unogame.GameNotStartedException;

/**
 * A GUI Card Prototype MVC model Main GUI
 *
 * @author Lily Romano
 */
public class UNOGameModel {

    /**
     * Total number of computer players
     */
    private final static int NUM_OF_COMPUTER_PLAYERS = 3;

    /**
     * The Player idNum for the human player
     */
    private final static int HUMAN_PLAYER = 1;

    private static Game unoGame;

    private InvalidPlayPopup invalidPlayPopup;

    /**
     * An explicit constructor for the UNO Main GUI Model
     */
    public UNOGameModel() throws EmptyDeckException {
        unoGame = new Game();
        setNewDefaultGame();
        unoGame.startGame();
        System.out.println(unoGame.getTheDrawDeck());
        this.invalidPlayPopup = new InvalidPlayPopup();

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
     * Pops the top card from the draw deck so we can add it to hand.
     *
     * @return
     * @throws EmptyDeckException
     */
    Card popNextDrawCard() throws EmptyDeckException {
        return unoGame.getTheDrawDeck().popTopCard();
    }

    /**
     * Peeks the next card, useful for testing.
     *
     * @return
     * @throws EmptyDeckException
     */
    Card peekNextDrawCard() throws EmptyDeckException {
        return unoGame.getTheDrawDeck().peekTopCard();

    }

    Card peekDiscardPileNextCard() throws EmptyDeckException {
        return unoGame.getTheDiscardDeck().peekBottomCard();

    }

    public static int getNUM_OF_COMPUTER_PLAYERS() {
        return NUM_OF_COMPUTER_PLAYERS;
    }

    /**
     * Takes a card from the draw deck and adds it to the players hand.
     */
    public void tryToDrawCardAction() {
        //TODO [Basic Game]
        boolean isDrawSuccessful = false;
        //TODO [Basic Game] If there are no cards in the discard or draw piles this loop will be infinite
        while (!isDrawSuccessful) {
            try {
                unoGame.drawCard(HUMAN_PLAYER);
                System.out.println("JUST DREW A CARD!!");
                isDrawSuccessful = true;
            } catch (EmptyDeckException ex) {
                unoGame.shuffleDiscardToDrawDeck();
            }
        }
    }

    public void tryToPlayCardAction(int playCardIndex) throws EmptyDeckException {
        System.out.println(unoGame.getPlayersHandCopy(
                HUMAN_PLAYER).get(playCardIndex));
        System.out.println(unoGame.getPlayersHandCopy(
                HUMAN_PLAYER));

        if (!unoGame.isLegalPlay(unoGame.getPlayersHandCopy(
                HUMAN_PLAYER).get(playCardIndex))) {
            System.out.println("Invalid play\n");
            invalidPlayPopup.display();
            //POP-UP BOX
        }
        else {
            unoGame.playCard(HUMAN_PLAYER, playCardIndex);
            System.out.println("Valid Play\n");

        }

    }

    public static Game getUnoGame() {
        return unoGame;
    }

    public static int getHUMAN_PLAYER() {
        return HUMAN_PLAYER;
    }

    public InvalidPlayPopup getInvalidPlayPopup() {
        return invalidPlayPopup;
    }

}
