/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: James Kelly, Scott Little, Rachel Wang, Lily Romano
* Date: Nov 12, 2018
* Time: 4:55:48 PM
*
* Project: csci205FinalProject
* Package: prototypegui.cardcreator
* File: CardBackView
* Description:
*
* ****************************************
 */
package unogamemvc.cardcreator;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import unogamemvc.resource.CardSettings;

/**
 * Utility class for updating existing StackPanes to display card fronts
 *
 * @author Lily Romano
 */
public final class CardBackView {

    /**
     * Creates a StackPane that represents the back of a card
     *
     * @return The StackPane for displaying
     */
    public static StackPane createCardBackView() {
        StackPane faceDown = new StackPane();

        faceDown.setPrefSize(CardSettings.OUTER_CARD_WIDTH,
                             CardSettings.OUTER_CARD_HEIGHT);
        faceDown.getStyleClass().add("card");

        //Set Inside of face
        VBox inside = new VBox();
        inside.getStyleClass().add("inside");
        inside.getStyleClass().add("insideback");
        faceDown.getChildren().add(inside);
        StackPane.setMargin(inside, new Insets(CardSettings.OUTER_CARD_MARGIN));

        Label logo = new Label("BUno");
        logo.getStyleClass().add("logo");

        inside.getChildren().add(logo);

        return faceDown;
    }

}
