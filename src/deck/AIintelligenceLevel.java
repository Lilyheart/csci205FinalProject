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
    STUPID(0.2),
    MEDIUM(0.5),
    SMART(0.85),
    BRILLIANT(0.99);

    /**
     * The percent (1=100%) of the time the AI catches that BUno should have
     * been called.
     *
     * @author Rachel Wang
     */
    private final double bunoCatch;

    private AIintelligenceLevel(double bunoCatch) {
        this.bunoCatch = bunoCatch;
    }

    public double getBunoCatchPercent() {
        return bunoCatch;
    }
}
