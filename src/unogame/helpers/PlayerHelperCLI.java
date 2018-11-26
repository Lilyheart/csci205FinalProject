/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: James Kelly, Scott Little, Rachel Wang, Lily Romano
* Date: Nov 26, 2018
* Time: 11:24:06 AM
*
* Project: csci205FinalProject
* Package: unogame
* File: PlayerHelperCLI
* Description:
*
* ****************************************
 */
package unogame.helpers;

import deck.card.Card;
import java.util.concurrent.CopyOnWriteArrayList;
import unogame.NoValidCardException;
import unogame.PlayCommand;

/**
 *
 * @author Lily Romano
 */
public final class PlayerHelperCLI {

    public int getValidCard(CopyOnWriteArrayList<Card> hand,
                            Card discardCard) throws NoValidCardException {
        //TODO [Basic Game] Code this.
        return 0;

    }

    public PlayCommand getPlayCommand(CopyOnWriteArrayList<Card> hand,
                                      Card discardCard) {
        //TODO [Basic Game] Code this.
        return PlayCommand.BUNO;
    }

}
