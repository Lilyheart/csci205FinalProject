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
package prototypegui.model;

import cards.Card;
import cards.Deck;

/**
 * A GUI Card Prototype MVC model Main GUI
 *
 * @version 0.1
 * @author Lily Romano
 */
public class MainGuiModel {

    private Deck theDeck;

    /**
     * An explicit constructor for the Card Prototype Model Main GUI
     */
    public MainGuiModel() {
        theDeck = new Deck();
        theDeck.shuffle();
    }

    public Deck getTheDeck() {
        return theDeck;
    }

    public Card getNextCard() {
        return theDeck.getNextCard();
    }

}
