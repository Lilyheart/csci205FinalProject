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
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import unogame.NoValidCardException;
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
            if (source instanceof StackPane && !theModel.isIsComputerTurn()) { //Any card clicked

                System.out.println(((StackPane) source).getId());

                System.out.println("XXXX"
                                   + theModel.getUnoGame().getTheDiscardDeck().peekBottomCard());
                try {
                    //Plays the card via unogame.Game
                    theModel.tryToPlayCardAction(
                            Integer.parseInt(((StackPane) source).getId()));
                } catch (EmptyDeckException ex) {
                    System.out.println("EMPTY DECK");
                }

                //Redraws the discard deck - TODO [Refactor] Could be own method?
                updateDiscardDeck();

                //Clears the players and and redraws
                theView.getCardsInPlayersHandPane().getChildren().clear();
                theView.drawPlayerHandPane();

                System.out.println(theModel.getUnoGame().getPlayersHandCopy(
                        theModel.getHUMAN_PLAYER()));

                startTask();

                this.activateCardsInPlayersHand();
                //this.theView.drawPlayerHandPane();
            }

        }

//        }
    }

    private void updateDiscardDeck() {
        theView.getDiscardDeckPane().getChildren().add(
                CardFrontView.createCardFrontView(
                        theModel.getUnoGame().getTheDiscardDeck().peekBottomCard()));
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

    public void startTask() {
        // Create a Runnable
        Runnable task = new Runnable() {
            public void run() {
                runOpponentsTurnsTask();
            }
        };

        // Run the task in a background thread
        Thread backgroundThread = new Thread(task);
        // Terminate the running thread if the application exits
        backgroundThread.setDaemon(true);
        // Start the thread
        backgroundThread.start();
    }

    public void runOpponentsTurnsTask() {
        theModel.setIsComputerTurn(true);
        try {

            Platform.runLater((new Runnable() {
                @Override
                public void run() {
//                    theView.getOpponentsPane().getChildren().clear();
                    updateDiscardDeck();
                }
            }));
            Thread.sleep(1000);

            for (int i = 2; i <= 4; i++) {

                try {
                    //Sleep the computer for 2 seconds
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException ex) {
                        //If sleep doesn't happen, game can continue to progress, it's purely for effect
                    }

                    theModel.getUnoGame().computerTurn(i);

                    //TODO redraw stuffs
                    //TODO [GUI] Redraws the discard deck - TODO [Refactor] Could be own method?
                    //TODO [GUI] Redraw the computer's hand
                    //TODO [GUI] Deal with BUno
                    System.out.println("Played " + i
                                       + theModel.getUnoGame().getPlayersHandCopy(
                                    i));
                    System.out.println(
                            "Size of hand" + theModel.getUnoGame().getPlayersHandCopy(
                                    i).size());

                    try {
                        // Update the card on the JavaFx Application Thread
                        Platform.runLater((new Runnable() {
                            @Override
                            public void run() {
                                updateDiscardDeck();
                                theView.createOpponentsPane();
                            }
                        }));
                        Thread.sleep(1000);

                        //Sleep the computer for 2 seconds
                        try {
                            TimeUnit.SECONDS.sleep(2);
                        } catch (InterruptedException ex) {
                            //If sleep doesn't happen, game can continue to progress, it's purely for effect
                        }
                    } catch (InterruptedException e) {
                        System.out.println("Error: " + e);
                    }
                } catch (NoValidCardException ex) {
                    Logger.getLogger(
                            UNOGameController.class.getName()).log(
                            Level.SEVERE, null, ex);
                }

            }
        } catch (EmptyDeckException ex) {
            System.out.println("EMPTY DECK EXCEPTION");
        } catch (InterruptedException ex) {
            Logger.getLogger(UNOGameController.class.getName()).log(Level.SEVERE,
                                                                    null, ex);
        }

        theModel.setIsComputerTurn(false);
    }

    /**
     * Creates an event when the draw deck is clicked on. Puts the card in your
     * hand. Prints to the console as of now.
     */
    public void activateDrawDeck() {
        // add computer turn code here
        this.theView.getDrawDeckPane().getChildren().forEach(item -> {
            item.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {

                    if (!theModel.isIsComputerTurn()) {
                        //TODO [GUI] ?? Move this to method in Model?
                        System.out.println(
                                "***\nClicked on draw deck...next added to hand***");

                        //draw the card into the play
                        theModel.tryToDrawCardAction(); // pops card from the deck

                        //redraw the hand
                        theView.drawPlayerHandPane();
                        //theView.getOpponentsPane().getChildren().clear();

                        activateCardsInPlayersHand();

                        startTask();
                    }
                }

            });

        });
    }

}
