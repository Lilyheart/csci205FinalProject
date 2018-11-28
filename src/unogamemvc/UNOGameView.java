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

import deck.EmptyDeckException;
import deck.card.Card;
import java.util.concurrent.CopyOnWriteArrayList;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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
 * @author jameskelly
 */
public class UNOGameView {

    /**
     * The root node of the view which is a BorderPane
     */
    private final BorderPane root;

    /**
     * The model for the UNO game
     */
    private final UNOGameModel theModel;

    /**
     * The JavaFX object which represents the players hand.
     */
    private HBox playersHandHBox;

    /**
     * The Grid on which the Player's cards are displayed.
     */
    private GridPane cardsInPlayersHandPane;

    /**
     * The Grid which hold the two decks.
     */
    private GridPane drawAndDiscardDecksPane;

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
     * Container for opponents and decks
     */
    private VBox opponentsAndDeckVBox;

    /**
     * Container that holds the button
     */
    private VBox rightPanel;
    /**
     * The button to press when a user wants to shout UNO!
     */
    private Button UNOButton;

    /**
     * Menu bar that displays options to click
     */
    private MenuBar menuBar;

    /**
     * Menu option that displays UNO rules
     */
    private Menu getHelp;

    /**
     * Menu option that shows choice to exit the game
     */
    private Menu exit;

    /**
     * Menu Item that exits the game
     */
    private MenuItem exit2;

    /**
     * An explicit constructor for the UNO game view
     *
     * @author Lily Romano
     * @author jameskelly
     *
     * @param theModel The model for the UNO game
     * @throws deck.EmptyDeckException
     */
    public UNOGameView(UNOGameModel theModel) throws EmptyDeckException {
        //TODO [Exception Handling]
        this.theModel = theModel;
        setDiscardDeckPane();

        root = new BorderPane();
        root.setId("rootNode");

        createPlayersHandHBox();

        drawPlayerHandPane();

        playersHandHBox.getChildren().add(cardsInPlayersHandPane);

        createOpponentsAndDeckVBox();
        createOpponentsPane();

        createDrawAndDiscardDecksGridPane();

        //create a card to represent the draw deck
        //TODO [Card Display] Draw deck should show top card of draw pile.
        drawDeckPane = CardBackView.createCardBackView();

        //create a card to represent the discard deck
        drawAndDiscardDecksPane.add(drawDeckPane, 0, 0);
        drawAndDiscardDecksPane.add(discardDeckPane, 1, 0);
        drawAndDiscardDecksPane.setAlignment(Pos.CENTER);

        opponentsAndDeckVBox.getChildren().add(opponentsPane);
        opponentsAndDeckVBox.getChildren().add(drawAndDiscardDecksPane);
        opponentsAndDeckVBox.setAlignment(Pos.CENTER);

        createRightPanel();
        createUNOButton();

        rightPanel.getChildren().add(UNOButton);

        menuBar = new MenuBar();
        getHelp = new Menu("Help");
        exit = new Menu("Exit");
        exit2 = new MenuItem("Goodbye!");
        exit2.setOnAction(e -> Platform.exit());
        exit.getItems().add(this.exit2);

        menuBar.getMenus().addAll(getHelp, exit);

        root.setTop(menuBar);
        root.setCenter(opponentsAndDeckVBox);
        root.setRight(rightPanel);
        root.setBottom(playersHandHBox);

    }

    public BorderPane getRootNode() {
        return root;
    }

    public GridPane getCardsInPlayersHandPane() {
        return cardsInPlayersHandPane;
    }

    public GridPane getDrawAndDiscardDecksPane() {
        return drawAndDiscardDecksPane;
    }

    public StackPane getDrawDeckPane() {
        return drawDeckPane;
    }

    public StackPane getDiscardDeckPane() {
        return discardDeckPane;
    }

    /**
     * Sets the discard deck card as the last card from the draw deck
     *
     * @throws EmptyDeckException
     * @author jameskelly
     */
    public void setDiscardDeckPane() throws EmptyDeckException {
        this.discardDeckPane = CardFrontView.createCardFrontView(
                theModel.getUnoGame().getTheDiscardDeck().peekBottomCard());
    }

    /**
     * Creates a GUI object of the next card to add to add to the GirdPane
     *
     * @return
     * @throws EmptyDeckException
     * @author jameskelly
     */
    public StackPane createNextFaceUpCard() throws EmptyDeckException {
        //TODO [Card Display] Get cards from hand instead
        System.out.println(theModel.peekNextDrawCard()); //test to see if correct cards come out
        //test to see if correct cards come out
        StackPane faceUpCard = CardFrontView.createCardFrontView(
                theModel.peekNextDrawCard());
        return faceUpCard;
    }

    /**
     * Creates and adds the card to the GUI
     *
     * @author jameskelly
     */
    protected void drawPlayerHandPane() {
        /*TODO [Card Display] The way these cards are being created, they cannot be
        referenced by the controller.  A suggestion would be to create a class
        instance variable that stores some type of datastructure that makes sense
        cards and work from there.
         */
        //make list of cards, add each card to pane, just pass future players hand of cards
        //  instead of hardcoding
        CopyOnWriteArrayList<Card> playersHand = theModel.getUnoGame().getPlayersHandCopy(
                theModel.getHUMAN_PLAYER());
        int nextCol = 0;
        int secondRow = 0;

        for (Card card : playersHand) {
            StackPane faceUpCard = CardFrontView.createCardFrontView(
                    card);
            faceUpCard.setId(String.valueOf(nextCol));
            /*
            if (nextCol >= 9) {//Make final global
                cardsInPlayersHandPane.add(faceUpCard, secondRow, 1);
                secondRow++;
            }
            else {
                cardsInPlayersHandPane.add(faceUpCard, nextCol, 0);
                nextCol++;

            }*/
            cardsInPlayersHandPane.add(faceUpCard, nextCol, 0);
            nextCol++;
        }
    }

    /**
     * Creates and adds the opponents facedown cards to the GUI
     *
     * @param numCards
     * @author jameskelly
     */
    protected void drawComputerHandPane(int numCards) {
        opponentStack = new StackPane();

        StackPane bottomCard = CardBackView.createCardBackView();
        opponentStack.getChildren().add(bottomCard);

        //Make next card
        for (int j = 1; j < 7; j++) {
            StackPane nextCard = CardBackView.createCardBackView();
            nextCard.setTranslateX(5 * j);
            opponentStack.getChildren().add(nextCard);
        }

    }

    /**
     * Creates the UNO button
     *
     * @author jameskelly
     */
    private void createUNOButton() {
        UNOButton = new Button();
        UNOButton.setText("BUno!");
        UNOButton.setPrefWidth(100);
    }

    /**
     * Creates the container on the right side of the border pane
     *
     * @author jameskelly
     */
    private void createRightPanel() {
        //create the right box that hold the button
        rightPanel = new VBox();
        rightPanel.setPadding(new Insets(10, 50, 10, 10));
        rightPanel.setAlignment(Pos.BOTTOM_RIGHT);
        rightPanel.setSpacing(50);
    }

    /**
     * Creates the GridPane that hold the opponents hands
     *
     * @author jameskelly
     */
    private void createOpponentsPane() {
        //create the model of opponentsPane
        opponentsPane = new GridPane();
        for (int i = 0; i < theModel.getUnoGame().getNumComputerPlayers(); i++) {
            drawComputerHandPane(
                    theModel.getUnoGame().getPlayersHandCopy(i + 2).size());
            /* TODO [Card Display] The hands stack in a weird way*/
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
     * @author jameskelly
     */
    private void createOpponentsAndDeckVBox() {
        opponentsAndDeckVBox = new VBox();
        opponentsAndDeckVBox.setSpacing(100);
        opponentsAndDeckVBox.setPadding(new Insets(10, 10, 10, 10));
    }

    /**
     * Creates the container that hold the Players hand
     *
     * @author jameskelly
     */
    private void createPlayersHandHBox() {
        playersHandHBox = new HBox();
        playersHandHBox.setSpacing(100);
        playersHandHBox.setPadding(new Insets(10, 10, 10, 10));

        cardsInPlayersHandPane = new GridPane();
        cardsInPlayersHandPane.setHgap(20);
        cardsInPlayersHandPane.setVgap(20);
        cardsInPlayersHandPane.setPadding(new Insets(40));
    }

    /**
     * Creates the grid that holds the discard and draw decks
     *
     * @author jameskelly
     */
    private void createDrawAndDiscardDecksGridPane() {
        drawAndDiscardDecksPane = new GridPane();
        drawAndDiscardDecksPane.setHgap(20);
        drawAndDiscardDecksPane.setVgap(20);
        drawAndDiscardDecksPane.setPadding(new Insets(40));
    }

}
