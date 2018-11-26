/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: James Kelly, Scott Little, Rachel Wang, Lily Romano
* Date: Nov 26, 2018
* Time: 11:29:50 AM
*
* Project: csci205FinalProject
* Package: unogame.playhelpers
* File: CLIHelper
* Description:
*
* ****************************************
 */
package unogame.playhelpers;

import deck.card.Card;
import java.util.Scanner;

/**
 *
 * @author Lily Romano
 */
public final class CLIHelper {

    private static final Scanner KEYBOARD = new Scanner(System.in);

    /**
     * Returns an integer from the keyboard input. Acceptable values range from
     * 1 to maxValue.
     *
     * @param maxValue the maximum value allowed. Should be greater than or
     * equal to one.
     * @return the integer input by the keyboard.
     */
    public static int getKeyboardInt(int maxValue) {
        int response = -1;
        //TODO [Basic Game] Test to ensure maxValue >= 1

        while (response < 1 || response > maxValue) {
            if (KEYBOARD.hasNextInt()) {
                response = KEYBOARD.nextInt();
                if (response < 1 || response > maxValue) {
                    System.out.print(">> Input a valid option");
                }
            }
            else {
                System.out.print(">> You didn't input a number.  "
                                 + "Please input a number");
            }
        }

        return response;
    }

    /**
     * Returns a Card description in easy to read format.
     *
     * @param card
     * @return a Card description in easy to read format.
     */
    public static String easyCardDescription(Card card) {
        return properCase(
                card.getColor().toString() + " " + card.getType().toString());
    }

    /**
     * Returns a string as proper case. If more than one word is sent, each word
     * is converted to proper case.
     *
     * @param string the string to convert to proper case.
     * @return a string as proper case
     */
    public static String properCase(String string) {
        String[] words = string.split("\\s+");
        String result = "";
        for (int i = 0; i < words.length; i++) {
            String s = words[i];
            words[i] = s.toUpperCase().charAt(0) + s.substring(1, s.length()).toLowerCase();
        }
        return String.join(" ", words);
    }

}
