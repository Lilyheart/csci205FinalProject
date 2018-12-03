/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: James Kelly, Scott Little, Rachel Wang, Lily Romano
* Date: Nov 12, 2018
* Time: 12:24:58 PM
*
* Project: csci205FinalProject
* Package: prototypegui.view
* File: CardFrontView
* Description:
*
* ****************************************
 */
package unogamemvc.cardcreator;

import deck.card.Card;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import unogamemvc.resource.CardSettings;

/**
 * Utility class for creating and updating existing StackPanes to display card
 * fronts
 *
 * @author Lily Romano
 */
public final class CardFrontView {

    /**
     * Creates a StackPane that represents a face up card
     *
     * @author Lily Romano
     *
     * @param card The {@code Card} the pane should display
     * @return The StackPane for displaying
     */
    public static StackPane createCardFrontView(Card card) {
        StackPane faceUp = new StackPane();

        faceUp.setPrefSize(CardSettings.OUTER_CARD_WIDTH,
                           CardSettings.OUTER_CARD_HEIGHT);
        faceUp.getStyleClass().add("card");

        //Set Inside of face
        VBox inside = new VBox();
        inside.getStyleClass().add("inside");
        StackPane.setMargin(inside, new Insets(CardSettings.OUTER_CARD_MARGIN));
        faceUp.getChildren().add(inside);

        //Create content of the inside of the face
        Label top = new Label();
        top.getStyleClass().add("top");
        top.setMinWidth(CardSettings.INNER_CARD_WIDTH);

        Label middle = new Label();
        middle.getStyleClass().add("middle");
        middle.setMinWidth(CardSettings.INNER_CARD_WIDTH);

        Label bottom = new Label();
        bottom.getStyleClass().add("bottom");
        bottom.setMinWidth(CardSettings.INNER_CARD_WIDTH);

        //Add inside content
        inside.getChildren().addAll(top, middle, bottom);

        //Update content to match card creating
        changeCardFrontView(card, faceUp);

        return faceUp;
    }

    /**
     * Updates an previously created StackPane to display a card.
     *
     * @author Lily Romano
     *
     * @param newCard The {@code Card} the pane should display
     * @param cardPane The StackPane for displaying
     * @return The StackPane for displaying
     */
    public static Node changeCardFrontView(Card newCard, StackPane cardPane) {
        String cardMainString = newCard.getType().getCardMainText();
        String cardCornerString = newCard.getType().getCardCornerText();

        //Get VBox in StackPane
        VBox inside = (VBox) cardPane.getChildren().get(0);

        //Get values of card to display
        String cardColor = newCard.getColorString();

        //Iterate through VBox and update all card types and text color of middle
        for (Node n : inside.getChildren()) {
            if (n instanceof Label) {
                ((Label) n).setText(cardCornerString);
            }
            if ("middle".equals(n.getStyleClass().get(1))) {
                n.setStyle("-fx-text-fill: " + cardColor);
                ((Label) n).setText(cardMainString);
            }
        }

        inside.setStyle("-fx-background-color: " + cardColor);

        return cardPane;
    }

}
