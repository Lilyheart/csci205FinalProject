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
package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * A Prototype of Cards
 *
 * @version 0.1
 * @author Lily Romano
 */
public class CardPrototypeMain extends Application {

    /**
     * The model for the Card Prototype
     */
    private CardPrototypeModel theModel;

    /**
     * The view for the Card Prototype
     */
    private CardPrototypeView theView;

    /**
     * The controller for the Card Prototype
     */
    private CardPrototypeController theControl;

    /**
     * The primary method that is called by the JavaFX application to initialize
     * the GUI
     *
     * @throws Exception
     */
    @Override
    public void init() throws Exception {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        this.theModel = new CardPrototypeModel();
        this.theView = new CardPrototypeView(theModel);
    }

    /**
     * The primary method that is called by the JavaFX application to set up the
     * initial stage
     *
     * @param primaryStage The initial stage
     */
    @Override
    public void start(Stage primaryStage) {
        this.theControl = new CardPrototypeController(theModel, theView);

        Scene scene = new Scene(this.theView.getRootNode());

        primaryStage.setTitle("Card Prototype");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
