package model;

/**
 * Represents a single player in a game of Blackjack
 * 
 * @author Jakob Garcia
 */
public class Player {
	private double balance;
	private BlackjackHand hand; // main hand of Blackjack
	// private BlackjackHand splitHand; // second hand for splitting
	private String playerName;
	private double bet = 0; // main bet
	// private double splitBet = 0; // second bet for splitting
	private double nextBet = 0;
	private boolean folded = false; // indicates if the player has folded
	// private boolean split = false;
	private boolean hasCompletedTurn = false;
	private boolean isPlayer; // indicates whether or not the player is a computer

	/**
	 * constructs the Player class (invariable balance)
	 * @param name: player's name
	 * @param isPlayer: whether or not player is controlled by a human
	 */
	public Player(String name, boolean isPlayer) {
		balance = 100.00;
		playerName = name;
		hand = new BlackjackHand();
		this.isPlayer = isPlayer;
	}

	/**
	 * constructs the Player class (variable balance)
	 * @param name: player's name
	 * @param bal: player's starting balance
	 * @param isPlayer: whether or not player is controlled by a human
	 */
	public Player(String name, double bal, boolean isPlayer) {
		balance = bal;
		playerName = name;
		hand = new BlackjackHand();
		this.isPlayer = isPlayer;

	}

	/*
	 * Receive cards from the dealer, and add them to their BlackjackHand
	 */
	public void receiveCards(Card dealtCard) {
		hand.dealCard(dealtCard);
	}

	/*
	 * Place the bets for this round of blackjack
	 */
	public void placeBet(double amount) {
		balance -= amount;
		bet += amount;
	}

	/*
	 * Receive payout for this bet
	 */
	public void receivePayout(boolean draw) {
		// Receive pay-out

//		if (split) {
//			if (draw)
//				balance += splitBet;
//			else if (splitHand.isBlackJack())
//				balance += splitBet * 2.5;
//			else
//				balance += splitBet * 2;
//		} else {
		if (draw)
			balance += bet;
		else if (hand.isBlackJack())
			balance += bet * 2.5;
		else
			balance += bet * 2;

		bet = 0; // reset bet to 0
//		}
	}

	/*
	 * Allows the player to hit and receive a card. Requires the Game to pass the
	 * card from the dealer
	 */
	public boolean hit(Card card) {
//		if (split) {
//			splitHand.dealCard(card);
//			if (splitHand.isBusted())
//				folded = true;
//			return splitHand.isBusted();
//		} else {
		hand.dealCard(card);
		if (hand.isBusted())
			folded = true;
		return hand.isBusted();
//		}
	}

	/*
	 * Allows the player to double down and receive a card. Requires the Game to
	 * pass the card from the dealer. Returns true if busted
	 */
	public boolean doubleDown(Card card) {
//		if (split) {
//			balance -= bet;
//			splitBet *= 2;
//			splitHand.dealCard(card);
//			folded = true;
//			return splitHand.isBusted();
//		} else {
		balance -= bet;
		bet *= 2;
		hand.dealCard(card);
		folded = true;
		return hand.isBusted();
//		}
	}

	/*
	 * Allows the player to split once and play two hands. Requires the game to
	 * handle swapping between both hands
	 */
//	public void split() {
//		splitHand = new BlackjackHand();
//		splitHand.dealCard(hand.split());
//		split = true;
//		balance -= bet;
//		splitBet = bet;
//	}

	/*
	 * Resets values needed for next game
	 */
	public void discardCards() {
		folded = false;
//		split = false;
		hand = new BlackjackHand();
	}

	/**
	 * gets player's balance
	 * @return player's current balance
	 */
	public double checkBalance() {
		return balance;
	}

	/**
	 * gets player's name
	 * @return player's current name
	 */
	public String getName() {
		return playerName;
	}

	/**
	 * gets player's blackjack status
	 * @return whether player has blackjack or not
	 */
	public boolean hasBlackjack() {
		return hand.isBlackJack();
	}

	/**
	 * gets player's busted status
	 * @return whether player's hand total >21
	 */
	public boolean isBusted() {
		return hand.isBusted();
	}

	/**
	 * gets player's control status
	 * @return whether player is being control by a human
	 */
	public boolean isPlayer() {
		return isPlayer;
	}

	/**
	 * gets player's bet
	 * @return player's current bet
	 */
	public double getBet() {
		return bet;
	}

	/**
	 * gets player's hand total
	 * @return player's current hand total
	 */
	public int getHandTotal() {
//		if (split)
//			return splitHand.getTotal();
		return hand.getTotal();
	}

	/**
	 * player folds, setting folded to true
	 */
	public void fold() {
		folded = true;
	}

	/**
	 * gets player's folded status
	 * @return whether player is folded or not
	 */
	public boolean isFolded() {
		return folded;
	}

//	public boolean isSplit() {
//		return split;
//	}
	
	/**
	 * gets player's hand
	 * @return player's current hand
	 */
	public BlackjackHand getHand() {
		return hand;
	}

	/**
	 * returns the player information represented as a string
	 * @return a string containing the player's name, balance, hand, and hand total
	 */
	@Override
	public String toString() {
		return playerName + "\nBalance: " + balance + "\n" + hand + "\nTotal Score: " + hand.getTotal();
	}

}
