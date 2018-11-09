/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: James Kelly, Scott Little, Rachel Wang, Lily Romano
* Date: Nov 9, 2018
* Time: 11:25:36 AM
*
* Project: csci205FinalProject
* Package: cards
* File: CardColor
* Description:
*
* ****************************************
 */
package cards;

import javafx.scene.paint.Color;

/**
 * A enumerated type to represent the possible card colors
 *
 * @author Lily Romano
 */
public enum CardColor {

    /**
     * An enum with information on the blue cards
     */
    BLUE(Color.web("#E87722")),
    /**
     * An enum with information on the orange cards
     */
    ORANGE(Color.web("#003865")),
    /**
     * An enum with information on the yellow cards
     */
    YELLOW(Color.web("#FFCD00")),
    /**
     * An enum with information on the gray cards
     */
    GRAY(Color.web("#A7A8AA")),
    /**
     * An enum with information on the special cards
     */
    SPECIAL(Color.web("#FFFFFF"));

    /**
     * The {@code Color} of the card
     */
    private final Color color;

    /**
     * An explicit constructor for the Card Color enumerated type
     *
     * @param color
     */
    private CardColor(Color color) {
        this.color = color;
    }

    /**
     * Returns the {@code Color} of the card.
     *
     * @return the {@code Color} of the card
     */
    public Color getColor() {
        return color;
    }
}
