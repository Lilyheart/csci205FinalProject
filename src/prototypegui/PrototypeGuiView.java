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
* File: PrototypeGuiView
* Description:
*
* ****************************************
 */
package prototypegui;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import prototypegui.cardcreator.CardBackView;
import prototypegui.cardcreator.CardFrontView;

/**
 * A GUI Card Prototype MVC view Main GUI
 *
 * @author Lily Romano
 */
public class PrototypeGuiView {

    /**
     * The root node of the view which is a BorderPane
     */
    private final BorderPane root;

    /**
     * The model for the Card Prototype
     */
    private final PrototypeGuiModel theModel;

    /**
     * The StackPane of the facedown pile of cards.
     */
    StackPane faceDown;

    /**
     * The StackPane of the faceup pile of cards.
     */
    StackPane faceUp;

    /**
     * An explicit constructor for the Card Prototype view Main GUI
     *
     * @author Lily Romano
     *
     * @param theModel The model for the Card Prototype Main GUI
     */
    public PrototypeGuiView(PrototypeGuiModel theModel) {
        this.theModel = theModel;

        root = new BorderPane();
        root.setId("rootNode");
        GridPane grid = new GridPane();
        root.setCenter(grid);

        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(40));

        //Face down card
        faceDown = CardBackView.createCardBackView();
        grid.add(faceDown, 0, 0);

        //Face up card
        faceUp = CardFrontView.createCardFrontView(
                theModel.getNextCard());
        grid.add(faceUp, 1, 0);
    }

    /**
     * Returns the root BorderPane node
     *
     * @author Lily Romano
     *
     * @return the root BorderPane node
     */
    public BorderPane getRootNode() {
        return root;
    }

    /**
     * Returns the StackPane of the facedown pile of cards.
     *
     * @author Lily Romano
     *
     * @return the StackPane of the facedown pile of cards.
     */
    public StackPane getFaceDownPane() {
        return faceDown;
    }

    /**
     * Returns the StackPane of the faceup pile of cards.
     *
     * @author Lily Romano
     *
     * @return the StackPane of the faceup pile of cards.
     */
    public StackPane getFaceUpPane() {
        return faceUp;
    }
}
