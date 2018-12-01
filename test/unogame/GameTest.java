/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: James Kelly, Scott Little, Rachel Wang, Lily Romano
* Date: Nov 18, 2018
* Time: 8:44:34 PM
*
* Project: csci205FinalProject
* Package: unogame
* File: GameTest
* Description:
*
* ****************************************
 */
package unogame;

import deck.PlayerHand;
import deck.card.Card;
import deck.card.CardColor;
import deck.card.CardType;
import static junit.framework.TestCase.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author lilyheart
 */
public class GameTest {

    private Game unoGame;

    public GameTest() {
    }

    @Before
    public void setUp() {
        unoGame = new Game();
    }

    @After
    public void tearDown() {
        unoGame = null;
    }

    /**
     * Test of getNumHumanPlayers method, of class Game.
     */
    @Test
    public void testGetNumHumanPlayers() throws Exception {
        System.out.println("getNumHumanPlayers");

        //test no human players
        assertEquals(0, unoGame.getNumHumanPlayers());

        //add player
        unoGame.makePlayer(false);
        assertEquals(1, unoGame.getNumHumanPlayers());
    }

    /**
     * Test of getNumComputerPlayers method, of class Game.
     */
    @Test
    public void testGetNumComputerPlayers() throws Exception {
        System.out.println("getNumComputerPlayers");

        //test no computer players
        assertEquals(0, unoGame.getNumComputerPlayers());

        //add player
        unoGame.makePlayer(true);
        assertEquals(1, unoGame.getNumComputerPlayers());
    }

    /**
     * Test of getPlayersHandCopy method, of class Game.
     */
    @Test
    public void testGetPlayersHandCopy() throws Exception {
        System.out.println("getPlayersHandCopy");

        unoGame.makePlayer(true);

        assertEquals(PlayerHand.NEWHANDCARDNUM,
                     unoGame.getPlayersHandCopy(1).size());
    }

    /**
     * Test of drawCard method, of class Game.
     */
    @Test
    public void testDrawCard() throws Exception {
        System.out.println("drawCard");

        unoGame.makePlayer(true);
        unoGame.drawCard(1);

        assertEquals(PlayerHand.NEWHANDCARDNUM + 1,
                     unoGame.getPlayersHandCopy(1).size());
    }

    /**
     * Test of shuffleDiscardToDrawDeck method, of class Game. Also tests
     * playCard.
     */
    @Test
    public void testShuffleDiscardToDrawDeck() throws Exception {
        System.out.println("shuffleDiscardToDrawDeck");

        //confirm discard starts with zero cards
        assertEquals(0, unoGame.theDiscardDeck.getDeckSize());

        //start game
        unoGame.makePlayer(true);
        unoGame.startGame();
        int drawDeckSize = unoGame.theDrawDeck.getDeckSize();

        //test new game settings
        assertEquals(1, unoGame.theDiscardDeck.getDeckSize());

        //discard a card
        unoGame.playCard(1, 0);
        drawDeckSize++;

        //confirm card left player's hand
        assertEquals(PlayerHand.NEWHANDCARDNUM - 1,
                     unoGame.getPlayersHandCopy(1).size());

        //discard more cards
        unoGame.playCard(1, 0);
        drawDeckSize++;
        unoGame.playCard(1, 0);
        drawDeckSize++;
        unoGame.playCard(1, 0);
        drawDeckSize++;

        //shuffle discard into draw
        unoGame.shuffleDiscardToDrawDeck();

        //test deck size
        assertEquals(1, unoGame.theDiscardDeck.getDeckSize());
        assertEquals(drawDeckSize, unoGame.theDrawDeck.getDeckSize());

    }

    /**
     * Test of isLegalPlay method with matching color only, of class Game.
     */
    @Test
    public void testIsLegalPlayColorOnly() throws Exception {
        System.out.println("testIsLegalPlayColorOnly");

        unoGame.makePlayer(true);
        unoGame.startGame();

        Card discardCard = unoGame.theDiscardDeck.peekTopCard();

        if (discardCard.getColor() == CardColor.SPECIAL) {
            //TODO [Not Basic Game] Expand this test
        }

        CardColor matchColor = discardCard.getColor();
        CardType unmatchType = (discardCard.getType() == CardType.ZERO) ? CardType.ONE : CardType.ZERO;

        //Test matches color only
        Card testCard = new Card(matchColor, unmatchType);
        assertEquals(true, unoGame.isLegalPlay(testCard));
    }

    /**
     * Test of isLegalPlay method with matching type only, of class Game.
     */
    @Test
    public void testIsLegalPlayTypeOnly() throws Exception {
        System.out.println("testIsLegalPlayTypeOnly");

        unoGame.makePlayer(true);
        unoGame.startGame();

        Card discardCard = unoGame.theDiscardDeck.peekTopCard();

        if (discardCard.getColor() == CardColor.SPECIAL) {
            //TODO [Not Basic Game] Expand this test
        }

        CardColor unmatchColor = (discardCard.getColor() == CardColor.BLUE) ? CardColor.GRAY : CardColor.BLUE;
        CardType matchType = discardCard.getType();

        //Test matches color only
        Card testCard = new Card(unmatchColor, matchType);
        assertEquals(true, unoGame.isLegalPlay(testCard));
    }

    /**
     * Test of isLegalPlay method with matching type only, of class Game.
     */
    @Test
    public void testIsLegalPlayBothMatch() throws Exception {
        System.out.println("testIsLegalPlayBothMatch");

        unoGame.makePlayer(true);
        unoGame.startGame();

        Card discardCard = unoGame.theDiscardDeck.peekTopCard();

        if (discardCard.getColor() == CardColor.SPECIAL) {
            //TODO [Not Basic Game] Expand this test
        }

        CardColor matchColor = discardCard.getColor();
        CardType matchType = discardCard.getType();

        //Test matches color only
        Card testCard = new Card(matchColor, matchType);
        assertEquals(true, unoGame.isLegalPlay(testCard));
    }

    /**
     * Test of isLegalPlay method with matching type only, of class Game.
     */
    @Test
    public void testIsLegalPlayNeitherMatch() throws Exception {
        System.out.println("testIsLegalPlayNeitherMatch");

        unoGame.makePlayer(true);
        unoGame.startGame();

        Card discardCard = unoGame.theDiscardDeck.peekTopCard();

        if (discardCard.getColor() == CardColor.SPECIAL) {
            //TODO [Not Basic Game] Expand this test
        }

        CardColor unmatchColor = (discardCard.getColor() == CardColor.BLUE) ? CardColor.GRAY : CardColor.BLUE;
        CardType unmatchType = (discardCard.getType() == CardType.ZERO) ? CardType.ONE : CardType.ZERO;

        //Test matches color only
        Card testCard = new Card(unmatchColor, unmatchType);
        assertEquals(false, unoGame.isLegalPlay(testCard));
    }
}
