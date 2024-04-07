package model;

public class BlackjackHand implements Comparable<BlackjackHand> {
	private Card[] hand = new Card[2];

	@Override
	public int compareTo(BlackjackHand o) { // Need logic for ace 1/11
		// TODO Auto-generated method stub
		return 0;
	}

	public void add(Card dealtCard) {
		// TODO Auto-generated method stub
		if (hand[0] == null) {
			hand[0] = dealtCard;
		}
		hand[1] = dealtCard;
	}

}
