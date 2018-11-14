/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: James Kelly, Scott Little, Rachel Wang, Lily Romano
* Date: Nov 14, 2018
* Time: 1:20:55 PM
*
* Project: csci205FinalProject
* Package: deck.card
* File: CardSettings
* Description:
*
* ****************************************
 */
package unogamemvc.resource;

/**
 *
 * @author Lily Romano
 */
public final class CardSettings {

    /**
     * Overall card width
     */
    public static final int OUTER_CARD_WIDTH = 128;

    /**
     * Overall card height
     */
    public static final int OUTER_CARD_HEIGHT = 178;

    /**
     * Margins of outside of card
     */
    public static final int OUTER_CARD_MARGIN = 5;

    /**
     * Inner card width which is calculated by the outer card width minus the
     * margins times 2 (left and right)
     */
    public static final int INNER_CARD_WIDTH = 128 - (OUTER_CARD_MARGIN * 2);

}
