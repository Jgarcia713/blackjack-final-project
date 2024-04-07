package model;

/**
 * @author Jakob Garcia
 */
public class Player {

	private double balance;
	private BlackjackHand hand;
	private BlackjackHand splitHand; // Add for splitting?
	private String playerName;

	public Player(String name) {
		balance = 100.00;
		playerName = name;
		hand = new BlackjackHand();
	}

	public void receiveCards(Card dealtCard) {
		// Receive Cards from the Dealer
		hand.add(dealtCard);
	}

	public double placeBet(double amount) {
		// Adds ante to Dealer's pot
		balance -= amount;
		return amount;
	}

	public void receivePayout(double payout) {
		// Receive pay-out from Dealer's pot
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
