package model;

import java.util.ArrayList;

/**
 * @author Jakob Garcia
 */
public class Dealer {

	private Deck deck;
	private ArrayList<Player> players;
	private BlackjackHand dealerHand;
	private double moneyPot;

	public Dealer(ArrayList<Player> players) {
		// The dealer controls the card Deck, money, and interacts with Players
		deck = new Deck();
		this.players = players;
		moneyPot = 0;
		dealerHand = new BlackjackHand();
	}

	public void dealCards() {
		// deal Cards to each Player and store community Cards
		dealerHand.add(deck.getTopCard());
		for (Player player : players) {
			player.receiveCards(deck.getTopCard());
		}
		dealerHand.add(deck.getTopCard());
		for (Player player : players) {
			player.receiveCards(deck.getTopCard());
		}
	}

	public void reshuffle() {
		// Reset the Deck and shuffle. Also, clear all Player Cards.
		deck.resetDeck();
		for (Player player : players) {
			player.discardCards();
		}
	}

	public void collectAnte() {
		// Collect ante from each Player and store into pot
		for (Player player : players) {
			moneyPot += player.placeBet(2.0);
		}
	}

	public void payWinners(ArrayList<Player> winners) {
		// Pay the winning Player(s) the pot amount, dividing as necessary
		double payout = (int) ((moneyPot / winners.size()) * 100.0) / 100.0;
		for (Player player : winners) {
			player.receivePayout(payout);
		}
		moneyPot = 0;
	}

	public double checkPot() {
		return moneyPot;
	}

}
