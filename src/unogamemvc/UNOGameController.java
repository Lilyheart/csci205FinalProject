/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: James Kelly, Scott Little, Rachel Wang, Lily Romano
* Date: Nov 9, 2018
* Time: 12:43:19 PM
*
* Project: csci205FinalProject
* Package: view
* File: UNOGameController
* Description:
*
* ****************************************
 */
package unogamemvc;

import deck.EmptyDeckException;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import unogamemvc.cardcreator.CardFrontView;

/**
 * A GUI Card Prototype MVC controller
 *
 * @author Lily Romano
 */
public class UNOGameController implements EventHandler<Event> {

    /**
     * The model for the UNO game
     */
    private final UNOGameModel theModel;

    /**
     * The view for the UNO game
     */
    private final UNOGameView theView;

    private int cardGUIIndex;

    /**
     * An explicit constructor for the UNO game controller.
     *
     * @author Lily Romano
     *
     * @param theModel The model for the UNO game
     * @param theView The view for the UNO game
     */
    @SuppressWarnings("LeakingThisInConstructor")
    public UNOGameController(UNOGameModel theModel,
            UNOGameView theView) throws EmptyDeckException {
        this.theModel = theModel;
        this.theView = theView;
        //this.cardGUIIndex = cardGUIIndex;
        this.theView.getRootNode().addEventFilter(KeyEvent.KEY_PRESSED, this);

        this.activateCardsInPlayersHand();
        this.activateDrawDeck();

    }

    /**
     * Handles events bound in this controller.
     *
     * @author Lily Romano
     *
     * @param event The event that triggered this method
     */
    @Override
    public void handle(Event event) {
        EventType eType = event.getEventType();

        //TODO [Final Project] Remove as esc should not close program during normal operation
        if (eType == KeyEvent.KEY_PRESSED) {
            KeyEvent target = (KeyEvent) event;
            if (target.getCode() == KeyCode.ESCAPE) {
                Platform.exit();
            }

        }
        else if (eType == MouseEvent.MOUSE_CLICKED) {
            Object source = event.getSource();
            if (source instanceof StackPane) { //Any card clicked
                System.out.println(((StackPane) source).getId());
                try {
                    System.out.println("XXXX"
                            + theModel.getUnoGame().getTheDiscardDeck().peekBottomCard());
                    theModel.tryToPlayCardAction(
                            Integer.parseInt(((StackPane) source).getId()));

                    theView.getDiscardDeckPane().getChildren().add(
                            CardFrontView.createCardFrontView(
                                    theModel.getUnoGame().getTheDiscardDeck().peekBottomCard()));

                    theView.getCardsInPlayersHandPane().getChildren().clear();
                    theView.drawPlayerHandPane();
                    System.out.println(theModel.getUnoGame().getPlayersHandCopy(
                            theModel.getHUMAN_PLAYER()));

                } catch (EmptyDeckException ex) {
                    System.out.println("EMPTY DECK EXCEPTION");
                }
                this.activateCardsInPlayersHand();
                //this.theView.drawPlayerHandPane();
            }

        }

//        }
    }

    /**
     *
     * Creates an event when the cards in the players hands are clicked on.
     * Prints to the console as of now.
     */
    public void activateCardsInPlayersHand() {
        this.theView.getCardsInPlayersHandPane().getChildren().forEach(item -> {
            item.setOnMouseClicked(this);

        });
    }

    /**
     * Creates an event when the draw deck is clicked on. Puts the card in your
     * hand. Prints to the console as of now.
     */
    public void activateDrawDeck() {
        this.theView.getDrawDeckPane().getChildren().forEach(item -> {
            item.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println(
                            "***\nClicked on draw deck...next added to hand***");

                    //draw the card into the play
                    theModel.tryToDrawCardAction(); // pops card from the deck

                    //redraw the hand
                    theView.drawPlayerHandPane();
                    activateCardsInPlayersHand();

                }
            });

        });
    }

}
