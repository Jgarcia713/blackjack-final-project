package model;

/**
 * Represents a single player in a game of Blackjack
 * 
 * @author Jakob Garcia
 */
public class Player {

	private double balance;
	private BlackjackHand hand;
	private BlackjackHand splitHand; // Add for splitting?
	private String playerName;

	/*
	 * Construct a player with a specified name and a starting balance of 100
	 */
	public Player(String name) {
		balance = 100.00;
		playerName = name;
		hand = new BlackjackHand();
	}

	/*
	 * Construct a player with a specified name and balance
	 */
	public Player(String name, double bal) {
		balance = bal;
		playerName = name;
		hand = new BlackjackHand();
	}

	/*
	 * Receive cards from the dealer, and add them to their BlackjackHand
	 */
	public void receiveCards(Card dealtCard) {
		hand.dealCard(dealtCard);
	}

	/*
	 * Receive cards from the dealer, and add them to their BlackjackHand
	 */
	public void receiveSplitCards(Card dealtCard) {
		splitHand.dealCard(dealtCard);
	}

	/*
	 * Place the bets to the dealer for this round of blackjack
	 */
	public double placeBet(double amount) {
		balance -= amount;
		return amount;
	}

	/*
	 * Receive payout from the Dealer
	 */
	public void receivePayout(double payout) {
		// Receive pay-out from Dealer
		balance += payout;
	}

	public double checkBalance() {
		return balance;
	}

	public void discardCards() {
		hand = new BlackjackHand();
	}

	public String getName() {
		return playerName;
	}
}
