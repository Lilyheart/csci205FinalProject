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
* File: MainGuiModel
* Description:
*
* ****************************************
 */
package prototypegui;

import deck.card.Card;
import deck.Deck;

/**
 * A GUI Card Prototype MVC model Main GUI
 *
 * @author Lily Romano
 */
public class MainGuiModel {

    /**
     * Creates a deck of cards
     */
    private Deck theDeck;

    /**
     * An explicit constructor for the Card Prototype Model Main GUI
     */
    public MainGuiModel() {
        theDeck = new Deck();
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
