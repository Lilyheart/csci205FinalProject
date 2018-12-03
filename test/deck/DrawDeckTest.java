/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: James Kelly, Scott Little, Rachel Wang, Lily Romano
* Date: Nov 2, 2018
* Time: 3:25:41 PM
*
* Project: csci205FinalProject
* Package: cards
* File: DrawDeckTest
* Description:
*
* ****************************************
 */
package deck;

import deck.card.Card;
import static junit.framework.TestCase.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the DrawDeck class as well as the BUnoDeck
 *
 * @author Lily Romano
 */
public class DrawDeckTest {

    /**
     * A new deck used for testing methods
     */
    DrawDeck testDeck;

    /**
     * The default string a deck of 102 cards should display
     */
    //TODO [Basic Game] Switch Active string test
//    String defaultDeckString = "Draw Deck: \n[Card{color=BLUE, type=ZERO}, Card{color=BLUE, type=ONE}, Card{color=BLUE, type=ONE}, Card{color=BLUE, type=TWO}, Card{color=BLUE, type=TWO}, Card{color=BLUE, type=THREE}, Card{color=BLUE, type=THREE}, Card{color=BLUE, type=FOUR}, Card{color=BLUE, type=FOUR}, Card{color=BLUE, type=FIVE}, Card{color=BLUE, type=FIVE}, Card{color=BLUE, type=SIX}, Card{color=BLUE, type=SIX}, Card{color=BLUE, type=SEVEN}, Card{color=BLUE, type=SEVEN}, Card{color=BLUE, type=EIGHT}, Card{color=BLUE, type=EIGHT}, Card{color=BLUE, type=NINE}, Card{color=BLUE, type=NINE}, Card{color=BLUE, type=DRAW2}, Card{color=BLUE, type=DRAW2}, Card{color=BLUE, type=REVERSE}, Card{color=BLUE, type=REVERSE}, Card{color=BLUE, type=SKIP}, Card{color=BLUE, type=SKIP}, Card{color=ORANGE, type=ZERO}, Card{color=ORANGE, type=ONE}, Card{color=ORANGE, type=ONE}, Card{color=ORANGE, type=TWO}, Card{color=ORANGE, type=TWO}, Card{color=ORANGE, type=THREE}, Card{color=ORANGE, type=THREE}, Card{color=ORANGE, type=FOUR}, Card{color=ORANGE, type=FOUR}, Card{color=ORANGE, type=FIVE}, Card{color=ORANGE, type=FIVE}, Card{color=ORANGE, type=SIX}, Card{color=ORANGE, type=SIX}, Card{color=ORANGE, type=SEVEN}, Card{color=ORANGE, type=SEVEN}, Card{color=ORANGE, type=EIGHT}, Card{color=ORANGE, type=EIGHT}, Card{color=ORANGE, type=NINE}, Card{color=ORANGE, type=NINE}, Card{color=ORANGE, type=DRAW2}, Card{color=ORANGE, type=DRAW2}, Card{color=ORANGE, type=REVERSE}, Card{color=ORANGE, type=REVERSE}, Card{color=ORANGE, type=SKIP}, Card{color=ORANGE, type=SKIP}, Card{color=YELLOW, type=ZERO}, Card{color=YELLOW, type=ONE}, Card{color=YELLOW, type=ONE}, Card{color=YELLOW, type=TWO}, Card{color=YELLOW, type=TWO}, Card{color=YELLOW, type=THREE}, Card{color=YELLOW, type=THREE}, Card{color=YELLOW, type=FOUR}, Card{color=YELLOW, type=FOUR}, Card{color=YELLOW, type=FIVE}, Card{color=YELLOW, type=FIVE}, Card{color=YELLOW, type=SIX}, Card{color=YELLOW, type=SIX}, Card{color=YELLOW, type=SEVEN}, Card{color=YELLOW, type=SEVEN}, Card{color=YELLOW, type=EIGHT}, Card{color=YELLOW, type=EIGHT}, Card{color=YELLOW, type=NINE}, Card{color=YELLOW, type=NINE}, Card{color=YELLOW, type=DRAW2}, Card{color=YELLOW, type=DRAW2}, Card{color=YELLOW, type=REVERSE}, Card{color=YELLOW, type=REVERSE}, Card{color=YELLOW, type=SKIP}, Card{color=YELLOW, type=SKIP}, Card{color=GRAY, type=ZERO}, Card{color=GRAY, type=ONE}, Card{color=GRAY, type=ONE}, Card{color=GRAY, type=TWO}, Card{color=GRAY, type=TWO}, Card{color=GRAY, type=THREE}, Card{color=GRAY, type=THREE}, Card{color=GRAY, type=FOUR}, Card{color=GRAY, type=FOUR}, Card{color=GRAY, type=FIVE}, Card{color=GRAY, type=FIVE}, Card{color=GRAY, type=SIX}, Card{color=GRAY, type=SIX}, Card{color=GRAY, type=SEVEN}, Card{color=GRAY, type=SEVEN}, Card{color=GRAY, type=EIGHT}, Card{color=GRAY, type=EIGHT}, Card{color=GRAY, type=NINE}, Card{color=GRAY, type=NINE}, Card{color=GRAY, type=DRAW2}, Card{color=GRAY, type=DRAW2}, Card{color=GRAY, type=REVERSE}, Card{color=GRAY, type=REVERSE}, Card{color=GRAY, type=SKIP}, Card{color=GRAY, type=SKIP}, Card{color=SPECIAL, type=WILD}, Card{color=SPECIAL, type=WILD}, Card{color=SPECIAL, type=WILD}, Card{color=SPECIAL, type=WILD}, Card{color=SPECIAL, type=WILDDRAW4}, Card{color=SPECIAL, type=WILDDRAW4}, Card{color=SPECIAL, type=WILDDRAW4}, Card{color=SPECIAL, type=WILDDRAW4}]";
    String defaultDeckString = "Draw Deck: \n[Card{color=BLUE, type=ZERO}, Card{color=BLUE, type=ONE}, Card{color=BLUE, type=ONE}, Card{color=BLUE, type=TWO}, Card{color=BLUE, type=TWO}, Card{color=BLUE, type=THREE}, Card{color=BLUE, type=THREE}, Card{color=BLUE, type=FOUR}, Card{color=BLUE, type=FOUR}, Card{color=BLUE, type=FIVE}, Card{color=BLUE, type=FIVE}, Card{color=BLUE, type=SIX}, Card{color=BLUE, type=SIX}, Card{color=BLUE, type=SEVEN}, Card{color=BLUE, type=SEVEN}, Card{color=BLUE, type=EIGHT}, Card{color=BLUE, type=EIGHT}, Card{color=BLUE, type=NINE}, Card{color=BLUE, type=NINE}, Card{color=ORANGE, type=ZERO}, Card{color=ORANGE, type=ONE}, Card{color=ORANGE, type=ONE}, Card{color=ORANGE, type=TWO}, Card{color=ORANGE, type=TWO}, Card{color=ORANGE, type=THREE}, Card{color=ORANGE, type=THREE}, Card{color=ORANGE, type=FOUR}, Card{color=ORANGE, type=FOUR}, Card{color=ORANGE, type=FIVE}, Card{color=ORANGE, type=FIVE}, Card{color=ORANGE, type=SIX}, Card{color=ORANGE, type=SIX}, Card{color=ORANGE, type=SEVEN}, Card{color=ORANGE, type=SEVEN}, Card{color=ORANGE, type=EIGHT}, Card{color=ORANGE, type=EIGHT}, Card{color=ORANGE, type=NINE}, Card{color=ORANGE, type=NINE}, Card{color=YELLOW, type=ZERO}, Card{color=YELLOW, type=ONE}, Card{color=YELLOW, type=ONE}, Card{color=YELLOW, type=TWO}, Card{color=YELLOW, type=TWO}, Card{color=YELLOW, type=THREE}, Card{color=YELLOW, type=THREE}, Card{color=YELLOW, type=FOUR}, Card{color=YELLOW, type=FOUR}, Card{color=YELLOW, type=FIVE}, Card{color=YELLOW, type=FIVE}, Card{color=YELLOW, type=SIX}, Card{color=YELLOW, type=SIX}, Card{color=YELLOW, type=SEVEN}, Card{color=YELLOW, type=SEVEN}, Card{color=YELLOW, type=EIGHT}, Card{color=YELLOW, type=EIGHT}, Card{color=YELLOW, type=NINE}, Card{color=YELLOW, type=NINE}, Card{color=GRAY, type=ZERO}, Card{color=GRAY, type=ONE}, Card{color=GRAY, type=ONE}, Card{color=GRAY, type=TWO}, Card{color=GRAY, type=TWO}, Card{color=GRAY, type=THREE}, Card{color=GRAY, type=THREE}, Card{color=GRAY, type=FOUR}, Card{color=GRAY, type=FOUR}, Card{color=GRAY, type=FIVE}, Card{color=GRAY, type=FIVE}, Card{color=GRAY, type=SIX}, Card{color=GRAY, type=SIX}, Card{color=GRAY, type=SEVEN}, Card{color=GRAY, type=SEVEN}, Card{color=GRAY, type=EIGHT}, Card{color=GRAY, type=EIGHT}, Card{color=GRAY, type=NINE}, Card{color=GRAY, type=NINE}]";
    String unshuffledTopCard = "Card{color=BLUE, type=ZERO}";

    /**
     * Creates a new deck before every individual test
     *
     * @author Lily Romano
     */
    @Before
    public void setUp() {
        testDeck = new DrawDeck();
    }

    /**
     * Deletes the deck before every individual test
     *
     * @author Lily Romano
     */
    @After
    public void tearDown() {
        testDeck = null;
    }

    /**
     * Tests creating a deck with the correct cards as well as the getDeck()
     * method.
     *
     * @author Lily Romano
     */
    @Test
    public void testFullDeck() {
        System.out.println("testFullDeck");

        String newDeckString = testDeck.toString();

        assertEquals(defaultDeckString, newDeckString);
    }

    /**
     * Test top card is popped correctly
     */
    @Test
    public void testTopCare() {
        assertEquals(unshuffledTopCard, testDeck.popTopCard().toString());
    }

    /**
     * Test the shuffle method, of class DrawDeck.
     *
     * @author Lily Romano
     */
    @Test
    public void testShuffle() {
        System.out.println("testShuffle");

        /*Ensure unshuffed deck is correct,
          otherwise asserting false after shuffle would pass
          even if shuffle was not working*/
        String newDeckString = testDeck.toString();
        assertEquals(defaultDeckString, newDeckString);

        //Shuffle and assert
        testDeck.shuffle();
        String newShuffledDeckString = testDeck.toString();
        assertFalse(defaultDeckString.equals(newShuffledDeckString));
    }

    /**
     * Test of popTopCard method, of class DrawDeck.
     *
     * @author Lily Romano
     */
    @Test
    public void testRemoveNextCard() {
        System.out.println("testRemoveNextCard");

        System.out.println("testRemoveNextCard");

        Card firstCard = testDeck.popTopCard();
        Card secondCard = testDeck.popTopCard();

        //test that second card does not equal first card
        assertFalse(firstCard.equals(secondCard));
    }

//    /**
//     * Test that EmptyDeckException is thrown correctly on empty deck and test
//     * removeAllCards() method.
//     */
//    @Test
//    public void testRemoveNextCardEmptyDeckException() {
//        System.out.println("testRemoveNextCardEmptyDeckException");
//
//        //Remove all cards in deck, fail if exception while deck still has cards
//        testDeck.removeAllCards();
//
//        //Confirm deck is empty
//        assertEquals(0, testDeck.getDeckSize());
//
//        //Attempt to remove card from empty deck
//        try {
//            testDeck.popTopCard();
//            fail("EmptyDeckException should have been throw for removeNextCard().  Should not have thrown exception");
//        } catch (EmptyDeckException e) {
//            assertEquals("class deck.DrawDeck is empty", e.getMessage());
//        }
//    }
    /**
     * Test of addCard method and the peekTopCard method, of class DrawDeck.
     *
     * @author Lily Romano
     */
    @Test
    public void testAddCard() {
        System.out.println("testAddCard");

        //get top card
        Card addCard = testDeck.popTopCard();

        //empty deck and then readd card
        testDeck.removeAllCards();
        testDeck.addCard(addCard);

        //Confirm deck has one card
        assertEquals(1, testDeck.getDeckSize());

        //Confirm card added is correct
        assertEquals(addCard, testDeck.peekTopCard());
    }
}
