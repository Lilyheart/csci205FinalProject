/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: James Kelly, Scott Little, Rachel Wang, Lily Romano
* Date: Nov 9, 2018
* Time: 9:56:42 AM
*
* Project: csci205FinalProject
* Package: gui
* File: CardPrototypeController
* Description: A GUI controller for the Card Prototype
*
* ****************************************
 */
package gui;

/**
 * A GUI Card Prototype MVC controller
 *
 * @version 0.1
 * @author Lily Romano
 */
public class CardPrototypeController {

    /**
     * The model for the Card Prototype
     */
    private final CardPrototypeModel theModel;

    /**
     * The view for the Card Prototype
     */
    private final CardPrototypeView theView;

    /**
     * An explicit constructor for the Card Prototype controller.
     *
     * @param theModel The model for the Card Prototype
     * @param theView The view for the Card Prototype
     */
    public CardPrototypeController(CardPrototypeModel theModel,
                                   CardPrototypeView theView) {
        this.theModel = theModel;
        this.theView = theView;
    }

}
