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
package prototypegui.controller;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import prototypegui.model.MainGuiModel;
import prototypegui.view.MainGuiView;

/**
 * A GUI Card Prototype MVC controller
 *
 * @version 0.1
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
