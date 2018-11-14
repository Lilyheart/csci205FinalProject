/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: James Kelly, Scott Little, Rachel Wang, Lily Romano
* Date: Nov 9, 2018
* Time: 9:56:23 AM
*
* Project: csci205FinalProject
* Package: gui
* File: PrototypeGuiModel
* Description:
*
* ****************************************
 */
package prototypegui;

import deck.card.Card;
import deck.DrawDeck;

/**
 * A GUI Card Prototype MVC model Main GUI
 *
 * @author Lily Romano
 */
public class PrototypeGuiModel {

    /**
     * Creates a deck of cards
     */
    private DrawDeck theDeck;

    /**
     * An explicit constructor for the Card Prototype Model Main GUI
     */
    public PrototypeGuiModel() {
        theDeck = new DrawDeck();
        theDeck.shuffle();
    }

    /**
     * Returns the next card in the deck
     *
     * @return the next card in the deck
     */
    public Card getNextCard() {
        return theDeck.getNextCard();
    }

}
