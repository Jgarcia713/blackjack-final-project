package controller_view;

import controller.BlackjackGame;
import model.Player;

public class BlackjackConsole {
    public static void main(String[] args) {
        BlackjackGame game = new BlackjackGame();
        game.addPlayer(new Player("p1", true));
        game.addPlayer(new Player("c1", false));
    }
}
