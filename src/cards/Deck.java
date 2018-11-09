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
* File: Deck
* Description:
*
* ****************************************
 */
package cards;

import java.util.ArrayList;

/**
 *
 * @author Lily Romano
 * @version 0.1
 */
public class Deck {

    private ArrayList deck;

    private static final int NUM_COLORS = 4;
    private static final int NUM_SPECIAL_NOTCOLOR = 2;
    private static final int TOTAL_NUMCARDS = 10;
    private static final int SPECIAL_COLOR_CARDS = 3;
    private static final int SPECIAL_NOTCOLOR_CARDS = 3;

    public Deck() {
        deck = new ArrayList();

        //Add Cards by color
        for (int i = 0; i < NUM_COLORS; i++) {
            CardColor color = CardColor.values()[i];

            //Add number cards by color
            for (int j = 0; j < TOTAL_NUMCARDS; j++) {
                CardType type = CardType.values()[j];
                Card card = new Card(color, type);
                //Add one of this card type
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

        for (int i = 0; i < NUM_SPECIAL_NOTCOLOR; i++) {
            CardColor color = CardColor.values()[4];
            CardType type = CardType.values()[i + TOTAL_NUMCARDS + SPECIAL_COLOR_CARDS];
            Card card = new Card(color, type);
            //Add two of this card type
            this.deck.add(card);
            this.deck.add(card);
        }
        System.out.println(deck);

    }

    public static void main(String[] args) {
        Deck thisdeck = new Deck();
    }

}
