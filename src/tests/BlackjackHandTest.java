package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import model.*;

public class BlackjackHandTest {
	@Test
	public void testConstructors() {
		Player pl1 = new Player("player", true);
		assertTrue("player".equals(pl1.getName()));
		assertTrue(pl1.isPlayer());
		assertEquals(pl1.checkBalance(), 100.00);
		pl1.toString();
		Player pl2 = new Player("tester", 1264.55, false);
		assertTrue("tester".equals(pl2.getName()));
		assertFalse(pl2.isPlayer());
		assertEquals(pl2.checkBalance(), 1264.55);
		pl2.toString();
	}
	
	@Test
	public void testCompare() {
		Player pl1 = new Player("player", true);
		pl1.receiveCards(new Card(Rank.TEN, Suit.DIAMONDS));
		pl1.receiveCards(new Card(Rank.DEUCE, Suit.DIAMONDS));


		Player pl2 = new Player("tester", 1264.55, false);
		pl2.receiveCards(new Card(Rank.TEN, Suit.DIAMONDS));
		pl2.receiveCards(new Card(Rank.THREE, Suit.CLUBS));

		assertEquals(-1, pl1.getHand().compareTo(pl2.getHand()));
		assertFalse(pl1.getHand().isTwentyOne());
		pl1.receiveCards(new Card(Rank.NINE, Suit.DIAMONDS));
		assertTrue(pl1.getHand().isTwentyOne());

	
	}
	
//	@Test 
//	public void testSplitGeneral() {
//		Player pl = new Player("test", true);
////		assertFalse(pl.isSplit());
//		pl.receiveCards(new Card(Rank.TEN, Suit.DIAMONDS));
//		pl.receiveCards(new Card(Rank.TEN, Suit.DIAMONDS));
////		pl.split();
////		assertTrue(pl.isSplit());
//		assertEquals(pl.getHandTotal(), 10);
//		pl.placeBet(10);
//		assertEquals(pl.getBet(), 10);
//		pl.doubleDown(new Card(Rank.ACE, Suit.CLUBS));
//		assertEquals(pl.getBet(), 20);
//		assertEquals(pl.checkBalance(), 980.00);
//		assertEquals(pl.getHandTotal(), 21);
//		pl.receivePayout(false);
//		assertEquals(pl.getBet(), 20);
//		assertEquals(pl.checkBalance(), 1030.00);
//		assertTrue(pl.hit(new Card(Rank.TEN, Suit.DIAMONDS)));
//		pl.receivePayout(false);
//		assertEquals(pl.getBet(), 20);
//		assertEquals(pl.checkBalance(), 1070.00);
//		pl.receivePayout(true);
//		assertEquals(pl.getBet(), 20);
//		assertEquals(pl.checkBalance(), 1090.00);
//	}
	
	@Test
	public void testCardsGeneral() {
		Player pl = new Player("test", true);
		pl.receiveCards(new Card(Rank.TEN, Suit.DIAMONDS));
		assertEquals(pl.getHandTotal(), 10);
        assertFalse(pl.hasBlackjack());
        assertFalse(pl.isBusted());
        pl.receiveCards(new Card(Rank.ACE, Suit.HEARTS));
        assertEquals(pl.getHandTotal(), 21);
        assertTrue(pl.hasBlackjack());
        assertFalse(pl.isBusted());
        assertFalse(pl.hit(new Card(Rank.DEUCE, Suit.SPADES)));
        assertEquals(pl.getHandTotal(), 13);
        assertFalse(pl.hasBlackjack());
        assertFalse(pl.isBusted());
        assertFalse(pl.isFolded());
        assertFalse(pl.hit(new Card(Rank.EIGHT, Suit.SPADES)));
        assertEquals(pl.getHandTotal(), 21);
        assertFalse(pl.hasBlackjack());
        assertFalse(pl.isBusted());
        assertFalse(pl.isFolded());
        assertTrue(pl.hit(new Card(Rank.DEUCE, Suit.SPADES)));
        assertEquals(pl.getHandTotal(), 23);
        assertFalse(pl.hasBlackjack());
        assertTrue(pl.isBusted());
        assertTrue(pl.isFolded()); 
        pl.discardCards();
        assertEquals(pl.getHandTotal(), 0);
        assertFalse(pl.hasBlackjack());
        assertFalse(pl.isBusted());
        assertFalse(pl.hit(new Card(Rank.FIVE, Suit.SPADES)));
        assertEquals(pl.getHandTotal(), 5);
        assertFalse(pl.hasBlackjack());
        assertFalse(pl.isBusted());
	}
	
	@Test
	public void testBet() {
		Player pl = new Player("test", 10000.00, true);
		assertEquals(pl.checkBalance(), 10000.00);
		assertEquals(pl.getBet(), 0);
		pl.placeBet(1000.00);
		assertEquals(pl.checkBalance(), 9000.00);
		assertEquals(pl.getBet(), 1000.00);
		pl.placeBet(0.01);
		assertEquals(pl.checkBalance(), 8999.99);
		assertEquals(pl.getBet(), 0000.01);
		assertFalse(pl.doubleDown(new Card(Rank.DEUCE, Suit.SPADES)));
		assertEquals(pl.checkBalance(), 8999.98);
		assertEquals(pl.getBet(), 0000.02);
		pl.placeBet(1000.00);
		assertEquals(pl.checkBalance(), 7999.98);
		assertEquals(pl.getBet(), 1000.00);
		pl.receivePayout(true);
		assertEquals(pl.checkBalance(), 8999.98);
		assertEquals(pl.getBet(), 1000.00);
		pl.receivePayout(false);
		assertEquals(pl.checkBalance(), 10999.98);
		assertEquals(pl.getBet(), 1000.00);
		pl.discardCards();
		pl.receiveCards(new Card(Rank.JACK, Suit.CLUBS));
		pl.receiveCards(new Card(Rank.ACE, Suit.CLUBS));
		pl.receivePayout(false);
		assertEquals(pl.checkBalance(), 13499.98);
		assertEquals(pl.getBet(), 1000.00);
	}
	
	@Test
	public void testFolding() {
		Player pl = new Player("test", true);
		assertFalse(pl.isFolded());
		pl.fold();
		assertTrue(pl.isFolded());
	}
	
	@Test
    public void testAces() {
        Card ace = new Card(Rank.ACE, Suit.CLUBS);
        Player pl = new Player("test", true);
        assertFalse(pl.hit(ace));
        assertEquals(pl.getHandTotal(), 11);
        assertFalse(pl.hasBlackjack());
        assertFalse(pl.isBusted());
        assertFalse(pl.hit(ace));
        assertEquals(pl.getHandTotal(), 12);
        assertFalse(pl.hasBlackjack());
        assertFalse(pl.isBusted());
        assertFalse(pl.hit(ace));
        assertEquals(pl.getHandTotal(), 13);
        assertFalse(pl.hasBlackjack());
        assertFalse(pl.isBusted());
        assertFalse(pl.hit(ace));
        assertEquals(pl.getHandTotal(), 14);
        assertFalse(pl.hasBlackjack());
        assertFalse(pl.isBusted());
        assertFalse(pl.hit(ace));
        assertEquals(pl.getHandTotal(), 15);
        assertFalse(pl.hasBlackjack());
        assertFalse(pl.isBusted());
        assertFalse(pl.hit(ace));
        assertEquals(pl.getHandTotal(), 16);
        assertFalse(pl.hasBlackjack());
        assertFalse(pl.isBusted());
        assertFalse(pl.hit(ace));
        assertEquals(pl.getHandTotal(), 17);
        assertFalse(pl.hasBlackjack());
        assertFalse(pl.isBusted());
        assertFalse(pl.hit(ace));
        assertEquals(pl.getHandTotal(), 18);
        assertFalse(pl.hasBlackjack());
        assertFalse(pl.isBusted());
        assertFalse(pl.hit(ace));
        assertEquals(pl.getHandTotal(), 19);
        assertFalse(pl.hasBlackjack());
        assertFalse(pl.isBusted());
        assertFalse(pl.hit(ace));
        assertEquals(pl.getHandTotal(), 20);
        assertFalse(pl.hasBlackjack());
        assertFalse(pl.isBusted());
        assertFalse(pl.hit(ace));
        assertEquals(pl.getHandTotal(), 21);
        assertFalse(pl.isBusted());
        assertFalse(pl.hit(ace));
        assertEquals(pl.getHandTotal(), 12);
        assertFalse(pl.hasBlackjack());
        assertFalse(pl.isBusted());
        assertFalse(pl.hit(ace));
        assertEquals(pl.getHandTotal(), 13);
        assertFalse(pl.hasBlackjack());
        assertFalse(pl.isBusted());
        assertFalse(pl.hit(ace));
        assertEquals(pl.getHandTotal(), 14);
        assertFalse(pl.hasBlackjack());
        assertFalse(pl.isBusted());
        assertFalse(pl.hit(ace));
        assertEquals(pl.getHandTotal(), 15);
        assertFalse(pl.hasBlackjack());
        assertFalse(pl.isBusted());
        assertFalse(pl.hit(ace));
        assertEquals(pl.getHandTotal(), 16);
        assertFalse(pl.hasBlackjack());
        assertFalse(pl.isBusted());
        assertFalse(pl.hit(ace));
        assertEquals(pl.getHandTotal(), 17);
        assertFalse(pl.hasBlackjack());
        assertFalse(pl.isBusted());
        assertFalse(pl.hit(ace));
        assertEquals(pl.getHandTotal(), 18);
        assertFalse(pl.hasBlackjack());
        assertFalse(pl.isBusted());
        assertFalse(pl.hit(ace));
        assertEquals(pl.getHandTotal(), 19);
        assertFalse(pl.hasBlackjack());
        assertFalse(pl.isBusted());
        assertFalse(pl.hit(ace));
        assertEquals(pl.getHandTotal(), 20);
        assertFalse(pl.hasBlackjack());
        assertFalse(pl.isBusted());
        assertFalse(pl.hit(ace));
        assertEquals(pl.getHandTotal(), 21);
        assertFalse(pl.isBusted());
        assertTrue(pl.hit(ace));
        assertEquals(pl.getHandTotal(), 22);
        assertFalse(pl.hasBlackjack());
        assertTrue(pl.isBusted());
    }
}
