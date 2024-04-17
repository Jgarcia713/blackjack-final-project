package view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.Actions;
import presenter.BlackjackGame;
import javafx.scene.control.Label;

/**
 * custom gridpane that houses all the controls for interacting with blackjack game
 */
public class BlackjackControlBar extends GridPane {

    private BlackjackGame theGame;
    public Label activePlayerLabel;
    public Button hit, stand, doubleDown, split;

    private TextField betInput;
    private Button placeBet;

    /**
     * constructor. Calls methods necessary for initializing the custom gridpane.
     * @param theModel reference to the BlackjackGame object that this pane should control.
     */
    public BlackjackControlBar(BlackjackGame theModel) {
        theGame = theModel;
        initializePanel();
        declareButtonEvents();
    }

    /**
     * Sets up all the ui elements for this gridpane panel.
     */
    private void initializePanel() {
        activePlayerLabel = new Label(theGame.getActivePlayer() + "'s turn");

        hit = new Button("Hit");
        stand = new Button("Stand");
        doubleDown = new Button("Double Down");
        split = new Button("Split");

        betInput = new TextField();
        placeBet = new Button("Place bet and Start Round");

        this.add(activePlayerLabel, 0, 0);
        this.add(hit, 1, 0);
        this.add(stand, 2, 0);
        // hiding these two buttons for now, until the functionality is properly implemented
//        this.add(doubleDown, 3, 0);
//        this.add(split, 4, 0);
        this.add(betInput, 10, 0);
        this.add(placeBet, 11, 0);
        this.setPadding(new Insets(10));

        this.setHgap(10);
    }

    /**
     * used when initializing panel. Declares the action events for the UI elements
     */
    private void declareButtonEvents() {
        if (hit == null)
            return;
        hit.setOnAction(event -> {
            theGame.makeMove(Actions.HIT);
        });

        if (stand == null)
            return;
        stand.setOnAction(event -> {
            theGame.makeMove(Actions.STAND);
        });

        if (doubleDown == null)
            return;
        doubleDown.setOnAction(event -> {
            theGame.makeMove(Actions.DOUBLE);
        });

        if (split == null)
            return;
        split.setOnAction(event -> {
            theGame.makeMove(Actions.SPLIT);
        });

        if (placeBet == null)
            return;
        placeBet.setOnAction(event -> {
            int betAmount = Integer.parseInt(betInput.getText());
            theGame.setActivePlayerBet(betAmount);
            theGame.startRound();
        });
    }

    /**
     * setter method for the text of the activePlayerLabel Label
     * @param playerName String representing what name to insert in the label.
     */
    public void updateActivePlayerLabel(String playerName) {
        activePlayerLabel.setText(playerName + "'s turn");
    }
}
