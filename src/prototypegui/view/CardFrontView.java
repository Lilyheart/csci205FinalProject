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
package prototypegui.view;

import cards.Card;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Lily Romano
 * @version 0.1
 */
public class CardFrontView {

    public static Node createCardFrontView(Card card) {
        String cardColor = card.getColorString();
        String cardString = card.getType().getCardText();

        StackPane faceUp = new StackPane();

        faceUp.setPrefSize(128, 178);
        faceUp.getStyleClass().add("card");

        //Set Inside of face
        VBox inside = new VBox();
        inside.getStyleClass().add("inside");
        faceUp.getChildren().add(inside);
        StackPane.setMargin(inside, new Insets(5));

        Label top = new Label(cardString);
        top.getStyleClass().add("top");
        top.setMinWidth(118);
        top.setPrefHeight(20);

        Label middle = new Label(cardString);
        middle.getStyleClass().add("middle");
        middle.setMinWidth(118);
        middle.setPrefHeight(138);

        Label bottom = new Label(cardString);
        bottom.getStyleClass().add("bottom");
        bottom.setMinWidth(118);
        bottom.setPrefHeight(20);

        inside.getChildren().addAll(top, middle, bottom);

        inside.setStyle("-fx-background-color: " + cardColor);
        middle.setStyle("-fx-text-fill: " + cardColor);

        return faceUp;
    }

}
