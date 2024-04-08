package model;

public class ComputerPlayer extends BlackjackPlayer {

	public ComputerPlayer(String name) {
		super(name);
		isPlayer = false;
	}

	public ComputerPlayer(String name, double balance) {
		super(name, balance);
		isPlayer = false;
	}

	/*
	 * Place the bets to the dealer for this round of blackjack
	 */
	public void placeBet(double amount, boolean split) {
		// TODO algorithm for different bets based on initial cards?
		balance -= amount;
		bet = amount;
	}
}
