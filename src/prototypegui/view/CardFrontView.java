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

        inside.setStyle("-fx-background-color: " + cardColor);
        middle.setStyle("-fx-text-fill: " + cardColor);

        inside.getChildren().addAll(top, middle, bottom);

        return faceUp;
    }

    public static Node changeCardFrontView(Card newCard, StackPane cardPane) {

        String cardColor = newCard.getColorString();
        String cardString = newCard.getType().getCardText();

        VBox inside = (VBox) cardPane.getChildren().get(0);
        Label top = (Label) inside.getChildren().get(0);
        Label middle = (Label) inside.getChildren().get(1);
        Label bottom = (Label) inside.getChildren().get(2);

        System.out.println();

        inside.setStyle("-fx-background-color: " + cardColor);
        middle.setStyle("-fx-text-fill: " + cardColor);

        top.setText(cardString);
        middle.setText(cardString);
        bottom.setText(cardString);

        return cardPane;
    }

}
