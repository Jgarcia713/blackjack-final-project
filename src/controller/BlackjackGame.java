package controller;

import model.Dealer;
import model.Player;

import java.util.ArrayList;

public class BlackjackGame {
    ArrayList<Player> players;
    Dealer dealer;
    public BlackjackGame() {
        players = new ArrayList<Player>();
        dealer = new Dealer(players);
    }

    public void addPlayer(String name, boolean isPlayer) {
        players.add(new Player(name, isPlayer));
    }
}
