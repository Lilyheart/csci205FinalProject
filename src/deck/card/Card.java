/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: James Kelly, Scott Little, Rachel Wang, Lily Romano
* Date: Nov 9, 2018
* Time: 11:42:50 AM
*
* Project: csci205FinalProject
* Package: cards
* File: Card
* Description:
*
* ****************************************
 */
package deck.card;

/**
 * A Card of an BUno deck
 *
 * @author Lily Romano
 */
public class Card {

    /**
     * The color of the card
     */
    private final CardColor color;

    /**
     * The type of card
     */
    private final CardType type;

    /**
     * An explicit constructor for a Card
     *
     * @param color the color of the card
     * @param type the type of card
     */
    public Card(CardColor color, CardType type) {
        this.color = color;
        this.type = type;
    }

    /**
     * Returns the enum CardColor of the card
     *
     * @return the enum CardColor of the card
     */
    public CardColor getColor() {
        return color;
    }

    /**
     * Returns the enum CardColor of the card
     *
     * @return the enum CardColor of the card
     */
    public String getColorString() {
        return color.getColor();
    }

    /**
     * Returns the enum CardType of the card
     *
     * @return the enum CardType of the card
     */
    public CardType getType() {
        return type;
    }

    /**
     * Returns a nicely formatted string representing a card.
     *
     * @return a nicely formatted string representing a card.
     */
    @Override
    public String toString() {
        return "Card{" + "color=" + color + ", type=" + type + '}';
    }

}
