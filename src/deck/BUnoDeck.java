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
 * An abstract class to manage a BUno deck
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
    //TODO [!Special Cards] Change following const to 2 for full game
    protected static final int NUM_SPECIAL_NOTCOLOR = 0;

    /**
     * The count of different number face cards 0-9
     */
    protected static final int TOTAL_NUMCARDS = 10;

    /**
     * The count of special cards in the deck that are colors Draw 2, Reverse
     * and Skip
     */
    //TODO [!Special Cards] Change following const to 3 for full game
    protected static final int SPECIAL_COLOR_CARDS = 0;

    /**
     * An explicit constructor for a deck of Uno Cards
     *
     * @author Lily Romano
     */
    public BUnoDeck() {
        this.deck = new CopyOnWriteArrayList<>();
    }

    /**
     * Returns the size of the deck.
     *
     * @author Lily Romano
     *
     * @return the size of the deck.
     */
    public int getDeckSize() {
        return deck.size();
    }

    /**
     * Shuffles the deck of cards.
     *
     * @author Lily Romano
     */
    public void shuffle() {
        Collections.shuffle(deck);
    }

    /**
     * Returns the card at a specific index in the deck and removes it
     *
     * @author Lily Romano
     *
     * @param index the index of the card to remove and return.
     * @return the card at a specific index in the deck and remove it.
     */
    public Card popCardAtIndex(int index) {
        return deck.remove(index);
    }

    /**
     * Returns the next card in the deck and removes it
     *
     * @author Lily Romano
     *
     * @return the next card in the deck and remove it.
     */
    public Card popTopCard() {
        return popCardAtIndex(0);
    }

    /**
     * Removes all cards from the deck and returns them as a
     * {@code CopyOnWriteArrayList}
     *
     * @author Lily Romano
     *
     * @return all cards from the deck as a {@code CopyOnWriteArrayList}
     */
    public CopyOnWriteArrayList<Card> removeAllCards() {
        CopyOnWriteArrayList<Card> remainingCards = (CopyOnWriteArrayList<Card>) deck.clone();
        deck.clear();

        return remainingCards;
    }

    /**
     * Returns the next card in the deck but leaves it in place
     *
     * @author Lily Romano
     *
     * @return the next card in the deck but leave it in place
     */
    public Card peekTopCard() {
        return deck.get(0);
    }

    /**
     * Returns the next card in the deck but leaves it in place
     *
     * @author Lily Romano
     *
     * @return the next card in the deck but leave it in place
     */
    public Card peekBottomCard() {
        return deck.get(deck.size() - 1);
    }

    /**
     * Add a card to this deck
     *
     * @author Lily Romano
     *
     * @param newCard the {@code Card} to add.
     */
    public void addCard(Card newCard) {
        deck.add(newCard);
    }

    /**
     * Add an array of card to this deck
     *
     * @author Lily Romano
     *
     * @param cards The {@code CopyOnWriteArrayList} of {@code Card}s to add.
     */
    public void addCards(CopyOnWriteArrayList<Card> cards) {
        cards.forEach((card) -> {
            deck.add(card);
        });
    }

    /**
     * Returns a well formatted string representing the deck.
     *
     * @author Lily Romano
     *
     * @return a well formatted string representing the deck.
     */
    @Override
    public String toString() {
        return "Deck: " + deck;
    }

}
