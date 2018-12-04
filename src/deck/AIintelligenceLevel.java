/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: James Kelly, Scott Little, Rachel Wang, Lily Romano
* Date: Dec 3, 2018
* Time: 3:28:22 PM
*
* Project: csci205FinalProject
* Package: unogame
* File: AIintelligenceLevel
* Description:
*
* ****************************************
 */
package deck;

/**
 *
 * @author Rachel Wang
 */
public enum AIintelligenceLevel {

    /**
     * A stupid level of AI that catches BUno 20% of the time
     */
    STUPID(0.2),
    /**
     * A medium level of AI that catches BUno 50% of the time
     */
    MEDIUM(0.5),
    /**
     * A smart level of AI that catches BUno 85% of the time
     */
    SMART(0.85),
    /**
     * A brilliant level of AI that catches BUno 99% of the time
     */
    BRILLIANT(0.99);

    /**
     * The percent (1 represents 100%) of the time the AI catches that BUno
     * should have been called.
     *
     * @author Rachel Wang
     */
    private final double bunoCatch;

    /**
     * An explicit constructor for the AI intelligence Level enumerated type
     *
     * @param bunoCatch The percent of the time the AI catches that BUNno should
     * be called
     */
    private AIintelligenceLevel(double bunoCatch) {
        this.bunoCatch = bunoCatch;
    }

    /**
     * Returns the percent, as a decimal (50% is returns as 0.5), that the AI
     * should catch that BUno should be called.
     *
     * @return The percent of the time the AI catches that BUNno should be
     * called
     */
    public double getBunoCatchPercent() {
        return bunoCatch;
    }
}
