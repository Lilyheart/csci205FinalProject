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
package prototypegui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
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
public class MainGuiView {

    /**
     * The root node of the view which is a BorderPane
     */
    private final BorderPane root;

    /**
     * The model for the Card Prototype
     */
    private final MainGuiModel theModel;

    /**
     * The StackPane of the facedown pile of cards.
     */
    StackPane faceDown;

    /**
     * The StackPane of the faceup pile of cards.
     */
    StackPane faceUp;

    /**
     * Add stuff for two decks
     */
    StackPane faceDownComputer;
    StackPane faceUpComputer;
    Button btn;

    /**
     * An explicit constructor for the Card Prototype view Main GUI
     *
     * @param theModel The model for the Card Prototype Main GUI
     */
    public MainGuiView(MainGuiModel theModel) {
        this.theModel = theModel;

        root = new BorderPane();
        root.setId("rootNode");

        //Create Computer
        GridPane gridComputer = new GridPane();
        root.setCenter(gridComputer);

        gridComputer.setHgap(20);
        gridComputer.setVgap(20);
        gridComputer.setPadding(new Insets(40));

        //Face down card
        faceDownComputer = CardBackView.createCardBackView();
        gridComputer.add(faceDownComputer, 0, 0);

        //Face up card
        faceUpComputer = CardFrontView.createCardFrontView(
                theModel.getNextComputerCard());
        gridComputer.add(faceUpComputer, 1, 0);

        GridPane buttonPane = new GridPane();
        root.setTop(buttonPane);

        btn = new Button("Start");
        buttonPane.add(btn, 0, 0);

        //Create Player
        GridPane grid = new GridPane();
        root.setBottom(grid);

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
     * @return the root BorderPane node
     */
    public BorderPane getRootNode() {
        return root;
    }

    /**
     * Returns the StackPane of the facedown pile of cards.
     *
     * @return the StackPane of the facedown pile of cards.
     */
    public StackPane getFaceDownPane() {
        return faceDown;
    }

    /**
     * Returns the StackPane of the faceup pile of cards.
     *
     * @return the StackPane of the faceup pile of cards.
     */
    public StackPane getFaceUpPane() {
        return faceUp;
    }

    /**
     * Returns the StackPane of the facedown pile of cards.
     *
     * @return the StackPane of the facedown pile of cards.
     */
    public StackPane getFaceDownComputerPane() {
        return faceDownComputer;
    }

    /**
     * Returns the StackPane of the faceup pile of cards.
     *
     * @return the StackPane of the faceup pile of cards.
     */
    public StackPane getFaceUpComputerPane() {
        return faceUpComputer;
    }

    public Button getBtn() {
        return btn;
    }

}
