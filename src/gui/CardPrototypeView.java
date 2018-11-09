/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: James Kelly, Scott Little, Rachel Wang, Lily Romano
* Date: Nov 9, 2018
* Time: 9:56:34 AM
*
* Project: csci205FinalProject
* Package: gui
* File: CardPrototypeView
* Description:
*
* ****************************************
 */
package gui;

import javafx.scene.layout.BorderPane;

/**
 * A GUI Card Prototype MVC view
 *
 * @version 0.1
 * @author Lily Romano
 */
public class CardPrototypeView {

    /**
     * The root node of the view which is a BorderPane
     */
    private final BorderPane root;

    /**
     * The model for the Card Prototype
     */
    private final CardPrototypeModel theModel;

    /**
     * An explicit constructor for the Card Prototype view
     *
     * @param theModel The model for the Card Prototype
     */
    public CardPrototypeView(CardPrototypeModel theModel) {
        this.theModel = theModel;

        root = new BorderPane();
    }

    /**
     * Returns the root BorderPane node
     *
     * @return the root BorderPane node
     */
    public BorderPane getRootNode() {
        return root;
    }

}
