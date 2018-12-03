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

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import unogame.Game;

/**
 *
 * @author jameskelly
 */
public class RoundOverPopup {

    private Scene scene1;
    private BorderPane layout;
    private Stage popUpWindow;
    private Button exit;

    Game unoGame;

    public RoundOverPopup(Game unoGame) {
        this.unoGame = unoGame;

        VBox editingVBox = new VBox();
        //creating borderpane layout
        layout = new BorderPane();
        layout.setPadding(new Insets(0, 15, 15, 0));
        layout.setCenter(editingVBox);
        layout.setPadding(new Insets(20, 20, 20, 20));

        scene1 = new Scene(layout, 350, 200);

        Label label = new Label(
                "Round Over!");
        label.setStyle("-fx-font: 24 arial;");
        label.setAlignment(Pos.CENTER);
        label.setPadding(new Insets(0, 0, 20, 0));

        exit = new Button("Exit");
        exit.setPrefSize(100, 50);

        editingVBox.getChildren().addAll(label, exit);
        editingVBox.setAlignment(Pos.CENTER);
        this.exit.setOnAction((event) -> {
//            unoGame.startRound();
            popUpWindow.close();
        });

    }

    /**
     * Displays the popup.
     */
    public void display() {

        popUpWindow = new Stage();
        popUpWindow.setTitle("Round Over!");
        popUpWindow.setWidth(700);
        popUpWindow.setScene(scene1);
        popUpWindow.show();
    }

    public Button getExit() {
        return exit;
    }

}
