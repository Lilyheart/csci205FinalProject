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

import deck.DrawDeck;
import deck.EmptyDeckException;
import deck.card.Card;

/**
 * A GUI Card Prototype MVC model Main GUI
 *
 * @author Lily Romano
 */
public class UNOGameModel {

    /**
     * Creates a deck of cards
     */
    private final DrawDeck theDrawDeck;

    /**
     * An explicit constructor for the UNO Main GUI Model
     */
    public UNOGameModel() {
        theDrawDeck = new DrawDeck();
        theDrawDeck.shuffle();
    }

    /**
     * Pops the top card from the draw deck so we can add it to hand.
     *
     * @return
     * @throws EmptyDeckException
     */
    Card popNextDrawCard() throws EmptyDeckException {
        return theDrawDeck.popTopCard();
    }

    /**
     * Peeks the next card, useful for testing.
     *
     * @return
     * @throws EmptyDeckException
     */
    Card peekNextDrawCard() throws EmptyDeckException {
        return theDrawDeck.peekTopCard();
    }

}
