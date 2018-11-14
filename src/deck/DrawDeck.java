/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: James Kelly, Scott Little, Rachel Wang, Lily Romano
* Date: Nov 9, 2018
* Time: 11:44:06 AM
*
* Project: csci205FinalProject
* Package: cards
* File: DrawDeck
* Description:
*
* ****************************************
 */
package deck;

import deck.card.Card;
import deck.card.CardColor;
import deck.card.CardType;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * A Prototype DrawDeck file for the deck GUI prototype
 *
 * @author Lily Romano
 */
public class DrawDeck {

    /**
     * An {@code LinkedList} representing the deck.
     */
    private final LinkedList<Card> deck;

    //TODO [Color Cleanup] Move some of these constants into the enums
    /**
     * The number of different colors in the deck
     */
    private static final int NUM_COLORS = 4;

    /**
     * The count of special cards in the deck that are not colors Wild and Wild
     * Draw 3
     */
    private static final int NUM_SPECIAL_NOTCOLOR = 2;

    /**
     * The count of different number face cards 0-9
     */
    private static final int TOTAL_NUMCARDS = 10;

    /**
     * The count of special cards in the deck that are colors Draw 2, Reverse
     * and Skip
     */
    private static final int SPECIAL_COLOR_CARDS = 3;

    /**
     * An iterator for the {@code deck}
     */
    private ListIterator cardIterator;

    /**
     * An explicit constructor for the deck
     */
    public DrawDeck() {
        deck = new LinkedList<>();

        //Add Cards by color
        for (int i = 0; i < NUM_COLORS; i++) {
            CardColor color = CardColor.values()[i];

            //Add number cards by color
            for (int j = 0; j < TOTAL_NUMCARDS; j++) {
                CardType type = CardType.values()[j];
                Card card = new Card(color, type);
                //Add one of this card zero, two of the others
                this.deck.add(card);

                //If card number is not zero, add a second card of this type
                if (j > 0) {
                    this.deck.add(card);
                }
            }

            //Add color special cards
            for (int j = 0; j < SPECIAL_COLOR_CARDS; j++) {
                CardType type = CardType.values()[j + TOTAL_NUMCARDS];
                Card card = new Card(color, type);
                //Add two of this card type
                this.deck.add(card);
                this.deck.add(card);
            }
        }

        //Add Special non color cards
        for (int i = 0; i < NUM_SPECIAL_NOTCOLOR; i++) {
            CardColor color = CardColor.values()[4];
            CardType type = CardType.values()[i + TOTAL_NUMCARDS + SPECIAL_COLOR_CARDS];
            Card card = new Card(color, type);
            //Add four of this card type
            this.deck.add(card);
            this.deck.add(card);
            this.deck.add(card);
            this.deck.add(card);
        }

        cardIterator = deck.listIterator();
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
        if (!cardIterator.hasNext()) {
            cardIterator = deck.listIterator();
        }

        Card card = (Card) cardIterator.next();
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

    /**
     * Returns the size of the deck.
     *
     * @return the size of the deck.
     */
    public int getDeckSize() {
        return deck.size();
    }

    public static void main(String[] args) {
        DrawDeck theDeck = new DrawDeck();

        theDeck.shuffle();
        System.out.println(theDeck.toString());
    }

}
