package model;

import java.util.ArrayList;

/**
 * @author Jakob Garcia
 */
public class Dealer {

	private Deck deck;
	private ArrayList<BlackjackPlayer> players;
	private BlackjackHand dealerHand;

	public Dealer(ArrayList<BlackjackPlayer> players) {
		deck = new Deck();
		this.players = players;
		dealerHand = new BlackjackHand();
	}

	/*
	 * Deal a card to each player and then to the dealer
	 */
	public void dealCards() {
		for (BlackjackPlayer player : players) {
			player.receiveCards(deck.getTopCard());
		}
		dealerHand.dealCard(deck.getTopCard());

		for (BlackjackPlayer player : players) {
			player.receiveCards(deck.getTopCard());
		}
		dealerHand.dealCard(deck.getTopCard());

	}

	/*
	 * Reshuffle the deck and clear Player cards
	 */
	public void reshuffle() {
		// Reset the Deck and shuffle. Also, clear all Player Cards.
		deck.resetDeck();
		for (BlackjackPlayer player : players) {
			player.discardCards();
		}
	}

	/*
	 * Collect a bet from each Player and store that number
	 */
	public void collectBet(ArrayList<Double> bets) {
		for (int i = 0; i < bets.size(); i++) {
			players.get(i).placeBet(bets.get(i), false);
		}
	}

	/*
	 * Pay the winning Player(s) based on their bets. If they got Blackjack, they
	 * get 1.5x their bet
	 */
	public void payWinners(ArrayList<BlackjackPlayer> winners) {
		for (BlackjackPlayer player : winners) {
			if (player.hasBlackjack())
				player.receivePayout(true);
			else
				player.receivePayout(false);
		}
	}

}
