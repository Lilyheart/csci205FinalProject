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
* File: MainGuiController
* Description: A GUI controller for the Card Prototype
*
* ****************************************
 */
package prototypegui;

import deck.card.Card;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import prototypegui.cardcreator.CardFrontView;

/**
 * A GUI Card Prototype MVC controller
 *
 * @author Lily Romano
 */
public class MainGuiController implements EventHandler<Event> {

    /**
     * The model for the Card Prototype Main GUI
     */
    private final MainGuiModel theModel;

    /**
     * The view for the Card Prototype Main GUI
     */
    private final MainGuiView theView;

    /**
     * An explicit constructor for the Card Prototype controller Main GUI.
     *
     * @param theModel The model for the Card Prototype
     * @param theView The view for the Card Prototype
     */
    @SuppressWarnings("LeakingThisInConstructor")
    public MainGuiController(MainGuiModel theModel,
                             MainGuiView theView) {
        this.theModel = theModel;
        this.theView = theView;

        this.theView.getRootNode().addEventFilter(KeyEvent.KEY_PRESSED, this);
        this.theView.getFaceDownPane().setOnMouseClicked(this);
        this.theView.getFaceUpPane().setOnMouseClicked(this);

        this.theView.getFaceDownComputerPane().setOnMouseClicked(this);
        this.theView.getFaceUpComputerPane().setOnMouseClicked(this);

        this.theView.getBtn().setOnMouseClicked(this);
    }

    @Override
    public void handle(Event event) {
        EventType eType = event.getEventType();

        if (event.getSource() == theView.getFaceDownPane()) {
            Card newCard = theModel.getNextCard();
            CardFrontView.changeCardFrontView(newCard,
                                              (StackPane) theView.getFaceUpPane());
        }
        else if (event.getSource() == theView.getBtn()) {
            System.out.println("XXX");
            startTask();
        }

        if (eType == KeyEvent.KEY_PRESSED) {
            KeyEvent target = (KeyEvent) event;
            if (target.getCode() == KeyCode.ESCAPE) {
                Platform.exit();
            }
        }
    }

    public void startTask() {
        // Create a Runnable
        Runnable task = new Runnable() {
            public void run() {
                runTask();
            }
        };

        // Run the task in a background thread
        Thread backgroundThread = new Thread(task);
        // Terminate the running thread if the application exits
        backgroundThread.setDaemon(true);
        // Start the thread
        backgroundThread.start();
    }

    public void runTask() {
        for (int i = 0; i < 1000; i++) {
            try {
                // Update the card on the JavaFx Application Thread
                Platform.runLater((new Runnable() {
                    @Override
                    public void run() {
                        Card newCard = theModel.getNextComputerCard();
                        CardFrontView.changeCardFrontView(newCard,
                                                          (StackPane) theView.getFaceUpComputerPane());
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
        }
    }

}
