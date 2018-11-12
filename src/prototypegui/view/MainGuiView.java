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
* File: MainGuiView
* Description:
*
* ****************************************
 */
package prototypegui.view;

import prototypegui.model.MainGuiModel;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

/**
 * A GUI Card Prototype MVC view Main GUI
 *
 * @version 0.1
 * @author Lily Romano
 */
public class MainGuiView {

    /**
     * The root node of the view which is a BorderPane
     */
    private final BorderPane root;

    /**
     * The model for the Card Prototype
     */
    private final MainGuiModel theModel;

    StackPane faceDown;
    Node faceUp;

    /**
     * An explicit constructor for the Card Prototype view Main GUI
     *
     * @param theModel The model for the Card Prototype Main GUI
     */
    public MainGuiView(MainGuiModel theModel) {
        this.theModel = theModel;

        root = new BorderPane();
        root.setId("rootNode");
        GridPane grid = new GridPane();
        root.setCenter(grid);

        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(40));

        //Face down card
        faceDown = new StackPane();
        grid.add(faceDown, 0, 0);
        faceDown.setPrefSize(128, 178);
        faceDown.getStyleClass().add("card");

        //Face up card
        faceUp = CardFrontView.createCardFrontView();
        grid.add(faceUp, 1, 0);
    }

    /**
     * Returns the root BorderPane node
     *
     * @return the root BorderPane node
     */
    public BorderPane getRootNode() {
        return root;
    }

    public StackPane getFaceDown() {
        return faceDown;
    }

    public Node getFaceUp() {
        return faceUp;
    }
}
