 package model;

import java.util.ArrayList;

public class BlackjackHand implements Comparable<BlackjackHand> {
	private ArrayList<Card> hand;
	private boolean busted;
	private int total;
	
	
	/**
	 *  constructs a blackjack hand
	 */
	public BlackjackHand() {
		hand = new ArrayList<>();
		busted = false;
		total = 0;
	}

	/**
	 * compares to Hand totals returning > 0 if this hand total is more, 0 if they
	 * are equal, and < 0 if other hand total is more
	 * 
	 * @param otherHand another version of a blackjack hand
	 * 
	 * @return an integer value representative of the comparison 
	 */
	@Override
	public int compareTo(BlackjackHand otherHand) {
		return total - otherHand.getTotal();
	}

	/**
	 * returns true if hand is a "blackJack" hand ie (has 2 cards,
	 *  an ace and total == 21)
	 *  
	 *  @return boolean value of whether or not the hand is a blackjack hand
	 */
	public boolean isBlackJack() {
		int aceIndex = aceIndex();
		return (hand.size() == 2 && total == 21 && aceIndex != -1);
	}

	/**
	 * returns true if hand value == 21
	 * 
	 * @return returns true if hand total == 21 false otherwise
	 */
	public boolean isTwentyOne() {
		return total == 21;
	}

//	/**
//	 * Returns true if the hand can be split (both cards are equal value)
//	 */
//	public boolean canSplit() {
//		return hand.get(0).compareTo(hand.get(1)) == 0;
//	}
//
//	/**
//	 * Returns the second card so the hand can be split
//	 */
//	public Card split() {
//		return hand.remove(1);
//	}

	/**
	 * Deals a card to current hand updating the hand, total, and boolean busted if
	 * total > 21
	 * 
	 * @param dealtCard is a single card that is dealt to the player
	 * 
	 */
	public void dealCard(Card dealtCard) {
		// add card to hand
		hand.add(dealtCard);
		total += dealtCard.getValue();

		// if total over 21 check for aces
		if (total + dealtCard.getValue() > 21) {

			// replace aces value with one, one at a time until no more aces
			// with a value of 11 or total is under 21
			int aceIndex = this.aceIndex();
			while (aceIndex != -1 && total > 21) {

				hand.remove(aceIndex);
				total -= 10;
				hand.add(aceIndex, new Card(Rank.ONE, dealtCard.getSuit()));
				aceIndex = this.aceIndex();
			}

			if (total > 21) {
				busted = true;
			}
		}
	}

	/**
	 * returns -1 if no ace is present else returns index of first ace
	 * 
	 * @return an index of ace in hand arrayList but -1 if there is none
	 */
	private int aceIndex() {
		for (int i = 0; i < hand.size(); i++) {
			if (hand.get(i).getValue() == 11) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 
	 * @return total amount from hand
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * 
	 * @return boolean value to see if the hand is under 22
	 */
	public boolean isBusted() {
		return busted;
	}

	/**
	 * 
	 * @return arrayList reference hand
	 */
	public ArrayList<Card> getHand() {
		return hand;
	}

	public String toString() { return hand.toString(); }
}
