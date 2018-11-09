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

    public Deck() {
        deck = new ArrayList();

        //Add Cards by color
        for (int i = 0; i < 4; i++) {
            CardColor color = CardColor.values()[i];

            //Add number cards by color
            for (int j = 0; j < 10; j++) {
                CardType type = CardType.values()[j];
                Card card = new Card(color, type);
                //Add one of this card type
                this.deck.add(card);

                //If card number is not zero, add a second card of this type
                if (j > 0) {
                    this.deck.add(card);
                }
            }
        }

        System.out.println(deck);

    }

    public static void main(String[] args) {
        Deck thisdeck = new Deck();
    }

}
