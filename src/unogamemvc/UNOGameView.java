/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: James Kelly, Scott Little, Rachel Wang, Lily Romano
* Date: Nov 9, 2018
* Time: 12:38:55 PM
*
* Project: csci205FinalProject
* Package: view
* File: UNOGameView
* Description:
*
* ****************************************
 */
package unogamemvc;

import deck.EmptyDeckException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import unogamemvc.cardcreator.CardBackView;
import unogamemvc.cardcreator.CardFrontView;

/**
 * A GUI Card Prototype MVC view Main GUI
 *
 * @author Lily Romano
 * @author jameskelly
 */
public class UNOGameView {

    /**
     * The root node of the view which is a BorderPane
     */
    private final BorderPane root;

    /**
     * The model for the UNO game
     */
    private final UNOGameModel theModel;

    /**
     * An explicit constructor for the UNO game view
     *
     * @author Lily Romano
     * @author jameskelly
     *
     * @param theModel The model for the UNO game
     * @throws deck.EmptyDeckException
     */
    public UNOGameView(UNOGameModel theModel) throws EmptyDeckException {
        //TODO [Exception Handling]

        this.theModel = theModel;

        root = new BorderPane();
        root.setId("rootNode");

        HBox playersHand = new HBox();
        playersHand.setSpacing(100);
        playersHand.setPadding(new Insets(10, 10, 10, 10));

        GridPane cardsInPlayersHand = new GridPane();
        cardsInPlayersHand.setHgap(20);
        cardsInPlayersHand.setVgap(20);
        cardsInPlayersHand.setPadding(new Insets(40));

        /*TODO [Card Display] The way these cards are being created, they cannot be
          referenced by the controller.  A suggestion would be to create a class
          instance variable that stores some type of datastructure that makes sense
          cards and work from there.
         */
        //make list of cards, add each card to pane, just pass future players hand of cards
        //  instead of hardcoding
        for (int i = 0; i <= 7; i++) {
            StackPane faceUpCard = CardFrontView.createCardFrontView(
                    theModel.getNextDrawCard());
            cardsInPlayersHand.add(faceUpCard, i, 0);
        }

        playersHand.getChildren().add(cardsInPlayersHand);

        VBox opponentsAndDeck = new VBox();
        opponentsAndDeck.setSpacing(100);
        opponentsAndDeck.setPadding(new Insets(10, 10, 10, 10));

        //create the model of opponents
        GridPane opponents = new GridPane();
        opponents.setHgap(20);
        opponents.setVgap(20);
        opponents.setPadding(new Insets(20, 0, 0, 0));
        opponents.setAlignment(Pos.TOP_CENTER);

        for (int i = 0; i < 3; i++) {
            StackPane opponent = new StackPane();
            /* TODO [Card Display] This is hardcoded to seven not linked to
               the actual number in the oppenent's hand.  This needs to be
               linked once that code exists.
             */
            //Make bottom card
            StackPane bottomCard = CardBackView.createCardBackView();
            opponent.getChildren().add(bottomCard);

            //Make next card
            for (int j = 1; j < 7; j++) {
                StackPane nextCard = CardBackView.createCardBackView();
                nextCard.setTranslateX(15 * j);
                opponent.getChildren().add(nextCard);
            }

            //Add opponent to opponents
            /* TODO [Card Display] The hands stack in a weird way*/
            opponents.add(opponent, i, 0);
        }

        GridPane decks = new GridPane();
        decks.setHgap(20);
        decks.setVgap(20);
        decks.setPadding(new Insets(40));

        //create a card to represent the draw deck
        //TODO [Card Display] Draw deck should show top card of draw pile.
        StackPane drawDeck = new StackPane();
        drawDeck.setPrefSize(128, 178);
        drawDeck.setMaxSize(128, 178);

        //create a card to represent the discard deck
        StackPane discardDeck = new StackPane();
        discardDeck.setPrefSize(128, 178);
        discardDeck.setMaxSize(128, 178);

        decks.add(drawDeck, 0, 0);
        decks.add(discardDeck, 1, 0);
        decks.setAlignment(Pos.CENTER);

        opponentsAndDeck.getChildren().add(opponents);
        opponentsAndDeck.getChildren().add(decks);
        opponentsAndDeck.setAlignment(Pos.CENTER);

        //create the right box that hold the button
        VBox rightPanel = new VBox();
        rightPanel.setPadding(new Insets(10, 50, 10, 10));
        rightPanel.setAlignment(Pos.BOTTOM_RIGHT);
        rightPanel.setSpacing(50);
        Button UNOButton = new Button();
        UNOButton.setText("BUno!");
        UNOButton.setPrefWidth(100);
        rightPanel.getChildren().add(UNOButton);

        root.setCenter(opponentsAndDeck);
        root.setRight(rightPanel);
        root.setBottom(playersHand);

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

}
