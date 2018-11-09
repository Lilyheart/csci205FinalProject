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
* File: CardPrototypeController
* Description: A GUI controller for the Card Prototype
*
* ****************************************
 */
package gui;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * A GUI Card Prototype MVC controller
 *
 * @version 0.1
 * @author Lily Romano
 */
public class CardPrototypeController implements EventHandler<Event> {

    /**
     * The model for the Card Prototype
     */
    private final CardPrototypeModel theModel;

    /**
     * The view for the Card Prototype
     */
    private final CardPrototypeView theView;

    /**
     * An explicit constructor for the Card Prototype controller.
     *
     * @param theModel The model for the Card Prototype
     * @param theView The view for the Card Prototype
     */
    @SuppressWarnings("LeakingThisInConstructor")
    public CardPrototypeController(CardPrototypeModel theModel,
                                   CardPrototypeView theView) {
        this.theModel = theModel;
        this.theView = theView;

        this.theView.getRootNode().addEventFilter(KeyEvent.KEY_PRESSED, this);
        this.theView.getFaceDown().setOnMouseClicked(this);
        this.theView.getFaceUp().setOnMouseClicked(this);
    }

    @Override
    public void handle(Event event) {
        EventType eType = event.getEventType();

        if (eType == KeyEvent.KEY_PRESSED) {
            KeyEvent target = (KeyEvent) event;
            if (target.getCode() == KeyCode.ESCAPE) {
                Platform.exit();
            }
        }
    }

}
