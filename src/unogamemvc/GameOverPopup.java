/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: James Kelly, Scott Little, Rachel Wang, Lily Romano
* Date: Nov 26, 2018
* Time: 10:48:51 PM
*
* Project: csci205FinalProject
* Package: unogamemvc
* File: InvalidPlayPopup
* Description:
*
* ****************************************
 */
package unogamemvc;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The pop-up that displays when the game is over.
 *
 * @author James Kelly
 */
public final class GameOverPopup {

    /**
     * Creates and Displays the pop-up.
     */
    public static void display() {

        VBox root = new VBox();
        Scene primaryScene = new Scene(root, 350, 200);
        root.setPadding(new Insets(20, 20, 20, 20));

        //creating borderpane root
        Label label = new Label(
                "Game Over!");
        label.setStyle("-fx-font: 24 arial;");
        label.setAlignment(Pos.CENTER);
        label.setPadding(new Insets(0, 0, 20, 0));

        Button exit = new Button("Exit");
        exit.setPrefSize(100, 50);

        root.getChildren().addAll(label, exit);
        root.setAlignment(Pos.CENTER);
        Stage popUpWindow = new Stage();

        popUpWindow.setTitle("Game Over!");
        popUpWindow.setWidth(700);
        popUpWindow.setScene(primaryScene);
        popUpWindow.show();

        exit.setOnAction(e -> Platform.exit());
    }

}
