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
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

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
    StackPane faceUp;

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
        faceUp = new StackPane();
        grid.add(faceUp, 1, 0);
        faceUp.setPrefSize(128, 178);
        faceUp.getStyleClass().add("card");

        //Set Inside of face
        VBox inside = new VBox();
        inside.getStyleClass().add("inside");
        faceUp.getChildren().add(inside);
        StackPane.setMargin(inside, new Insets(5));

        String string = "1";

        Label top = new Label(string);
        top.getStyleClass().add("top");
        top.setMinWidth(118);
        top.setPrefHeight(20);

        Label middle = new Label(string);
        middle.getStyleClass().add("middle");
        middle.setMinWidth(118);
        middle.setPrefHeight(138);

        Label bottom = new Label(string);
        bottom.getStyleClass().add("bottom");
        bottom.setMinWidth(118);
        bottom.setPrefHeight(20);

        inside.getChildren().addAll(top, middle, bottom);
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

    public StackPane getFaceUp() {
        return faceUp;
    }

}
