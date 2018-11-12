/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: James Kelly, Scott Little, Rachel Wang, Lily Romano
* Date: Nov 9, 2018
* Time: 11:30:29 AM
*
* Project: csci205FinalProject
* Package: cards
* File: CardType
* Description:
*
* ****************************************
 */
package deck.card;

/**
 * A enumerated type to represent the possible card types. The types must be
 * added in the order - Number cards, Colored special, Non-color special
 *
 * @author Lily Romano
 */
public enum CardType {

    /**
     * An enum with information on the card zero
     */
    ZERO("0", 0),
    /**
     * An enum with information on the card one
     */
    ONE("1", 1),
    /**
     * An enum with information on the card two
     */
    TWO("2", 2),
    /**
     * An enum with information on the card three
     */
    THREE("3", 3),
    /**
     * An enum with information on the card four
     */
    FOUR("4", 4),
    /**
     * An enum with information on the card five
     */
    FIVE("5", 5),
    /**
     * An enum with information on the card six
     */
    SIX("6", 6),
    /**
     * An enum with information on the card seven
     */
    SEVEN("7", 7),
    /**
     * An enum with information on the card eight
     */
    EIGHT("8", 8),
    /**
     * An enum with information on the card nine
     */
    NINE("9", 9),
    /**
     * An enum with information on the card draw 2
     */
    DRAW2("+2", 20),
    /**
     * An enum with information on the card reverse
     */
    REVERSE("⇆", 20),
    /**
     * An enum with information on the card skip
     */
    SKIP("Ø", 20),
    /**
     * An enum with information on the card wild
     */
    WILD("Wild", 50),
    /**
     * An enum with information on the card wild draw 4
     */
    WILDDRAW4("WildDraw4", 50);

    /**
     * A String that represents the card type
     */
    private final String cardText;

    /**
     * The scoring value of the card type
     */
    private final int cardPointValue;

    /**
     * An explicit constructor for the Card Type enumerated type
     *
     * @param cardText a {@code String} representing the card type
     */
    private CardType(String cardText, int cardValue) {
        this.cardText = cardText;
        this.cardPointValue = cardValue;
    }

    /**
     * Returns the string representation of the card type
     *
     * @return the string representation of the card type
     */
    public String getCardText() {
        return cardText;
    }

    /**
     * Returns the card point value for scoring
     *
     * @return the card point value for scoring
     */
    public int getCardPointValue() {
        return cardPointValue;
    }
}
