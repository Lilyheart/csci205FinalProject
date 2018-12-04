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
    //TODO [!Special Cards] Switch Active string test
//    String defaultDeckString = "Draw Deck: [Blue Zero, Blue One, Blue One, Blue Two, Blue Two, Blue Three, Blue Three, Blue Four, Blue Four, Blue Five, Blue Five, Blue Six, Blue Six, Blue Seven, Blue Seven, Blue Eight, Blue Eight, Blue Nine, Blue Nine, Blue Draw2, Blue Draw2, Blue Reverse, Blue Reverse, Blue Skip, Blue Skip, Orange Zero, Orange One, Orange One, Orange Two, Orange Two, Orange Three, Orange Three, Orange Four, Orange Four, Orange Five, Orange Five, Orange Six, Orange Six, Orange Seven, Orange Seven, Orange Eight, Orange Eight, Orange Nine, Orange Nine, Orange Draw2, Orange Draw2, Orange Reverse, Orange Reverse, Orange Skip, Orange Skip, Yellow Zero, Yellow One, Yellow One, Yellow Two, Yellow Two, Yellow Three, Yellow Three, Yellow Four, Yellow Four, Yellow Five, Yellow Five, Yellow Six, Yellow Six, Yellow Seven, Yellow Seven, Yellow Eight, Yellow Eight, Yellow Nine, Yellow Nine, Yellow Draw2, Yellow Draw2, Yellow Reverse, Yellow Reverse, Yellow Skip, Yellow Skip, Gray Zero, Gray One, Gray One, Gray Two, Gray Two, Gray Three, Gray Three, Gray Four, Gray Four, Gray Five, Gray Five, Gray Six, Gray Six, Gray Seven, Gray Seven, Gray Eight, Gray Eight, Gray Nine, Gray Nine, Gray Draw2, Gray Draw2, Gray Reverse, Gray Reverse, Gray Skip, Gray Skip, Special Wild, Special Wild, Special Wild, Special Wild, Special Wilddraw4, Special Wilddraw4, Special Wilddraw4, Special Wilddraw4]";
    String defaultDeckString = "Draw Deck: [Blue Zero, Blue One, Blue One, Blue Two, Blue Two, Blue Three, Blue Three, Blue Four, Blue Four, Blue Five, Blue Five, Blue Six, Blue Six, Blue Seven, Blue Seven, Blue Eight, Blue Eight, Blue Nine, Blue Nine, Orange Zero, Orange One, Orange One, Orange Two, Orange Two, Orange Three, Orange Three, Orange Four, Orange Four, Orange Five, Orange Five, Orange Six, Orange Six, Orange Seven, Orange Seven, Orange Eight, Orange Eight, Orange Nine, Orange Nine, Yellow Zero, Yellow One, Yellow One, Yellow Two, Yellow Two, Yellow Three, Yellow Three, Yellow Four, Yellow Four, Yellow Five, Yellow Five, Yellow Six, Yellow Six, Yellow Seven, Yellow Seven, Yellow Eight, Yellow Eight, Yellow Nine, Yellow Nine, Gray Zero, Gray One, Gray One, Gray Two, Gray Two, Gray Three, Gray Three, Gray Four, Gray Four, Gray Five, Gray Five, Gray Six, Gray Six, Gray Seven, Gray Seven, Gray Eight, Gray Eight, Gray Nine, Gray Nine]";
    String unshuffledTopCard = "Blue Zero";

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
    public void testTopCard() {
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
        System.out.println(newDeckString);
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
