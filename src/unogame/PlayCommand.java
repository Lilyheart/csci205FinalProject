/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: James Kelly, Scott Little, Rachel Wang, Lily Romano
* Date: Nov 26, 2018
* Time: 10:45:24 AM
*
* Project: csci205FinalProject
* Package: unogame
* File: PlayCommand
* Description:
*
* ****************************************
 */
package unogame;

/**
 * enum class for storing playCommand
 *
 * @author Rachel Wang
 */
public enum PlayCommand {

    /**
     * enum when player wants to call BUNO
     */
    BUNO,
    /**
     * enum when player wants to draw
     */
    DRAW,
    /**
     * enum when player wnats to play a card
     */
    PLAYCARD,
    /**
     * enum when still no valid card to play after the draw or the user chooses
     * to skip a playable card
     */
    PASS;
}
