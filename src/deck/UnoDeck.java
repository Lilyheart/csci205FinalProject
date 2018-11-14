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
* File: UnoDeck
* Description:
*
* ****************************************
 */
package deck;

import deck.card.Card;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author Lily Romano
 */
public abstract class UnoDeck {

    /**
     * An {@code LinkedList} representing the deck.
     */
    protected final List<Card> deck;

    //TODO [Color Cleanup] Move some of these constants into the enums
    /**
     * The number of different colors in the deck
     */
    protected static final int NUM_COLORS = 4;

    /**
     * The count of special cards in the deck that are not colors Wild and Wild
     * Draw 3
     */
    protected static final int NUM_SPECIAL_NOTCOLOR = 2;

    /**
     * The count of different number face cards 0-9
     */
    protected static final int TOTAL_NUMCARDS = 10;

    /**
     * The count of special cards in the deck that are colors Draw 2, Reverse
     * and Skip
     */
    protected static final int SPECIAL_COLOR_CARDS = 3;

    /**
     * An iterator for the {@code deck}
     */
    protected Iterator cardIterator;

    /**
     * An explicit constructor for a deck of Uno Cards
     */
    public UnoDeck() {
        this.deck = new CopyOnWriteArrayList<>();
        this.cardIterator = deck.iterator();
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
     */
    public Card getNextCard() {
        Card card;

        if (cardIterator.hasNext()) {
            card = (Card) cardIterator.next();
        }
        else {
            cardIterator = deck.iterator();
            card = (Card) cardIterator.next();
        }

        return card;
    }

    /**
     * Returns a well formatted string representing the deck.
     *
     * @return a well formatted string representing the deck.
     */
    @Override
    public String toString() {
        return "New Deck: \n" + deck;
    }

}
