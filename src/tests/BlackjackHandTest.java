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
	public void testCardsGeneral() {
		//Player pl1 = new Player("")
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
