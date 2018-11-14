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
* File: PrototypeGuiController
* Description: A GUI controller for the Card Prototype
*
* ****************************************
 */
package prototypegui;

import prototypegui.cardcreator.CardFrontView;
import deck.card.Card;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;

/**
 * A GUI Card Prototype MVC controller
 *
 * @author Lily Romano
 */
public class PrototypeGuiController implements EventHandler<Event> {

    /**
     * The model for the Card Prototype Main GUI
     */
    private final PrototypeGuiModel theModel;

    /**
     * The view for the Card Prototype Main GUI
     */
    private final PrototypeGuiView theView;

    /**
     * An explicit constructor for the Card Prototype controller Main GUI.
     *
     * @param theModel The model for the Card Prototype
     * @param theView The view for the Card Prototype
     */
    @SuppressWarnings("LeakingThisInConstructor")
    public PrototypeGuiController(PrototypeGuiModel theModel,
                             PrototypeGuiView theView) {
        this.theModel = theModel;
        this.theView = theView;

        this.theView.getRootNode().addEventFilter(KeyEvent.KEY_PRESSED, this);
        this.theView.getFaceDownPane().setOnMouseClicked(this);
        this.theView.getFaceUpPane().setOnMouseClicked(this);
    }

    @Override
    public void handle(Event event) {
        EventType eType = event.getEventType();

        if (event.getSource() == theView.getFaceDownPane()) {
            Card newCard = theModel.getNextCard();
            CardFrontView.changeCardFrontView(newCard,
                                              (StackPane) theView.getFaceUpPane());
        }

        if (eType == KeyEvent.KEY_PRESSED) {
            KeyEvent target = (KeyEvent) event;
            if (target.getCode() == KeyCode.ESCAPE) {
                Platform.exit();
            }
        }
    }

}
