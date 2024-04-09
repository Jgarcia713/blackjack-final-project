package controller_view;

import controller.BlackjackGame;
import controller.Game;
import model.Player;
import java.util.ArrayList;
import java.util.Scanner;
import model.Actions;
public class BlackjackConsole {

    private static BlackjackGame game;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        game = new BlackjackGame();
        game.addPlayer("p1", true);
        game.addPlayer("c1", false);
        boolean isGameOver = game.startRound();

        while (!game.isGameOver) {
            System.out.println(game.getActivePlayer() + "\nWhat would you like to do? ('hit'/'stand'/'double')");
            String userResponse = scanner.nextLine();
            try {
                Actions action = Actions.valueOf(userResponse.toUpperCase());
                game.makeMove(action);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid action!");
            }
        }
        displayResults();
    }

    private static void displayResults() {

    }
}
