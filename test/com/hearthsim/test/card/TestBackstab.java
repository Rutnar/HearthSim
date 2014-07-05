package com.hearthsim.test.card;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.hearthsim.card.Card;
import com.hearthsim.card.Deck;
import com.hearthsim.card.minion.Minion;
import com.hearthsim.card.spellcard.concrete.Backstab;
import com.hearthsim.util.BoardState;
import com.hearthsim.util.HearthTreeNode;

public class TestBackstab {

	private HearthTreeNode<BoardState> board;
	private static final byte mana = 2;
	private static final byte attack0 = 2;
	private static final byte health0 = 5;
	private static final byte health1 = 1;

	@Before
	public void setup() {
		board = new HearthTreeNode<BoardState>(new BoardState());
		Minion minion0 = new Minion("" + 0, mana, attack0, health0, attack0, health0, health0);
		Minion minion1 = new Minion("" + 0, mana, attack0, health0, attack0, health0, health0);
		Minion minion2 = new Minion("" + 0, mana, attack0, health1, attack0, health1, health1);
		Minion minion3 = new Minion("" + 0, mana, attack0, (byte)(health0-2), attack0, health0, health0);

		Backstab fb = new Backstab();
		board.data_.placeCard_hand(fb);
		board.data_.placeMinion_p0(minion0);
		board.data_.placeMinion_p1(minion1);
		board.data_.placeMinion_p1(minion2);
		board.data_.placeMinion_p1(minion3);
		
		board.data_.setMana_p0(10);
	}
	
	@Test
	public void test0() {


		Deck deck = null;
		
		Card theCard = board.data_.getCard_hand(0);
		HearthTreeNode<BoardState> res;
		
		res = theCard.useOn(0, 1, 0, board, deck);
		assertTrue(res == null);
		
		res = theCard.useOn(0, 0, 0, board, deck);
		assertTrue(res == null);

		res = theCard.useOn(0, 0, 1, board, deck);
		assertFalse(res == null);
		assertTrue(res.data_.getNumCards_hand() == 0);
		assertTrue(res.data_.getNumMinions_p0() == 1);
		assertTrue(res.data_.getNumMinions_p1() == 3);
		assertTrue(res.data_.getMana_p0() == 10);
		assertTrue(res.data_.getMinion_p0(0).getHealth() == health0-2);
		assertTrue(res.data_.getMinion_p0(0).getAttack() == attack0);
		assertTrue(res.data_.getMinion_p1(0).getHealth() == health0);
		assertTrue(res.data_.getMinion_p1(0).getAttack() == attack0);
		assertTrue(res.data_.getMinion_p1(1).getHealth() == health1);
		assertTrue(res.data_.getMinion_p1(1).getAttack() == attack0);
		assertTrue(res.data_.getMinion_p1(2).getHealth() == health0-2);
		assertTrue(res.data_.getMinion_p1(2).getAttack() == attack0);
		assertTrue(res.data_.getHero_p0().getHealth() == 30);
		assertTrue(res.data_.getHero_p1().getHealth() == 30);

		res = theCard.useOn(0, 1, 1, board, deck);

	}

	@Test
	public void test1() {


		Deck deck = null;
		
		Card theCard = board.data_.getCard_hand(0);
		HearthTreeNode<BoardState> res;

		res = theCard.useOn(0, 1, 1, board, deck);
		assertFalse(res == null);
		assertTrue(res.data_.getNumCards_hand() == 0);
		assertTrue(res.data_.getNumMinions_p0() == 1);
		assertTrue(res.data_.getNumMinions_p1() == 3);
		assertTrue(res.data_.getMana_p0() == 10);
		assertTrue(res.data_.getMinion_p0(0).getHealth() == health0);
		assertTrue(res.data_.getMinion_p0(0).getAttack() == attack0);
		assertTrue(res.data_.getMinion_p1(0).getHealth() == health0-2);
		assertTrue(res.data_.getMinion_p1(0).getAttack() == attack0);
		assertTrue(res.data_.getMinion_p1(1).getHealth() == health1);
		assertTrue(res.data_.getMinion_p1(1).getAttack() == attack0);
		assertTrue(res.data_.getMinion_p1(2).getHealth() == health0-2);
		assertTrue(res.data_.getMinion_p1(2).getAttack() == attack0);
		assertTrue(res.data_.getHero_p0().getHealth() == 30);
		assertTrue(res.data_.getHero_p1().getHealth() == 30);

		res = theCard.useOn(0, 1, 1, board, deck);

	}

	@Test
	public void test2() {


		Deck deck = null;
		
		Card theCard = board.data_.getCard_hand(0);
		HearthTreeNode<BoardState> res;

		res = theCard.useOn(0, 1, 2, board, deck);
		assertFalse(res == null);
		assertTrue(res.data_.getNumCards_hand() == 0);
		assertTrue(res.data_.getNumMinions_p0() == 1);
		assertTrue(res.data_.getNumMinions_p1() == 2);
		assertTrue(res.data_.getMana_p0() == 10);
		assertTrue(res.data_.getMinion_p0(0).getHealth() == health0);
		assertTrue(res.data_.getMinion_p0(0).getAttack() == attack0);
		assertTrue(res.data_.getMinion_p1(0).getHealth() == health0);
		assertTrue(res.data_.getMinion_p1(0).getAttack() == attack0);
		assertTrue(res.data_.getMinion_p1(1).getHealth() == health0-2);
		assertTrue(res.data_.getMinion_p1(1).getAttack() == attack0);
		assertTrue(res.data_.getHero_p0().getHealth() == 30);
		assertTrue(res.data_.getHero_p1().getHealth() == 30);

		res = theCard.useOn(0, 1, 1, board, deck);

	}
	
	@Test
	public void test3() {


		Deck deck = null;
		
		Card theCard = board.data_.getCard_hand(0);
		HearthTreeNode<BoardState> res;

		res = theCard.useOn(0, 1, 3, board, deck);
		assertTrue(res == null);

	}
}
