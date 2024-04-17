package view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.Actions;
import presenter.BlackjackGame;

public class BlackjackButtonControls extends GridPane {

    private BlackjackGame theGame;
    private Button hit, stand, doubleDown, split;

    private TextField betInput;
    private Button placeBet;
    public BlackjackButtonControls(BlackjackGame theModel) {
        theGame = theModel;
        initializePanel();
        declareButtonEvents();
    }

    private void initializePanel() {
        hit = new Button("Hit");
        stand = new Button("Stand");
        doubleDown = new Button("Double Down");
        split = new Button("Split");

        betInput = new TextField();
        placeBet = new Button("Place bet and Start Round");
        this.add(hit, 0, 0);
        this.add(stand, 1, 0);
        this.add(doubleDown, 2, 0);
        this.add(split, 3, 0);
        this.add(betInput, 10, 0);
        this.add(placeBet, 11, 0);
        this.setPadding(new Insets(10));

        this.setHgap(10);
    }

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

        });
    }

}
