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

/**
 * A Prototype DrawDeck file for the deck GUI prototype
 *
 * @author Lily Romano
 */
public class DrawDeck extends UnoDeck {

    /**
     * An explicit constructor for the deck
     */
    public DrawDeck() {
        super();

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

    }

}
