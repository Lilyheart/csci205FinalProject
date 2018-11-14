/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: James Kelly, Scott Little, Rachel Wang, Lily Romano
* Date: Nov 9, 2018
* Time: 9:47:31 AM
*
* Project: csci205FinalProject
* Package: gui
* File: NewFXMain
* Description:
*
* ****************************************
 */
package prototypegui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * A Prototype of Cards
 *
 * @author Lily Romano
 */
public class PrototypeMain extends Application {

    /**
     * The model for the Card Prototype
     */
    private PrototypeGuiModel theModel;

    /**
     * The view for the Card Prototype
     */
    private PrototypeGuiView theView;

    /**
     * The controller for the Card Prototype
     */
    private PrototypeGuiController theControl;

    /**
     * The primary method that is called by the JavaFX application to initialize
     * the GUI
     *
     * @throws Exception
     */
    @Override
    public void init() throws Exception {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        this.theModel = new PrototypeGuiModel();
        this.theView = new PrototypeGuiView(theModel);
    }

    /**
     * The primary method that is called by the JavaFX application to set up the
     * initial stage
     *
     * @param primaryStage The initial stage
     */
    @Override
    public void start(Stage primaryStage) {
        this.theControl = new PrototypeGuiController(theModel, theView);

        Scene scene = new Scene(this.theView.getRootNode());
        scene.getStylesheets().add(
                getClass().getResource("resource/stylesheet.css").toExternalForm());

        primaryStage.setTitle("Card Prototype");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();

        //Required to listen for key presses when only shapes are in the view
        this.theView.getRootNode().requestFocus();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
