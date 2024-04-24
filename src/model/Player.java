package model;

import java.util.ArrayList;

/**
 * Represents a single player in a game of Blackjack
 * 
 * @author Jakob Garcia
 */
public class Player {
	private double balance;
	private ArrayList<BlackjackHand> hand; // main hand of Blackjack
	private BlackjackHand currentHand;
	private int currentHandIndex;
	// private BlackjackHand splitHand; // second hand for splitting
	private String playerName;
	private double bet = 0; // main bet
	// private double splitBet = 0; // second bet for splitting
	private double nextBet = 0;
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
		hand = new ArrayList<>();
		BlackjackHand firstHand = new BlackjackHand();
		hand.add(firstHand);
		currentHand = hand.get(0);
		currentHandIndex = 0;
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
		hand = new ArrayList<>();
		BlackjackHand firstHand = new BlackjackHand();
		hand.add(firstHand);
		currentHand = hand.get(0);
		currentHandIndex = 0;
		this.isPlayer = isPlayer;

	}

	/*
	 * Receive cards from the dealer, and add them to their BlackjackHand
	 */
	public void receiveCards(Card dealtCard) {
		currentHand.dealCard(dealtCard);
	}

	/*
	 * Place the bets for this round of blackjack
	 */
	public void placeBet(double amount) {
		balance -= amount;
<<<<<<< HEAD
<<<<<<< HEAD
		bet += amount;
		currentHand.setBet(amount);
=======
		bet = amount;
>>>>>>> df7e8924c5a9b6adb3c5d097688c44926d5ddb98
=======
		bet = amount;
>>>>>>> df7e8924c5a9b6adb3c5d097688c44926d5ddb98
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
			balance += currentHand.getBet();
		else if (currentHand.isBlackJack())
			balance += currentHand.getBet() * 2.5;
		else
			balance += currentHand.getBet() * 2;

<<<<<<< HEAD
<<<<<<< HEAD
		bet = 0; // reset bet to 0
		currentHand.setBet(0);
=======
=======
>>>>>>> df7e8924c5a9b6adb3c5d097688c44926d5ddb98
//		}
>>>>>>> df7e8924c5a9b6adb3c5d097688c44926d5ddb98
	}

	/*
	 * Allows the player to hit and receive a card. Requires the Game to pass the
	 * card from the dealer
	 */
	public boolean hit(Card card) {
		currentHand.dealCard(card);
		if (currentHand.isBusted())
			currentHand.setFold(true);
		return currentHand.isBusted();
	}

	/*
	 * Doubles the player's bet, removing it from their balance.
	 */
<<<<<<< HEAD
<<<<<<< HEAD
	public boolean doubleDown(Card card) {
//		if (split) {
//			balance -= bet;
//			splitBet *= 2;
//			splitHand.dealCard(card);
//			folded = true;
//			return splitHand.isBusted();
//		} else {
		balance -= currentHand.getBet();
		currentHand.setBet(currentHand.getBet() * 2);
		currentHand.dealCard(card);
		currentHand.setFold(true);
		return currentHand.isBusted();
//		}
=======
=======
>>>>>>> df7e8924c5a9b6adb3c5d097688c44926d5ddb98
	public boolean doubleDown() {
		balance -= bet;
		bet *= 2;
		folded = true;
		return hand.isBusted();
<<<<<<< HEAD
>>>>>>> df7e8924c5a9b6adb3c5d097688c44926d5ddb98
=======
>>>>>>> df7e8924c5a9b6adb3c5d097688c44926d5ddb98
	}
 
	
	// TODO
	/**
	 * 
	 * @return
	 */
	public boolean split(Card card1, Card card2) {
		if(currentHand.isSplitable() && hand.size() < 4) {  
			// only splitHand when it has been split less than 4 times & is splitable
			
			BlackjackHand splitHand = new BlackjackHand();// create new hand
			splitHand.dealCard(currentHand.getCardForSplitting());// get card from old hand
			currentHand.dealCard(card1);
			splitHand.dealCard(card2);
			
			splitHand.setBet(currentHand.getBet());
			balance -= currentHand.getBet();
			
			hand.add(splitHand); // add new splitHand to the player's hand
			return true;
		}
		System.out.println("Not allowed to split on this hand");
		return false;
	}

	/*
	 * Resets values needed for next game
	 */
	public void discardCards() {
//		split = false;
		hand.clear();
		BlackjackHand firstHand = new BlackjackHand();
		hand.add(firstHand);
		currentHand = hand.get(0);
		currentHand.setFold(false);
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
		return currentHand.isBlackJack();
	}

	/**
	 * gets player's busted status
	 * @return whether player's hand total >21
	 */
	public boolean isBusted() {
		return currentHand.isBusted();
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
		return currentHand.getTotal();
	}

	/**
	 * returns the amount of hands this player has
	 * @return int of total number of hands
	 */
	public int numOfHands() {
		return hand.size();
	}
	
	/**
	 * player folds, setting folded to true
	 */
	public void fold() {
		currentHand.setFold(true);
	}

	/**
	 * gets player's folded status
	 * @return whether player is folded or not
	 */
	public boolean isFolded() {
		return currentHand.folded();
	}

	/**
	 * gets player's hands
	 * @return ArrayList player's hands
	 */
	public ArrayList<BlackjackHand> getArrayListHand() {
		return hand;
	}
	
	/**
	 * returns the currentHand that is being played
	 * @return BlackjackHand which is the currentHand
	 */
	public BlackjackHand getHand() {
		return currentHand;
	}
	
	/**
	 * gets the current amount of hands a player has
	 * @return int num of player's hands
	 */
	public int getNumOfBlackjackHands() {
		return hand.size();
	}
	/**
	 * iterates currentHand to the next possible hand it can move to
	 */
	public void goToNextPlayerHand() {
		currentHandIndex += 1;
		currentHand = hand.get(currentHandIndex);
	}
	
	/**
	 * Sets the currentHandIndex to a specified index and 
	 * changes the currentHand to the corresponding index as well
	 * @param index int the new index that it will be set too
	 */
	public void setCurrentHandIndex(int index) {
		currentHandIndex = index;
		currentHand = hand.get(currentHandIndex);
	}
	
	/**
	 * Returns the index of the currentHand ie where it 
	 * is located in the players hand
	 * @return int currentHandIndex 
	 */
	public int getCurrentHandIndex() {
		return currentHandIndex;
	}
	
	/**
	 * checks if currentHand is located at the last index in the
	 * arrayList of BlackjackHands
	 * @return boolean value of currentHandIndex == hand.size() - 1
	 */
	public boolean isInLastPlayerHand() {
		return currentHandIndex == hand.size() - 1;
	}
	/**
	 * returns the player information represented as a string
	 * @return a string containing the player's name, balance, hand, and hand total
	 */
	@Override
	public String toString() {
		String finalString = "";
		for(int i = 0; i < hand.size(); i++) {
			finalString += playerName + "\nBalance: " + balance + "\n" + hand.get(i) + "\nTotal Score: " + hand.get(i).getTotal() + "\n";
		}
		return finalString;
	}

}
