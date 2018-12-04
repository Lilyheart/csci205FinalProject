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

import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import unogame.AIHelper;
import unogame.RoundOverException;
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
                             UNOGameView theView) {
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

        if (eType == KeyEvent.KEY_PRESSED) {
            if (theModel.getUnoGame().isPrintDEBUG()) {
                KeyEvent target = (KeyEvent) event;
                if (target.getCode() == KeyCode.ESCAPE) {
                    Platform.exit();
                }
            }

        }
        else if (eType == MouseEvent.MOUSE_CLICKED) {
            Object source = event.getSource();
            if (source instanceof StackPane && !theModel.isIsComputerTurn()) { //Any card clicked
                boolean isLegalPlay = false;

                if (theModel.getUnoGame().isPrintDEBUG()) {
                    System.out.println(
                            "StackPaneID Clicked: " + ((StackPane) source).getId());
                }

                //Plays the card via unogame.Game
                isLegalPlay = theModel.tryToPlayCardAction(
                        Integer.parseInt(((StackPane) source).getId()));

                //Redraws the discard deck
                updateDiscardDeck();

                //Clears the players and and redraws
                theView.getCardsInPlayersHandPane().getChildren().clear();
                theView.drawPlayerHandPane();

                this.activateCardsInPlayersHand();

                try {
                    theModel.checkAndRunEndOfTurn();
                    if (isLegalPlay) {
                        startComputerTask();
                    }
                } catch (RoundOverException ex) {
                    theView.drawPanes();
                }
            }

        }

    }

    /**
     * Updates the discard deck
     */
    private void updateDiscardDeck() {
        theView.getDiscardDeckPane().getChildren().add(
                CardFrontView.createCardFrontView(
                        theModel.getUnoGame().getDiscardCardCard()));
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
     * Starts thread to run the opponents turn
     *
     * @author James Kelly
     */
    public void startComputerTask() {
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

    /**
     * Runs the opponents turn
     *
     * @author James Kelly
     */
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

                //Sleep the computer for 2 seconds
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException ex) {
                    //If sleep doesn't happen, game can continue to progress, it's purely for effect
                }

                AIHelper.computerTurn(theModel.getUnoGame(), i);

                try {
                    // Update the card on the JavaFx Application Thread
                    Platform.runLater((new Runnable() {
                        @Override
                        public void run() {
                            updateDiscardDeck();
                            theView.drawOpponentsPane();
                        }
                    }));
                    Thread.sleep(1000);

                    // Update the card on the JavaFx Application Thread
                    Platform.runLater((new Runnable() {
                        @Override
                        public void run() {
                            try {
                                theModel.checkAndRunEndOfTurn();
                            } catch (RoundOverException ex) {
                                theView.drawPanes();
                            }
                        }
                    }));
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    System.out.println("Error: " + ex);
                }

            }
        } catch (InterruptedException ex) {
            //If sleep doesn't happen, game can continue to progress, it's purely for effect
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
                        //draw the card into the play
                        theModel.tryToDrawCardAction(); // pops card from the deck

                        //redraw the hand
                        theView.drawPlayerHandPane();
                        //theView.getOpponentsPane().getChildren().clear();

                        activateCardsInPlayersHand();

                        startComputerTask();
                    }
                }

            });

        });
    }

}
