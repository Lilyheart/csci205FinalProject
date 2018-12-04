/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: James Kelly, Scott Little, Rachel Wang, Lily Romano
* Date: Nov 9, 2018
* Time: 12:38:55 PM
*
* Project: csci205FinalProject
* Package: view
* File: UNOGameView
* Description:
*
* ****************************************
 */
package unogamemvc;

import deck.card.Card;
import java.util.concurrent.CopyOnWriteArrayList;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import unogamemvc.cardcreator.CardBackView;
import unogamemvc.cardcreator.CardFrontView;

/**
 * A GUI Card Prototype MVC view Main GUI
 *
 * @author Lily Romano
 * @author James Kelly
 */
public class UNOGameView {

    /**
     * The model for the UNO game
     */
    private final UNOGameModel theModel;

    /**
     * The root node of the view which is a BorderPane
     */
    private final BorderPane root;

    /**
     * The JavaFX object which represents the players hand.
     */
    private HBox playersHandHBox;

    /**
     * The Grid on which the Player's cards are displayed.
     */
    private StackPane cardsInPlayersHandPane;

    /**
     * The deck to draw from.
     */
    private StackPane drawDeckPane;

    /**
     * The deck that you put the next card on top of.
     */
    private StackPane discardDeckPane;

    /**
     * The GUI pane that hold the opponents cards
     */
    private GridPane opponentsPane;

    /**
     * The stack of opponents cards
     */
    private StackPane opponentStack;

    /**
     * Container that holds the button
     */
    private VBox leftPanel;

    /**
     * An explicit constructor for the UNO game view
     *
     * @author Lily Romano
     * @author James Kelly
     *
     * @param theModel The model for the UNO game
     */
    public UNOGameView(UNOGameModel theModel) {
        this.theModel = theModel;
        setDiscardDeckPane();

        root = new BorderPane();
        root.setId("rootNode");

        drawPlayersHandHBox();

        drawPlayerHandPane();

        playersHandHBox.getChildren().add(cardsInPlayersHandPane);

        VBox opponentsAndDeckVBox = createOpponentsAndDeckVBox();

        opponentsPane = new GridPane();

        drawOpponentsPane();

        GridPane drawAndDiscardDecksPane = createDrawAndDiscardDecksGridPane();

        //create a card to represent the draw deck
        drawDeckPane = CardBackView.createCardBackView();

        //create a card to represent the discard deck
        drawAndDiscardDecksPane.add(drawDeckPane, 0, 0);
        drawAndDiscardDecksPane.add(discardDeckPane, 1, 0);
        drawAndDiscardDecksPane.setAlignment(Pos.CENTER);

        opponentsAndDeckVBox.getChildren().add(opponentsPane);
        opponentsAndDeckVBox.getChildren().add(drawAndDiscardDecksPane);
        opponentsAndDeckVBox.setAlignment(Pos.CENTER);

        VBox rightPanel = createRightPanel();
        //TODO [!BUno]
//        Button btnUNOButton = createUNOButton();

//        rightPanel.getChildren().add(btnUNOButton);
        MenuBar menuBar = new MenuBar();
        Menu getHelp = new Menu("Help");
        Menu exit = new Menu("Exit");
        MenuItem exit2 = new MenuItem("Goodbye!");
        exit2.setOnAction(e -> Platform.exit());
        exit.getItems().add(exit2);

        menuBar.getMenus().addAll(getHelp, exit);

        root.setTop(menuBar);
        root.setCenter(opponentsAndDeckVBox);
        root.setRight(rightPanel);
        updateScoreboard();
        //----------------------------------------------------------------------
        root.setBottom(playersHandHBox);

    }

    protected BorderPane getRootNode() {
        return root;
    }

    protected StackPane getCardsInPlayersHandPane() {
        return cardsInPlayersHandPane;
    }

    protected StackPane getDrawDeckPane() {
        return drawDeckPane;
    }

    protected StackPane getDiscardDeckPane() {
        return discardDeckPane;
    }

    /**
     * Updates the scoreboard pane
     */
    private void updateScoreboard() {
        leftPanel = new VBox();
        leftPanel.setPadding(new Insets(10, 10, 10, 10));
        leftPanel.setSpacing(20);
        for (int i = 1; i <= theModel.getUnoGame().getNumPlayers(); i++) {
            Label name;
            Label score;

            name = new Label("Player " + (i));
            score = new Label(String.valueOf(
                    theModel.getUnoGame().getScore(i - 1)));

            name.getStyleClass().add("scorePanelName");
            score.getStyleClass().add("scorePanelScore");

            leftPanel.getChildren().addAll(name, score);
            leftPanel.setAlignment(Pos.CENTER);
        }
        root.setLeft(leftPanel);
    }

    /**
     * Sets the discard deck card as the last card from the draw deck
     *
     * @author James Kelly
     */
    private void setDiscardDeckPane() {
        this.discardDeckPane = CardFrontView.createCardFrontView(
                theModel.getUnoGame().getDiscardCardCard());
    }

    /**
     * Creates and adds the card to the GUI
     *
     * @author James Kelly
     */
    protected void drawPlayerHandPane() {
        CopyOnWriteArrayList<Card> playersHand = theModel.getUnoGame().getPlayersHandCopy(
                theModel.getMAIN_PLAYER_ID());

        cardsInPlayersHandPane.getChildren().clear();
        int nextCol = 0;
        int j = 1;
        for (Card card : playersHand) {
            StackPane faceUpCard = CardFrontView.createCardFrontView(
                    card);

            faceUpCard.setId(String.valueOf(nextCol));
            faceUpCard.setTranslateX(30 * j);

            cardsInPlayersHandPane.getChildren().add(faceUpCard);
            nextCol++;
            j++;
        }
    }

    /**
     * Creates and adds the opponents facedown cards to the GUI
     *
     * @author James Kelly
     *
     * @param numCards the number of cards to draw in the other player's hand
     */
    private void drawOpponentHandPane(int numCards) {
        opponentStack = new StackPane();

        StackPane bottomCard = CardBackView.createCardBackView();
        opponentStack.getChildren().add(bottomCard);

        //Make next card
        for (int j = 1; j < numCards; j++) {
            StackPane nextCard = CardBackView.createCardBackView();
            nextCard.setTranslateX(5 * j);
            opponentStack.getChildren().add(nextCard);
        }

    }

    /**
     * Updates the discardDeckPane
     *
     * @author James Kelly
     */
    private void updateDiscardDeck() {
        discardDeckPane.getChildren().clear();

        discardDeckPane.getChildren().add(
                CardFrontView.createCardFrontView(
                        theModel.getUnoGame().getDiscardCardCard()));
    }

    /**
     * Creates the UNO button
     *
     * @author James Kelly
     *
     * @return the UNO Button
     */
    private Button createUNOButton() {
        Button btnUNOButton = new Button();

        btnUNOButton.setText("BUno!");
        btnUNOButton.setPrefWidth(100);

        return btnUNOButton;
    }

    /**
     * Creates the container on the right side of the border pane
     *
     * @author James Kelly
     *
     * @return the rightPanel VBox
     */
    private VBox createRightPanel() {
        VBox rightPanel = new VBox();

        rightPanel.setPadding(new Insets(10, 50, 10, 10));
        rightPanel.setAlignment(Pos.BOTTOM_RIGHT);
        rightPanel.setSpacing(50);

        return rightPanel;
    }

    /**
     * Creates and updates the GridPane that hold the opponents hands
     *
     * @author James Kelly
     */
    protected void drawOpponentsPane() {
        opponentsPane.getChildren().clear();

        for (int i = 0; i < theModel.getUnoGame().getNumComputerPlayers(); i++) {
            drawOpponentHandPane(
                    theModel.getUnoGame().getPlayersHandCopy(i + 2).size());
            opponentsPane.setHgap(100);
            opponentsPane.setVgap(20);
            opponentsPane.setPadding(new Insets(20, 0, 0, 0));
            opponentsPane.setAlignment(Pos.TOP_CENTER);
            opponentsPane.add(opponentStack, i, 0);
        }
    }

    /**
     * Creates the container that holds the opponents hands and decks
     *
     * @author James Kelly
     *
     * @return the opponentsAndDeckVBox VBox
     */
    private VBox createOpponentsAndDeckVBox() {
        VBox opponentsAndDeckVBox = new VBox();

        opponentsAndDeckVBox.setSpacing(100);
        opponentsAndDeckVBox.setPadding(new Insets(10, 10, 10, 10));

        return opponentsAndDeckVBox;
    }

    /**
     * Creates and updates the containers that hold the Players hand
     *
     * @author James Kelly
     */
    private void drawPlayersHandHBox() {
        playersHandHBox = new HBox();
        playersHandHBox.setSpacing(100);
        playersHandHBox.setPadding(new Insets(10, 10, 10, 10));

        cardsInPlayersHandPane = new StackPane();
        cardsInPlayersHandPane.setPadding(new Insets(40));
    }

    /**
     * Creates the grid that holds the discard and draw decks
     *
     * @author James Kelly
     *
     * @return the drawAndDiscardDecksPane GridPane
     */
    private GridPane createDrawAndDiscardDecksGridPane() {
        GridPane drawAndDiscardDecksPane = new GridPane();

        drawAndDiscardDecksPane.setHgap(20);
        drawAndDiscardDecksPane.setVgap(20);
        drawAndDiscardDecksPane.setPadding(new Insets(40));

        return drawAndDiscardDecksPane;
    }

    /**
     * Draws the panes that change
     *
     * @author James Kelly
     */
    protected void drawPanes() {
        drawPlayerHandPane();
        drawOpponentsPane();
        updateDiscardDeck();
        updateScoreboard();
    }

}
