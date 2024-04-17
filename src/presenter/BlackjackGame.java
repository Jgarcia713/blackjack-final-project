package presenter;

import model.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BlackjackGame extends OurObservable {
	private ArrayList<Player> players;
	private ArrayList<Integer> playerBets;
	private Iterator<Player> iterator;
	private Dealer dealer;
	private Player activePlayer;
	private int activePlayerBet;
	public boolean isGameOver = false;

	public BlackjackGame() {
		// NOTE: BlackJackGame and Dealer both reference the same ArrayList of players
		// This way, either can modify the list as necessary, and the other object will
		// be able to see those changes.
		players = new ArrayList<Player>();
		dealer = new Dealer(players);
	}

	public void addPlayer(String name, boolean isPlayer) {
		players.add(new Player(name, isPlayer));
	}

	public void setActivePlayerBet(int bet) {
		activePlayerBet = bet;
	}

	/**
	 * starts a round by collecting everyone's bets and dealing out cards.
	 *
	 * @apiNote currently uses a fixed bet of 20. Eventually need players to choose
	 *          their own bets
	 */
	public void startRound() {
		// sets up iterator and assigns activePlayer to the first player
		try {
			iterator = players.iterator();
			activePlayer = iterator.next();
		} catch (NoSuchElementException e) {
			System.out.println("ERROR: No players have been added to the game!");
			return;
		}
		isGameOver = false; // reset isGameOver flag
		dealer.dealCards();
		for (Player player : players) {
			if (player == activePlayer) {
				dealer.collectBet(player, activePlayerBet); // Collect bet from the active player
			} else
				dealer.collectBet(player, 20); // collect bet from each player
		}
		dealer.dealCards(); // deal cards to each player and the dealer
		if (dealer.hasTwentyOne()) {
			isGameOver = true;
		}
		notifyObservers(this);
	}

	/**
	 * given a string input of a move type, will perform the correct action. Will
	 * also update activePlayer and/or end the round as necessary.
	 *
	 * @apiNote Currently assumes that the active player is the one who made a move.
	 *          If we want to do online networking, we probably need to verify that
	 *          the request came from the correct player, but depends on how it's
	 *          setup.
	 * @param action string input from user saying what their move is.
	 */
	public boolean makeMove(Actions action) {
		if (action == Actions.HIT) {
			activePlayer.hit(dealer.dealSingleCard());
			if (activePlayer.isBusted()) {
				if (iterator.hasNext()) {
					activePlayer = iterator.next();
				} else {
					endRound();
				}
				notifyObservers(this);
				return true;
			}
		} else if (action == Actions.STAND) {
			if (iterator.hasNext()) {
				activePlayer = iterator.next();
			} else {
				endRound();
			}
		} else if (action == Actions.DOUBLE) {
			// TODO: fill out if statement
		} else if (action == Actions.SPLIT) {
			// TODO: fill out if statement
		}
		notifyObservers(this);
		return false;
	}

	/**
	 * performs necessary actions after all players have completed their turns. 1.
	 * dealer plays their turn 2. dealer pays out to winners 3. set isGameOver flag
	 * to true
	 */
	private void endRound() {
		playDealersTurn();
		dealer.payWinners();
		isGameOver = true;
		notifyObservers(this);
	}

	/**
	 * tells dealer to hit until it has reached the minimum score requirement.
	 */
	private void playDealersTurn() {
		dealer.hitUntilMinScore();

	}

	public Player getActivePlayer() {
		return activePlayer;
	}

	public String dealerString() {
		return dealer.toString();
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public ArrayList<Card> getDealerHand() {
		return dealer.getDealerHand();
	}
}
