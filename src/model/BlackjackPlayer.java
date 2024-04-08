package model;

public abstract class BlackjackPlayer {
	protected double balance;
	protected BlackjackHand hand;
	protected BlackjackHand splitHand; // create logic
	protected String playerName;
	protected double bet;
	protected double splitBet;
	protected boolean folded; // Implement logic
	protected boolean isPlayer;

	public BlackjackPlayer(String name) {
		balance = 100.00;
		playerName = name;
		hand = new BlackjackHand();
	}

	public BlackjackPlayer(String name, double bal) {
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

	public void createSplit() {
		splitHand = new BlackjackHand();
	}

	/*
	 * Receive cards from the dealer, and add them to their BlackjackHand
	 */
	public void receiveSplitCards(Card dealtCard) {
		splitHand.dealCard(dealtCard);
	}

	/*
	 * Receive payout from the Dealer
	 */
	public void receivePayout(boolean isBlackjack) {
		// Receive pay-out from Dealer
		if (isBlackjack)
			balance += bet * 1.5;
		else
			balance += bet;
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

	public boolean hasBlackjack() {
		return hand.isBlackJack();
	}

	public boolean isBusted() {
		return hand.isBusted();
	}

	public abstract void placeBet(double amount, boolean split);

	public boolean isPlayer() {
		return isPlayer;
	}

	public double getBet() {
		return bet;
	}

}
