package model;

/**
 * Represents a single player in a game of Blackjack
 * 
 * @author Jakob Garcia
 */
public class Player extends BlackjackPlayer {

	public Player(String name) {
		super(name);
		isPlayer = true;
	}

	public Player(String name, double balance) {
		super(name, balance);
		isPlayer = true;
	}

	/*
	 * Place the bets to the dealer for this round of blackjack
	 */
	public void placeBet(double amount, boolean split) {
		balance -= amount;
		bet = amount;
	}
	
	// TODO add methods for hitting, double downs, and splitting?
}
