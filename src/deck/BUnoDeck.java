/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: James Kelly, Scott Little, Rachel Wang, Lily Romano
* Date: Nov 14, 2018
* Time: 12:15:28 PM
*
* Project: csci205FinalProject
* Package: deck
* File: BUnoDeck
* Description:
*
* ****************************************
 */
package deck;

import deck.card.Card;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author Lily Romano
 */
public abstract class BUnoDeck {

    /**
     * An {@code LinkedList} representing the deck.
     */
    protected final CopyOnWriteArrayList<Card> deck;

    /**
     * The number of different colors in the deck
     */
    protected static final int NUM_COLORS = 4;

    /**
     * The count of special cards in the deck that are not colors Wild and Wild
     * Draw 3
     */
    //TODO [Basic Game] Change following const to 2 for full game
    protected static final int NUM_SPECIAL_NOTCOLOR = 0;

    /**
     * The count of different number face cards 0-9
     */
    protected static final int TOTAL_NUMCARDS = 10;

    /**
     * The count of special cards in the deck that are colors Draw 2, Reverse
     * and Skip
     */
    //TODO [Basic Game] Change following const to 3 for full game
    protected static final int SPECIAL_COLOR_CARDS = 0;

    /**
     * An explicit constructor for a deck of Uno Cards
     */
    public BUnoDeck() {
        this.deck = new CopyOnWriteArrayList<>();
    }

    /**
     * Returns the size of the deck.
     *
     * @return the size of the deck.
     */
    public int getDeckSize() {
        return deck.size();
    }

    /**
     * Shuffles the deck of cards.
     */
    public void shuffle() {
        Collections.shuffle(deck);
    }

    /**
     * Returns the next card in the deck.
     *
     * @return the next card in the deck.
     * @throws deck.EmptyDeckException when the deck is empty
     */
    public Card removeNextCard() throws EmptyDeckException {

        if (deck.isEmpty()) {
            throw new EmptyDeckException(this.getClass() + " Deck is empty");
        }

        return deck.remove(0);
    }

    public void addCard(Card newCard) {
        deck.add(newCard);
    }

    /**
     * Returns a well formatted string representing the deck.
     *
     * @return a well formatted string representing the deck.
     */
    @Override
    public String toString() {
        return "Deck: \n" + deck;
    }

}
