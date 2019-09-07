import java.awt.*;  
import java.awt.event.*;  
import javax.swing.JOptionPane;

import java.util.Random;

public class Deck {
	
		public enum Suit {
			CLUBS, DIAMONDS, HEARTS, SPADES
		}

		public enum Rank {
			TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
		}

		private class Card {
			public Suit mysuit;
			public Rank myrank;
		}
		
		private Card[] mycards = null;
		
		
		public void Shuffle() {
			//maybe dumb old-fashioned and slow idea but for now just leave it:
			//(1) make a list of 52 entries of random numbers
			//(2) as you bubble-sort that list, you also change the deck
			//(3) it is big-O of N-squared but since it is only 52 cards, no big deal
			Random rr = new Random();
			int[] myrand = new int[52];
			
			for (int ii=0; ii < 52; ii++)
				myrand[ii] = rr.nextInt(1000); //0 to 999
			
			int lastcardindex = 51;
			boolean didswap = true;
			while (didswap)
			{
				didswap = false;
				for (int cc=0; cc <= lastcardindex; cc++)
				{
					if (cc == lastcardindex) //no pairs of cards left
						break;
						
					int AA = myrand[cc];
					int BB = myrand[cc+1];
					if (BB < AA)
					{
						didswap = true;
						myrand[cc] = BB;
						myrand[cc+1] = AA;
						
						Card temp = mycards[cc];
						mycards[cc] = mycards[cc+1];
						mycards[cc+1] = temp;
					}
				}
				lastcardindex--;
			}
			
			for (int ii=0; ii < 52; ii++)
				System.out.printf("ii=%d %d %s%s%n", ii, myrand[ii], mycards[ii].myrank, mycards[ii].mysuit);
		}
		
		public boolean IsPair(int aa, int bb) {
			if (aa <= 51 && aa >= 0 && bb <= 51 && bb >= 0)
			{
				if (mycards[aa].myrank == mycards[bb].myrank)
					return true;
				else
					return false;
			}
			else
				return false;
		}
		
		public String GetCardString(int ii) {
			String suitstr = null;
			String rankstr = null;
			if (ii <= 51 && ii >= 0)
			{
				if (mycards[ii].mysuit == Suit.CLUBS)
					suitstr = "C";
				else if (mycards[ii].mysuit == Suit.DIAMONDS)
					suitstr = "D";
				else if (mycards[ii].mysuit == Suit.HEARTS)
					suitstr = "H";
				else
					suitstr = "S";
				
				if (mycards[ii].myrank == Rank.TWO)
					rankstr = "2";
				else if (mycards[ii].myrank == Rank.THREE)
					rankstr = "3";
				else if (mycards[ii].myrank == Rank.FOUR)
					rankstr = "4";
				else if (mycards[ii].myrank == Rank.FIVE)
					rankstr = "5";
				else if (mycards[ii].myrank == Rank.SIX)
					rankstr = "6";
				else if (mycards[ii].myrank == Rank.SEVEN)
					rankstr = "7";
				else if (mycards[ii].myrank == Rank.EIGHT)
					rankstr = "8";
				else if (mycards[ii].myrank == Rank.NINE)
					rankstr = "9";
				else if (mycards[ii].myrank == Rank.TEN)
					rankstr = "10";
				else if (mycards[ii].myrank == Rank.JACK)
					rankstr = "J";
				else if (mycards[ii].myrank == Rank.QUEEN)
					rankstr = "Q";
				else if (mycards[ii].myrank == Rank.KING)
					rankstr = "K";
				else
					rankstr = "A";
				
				return (rankstr+suitstr);
			}
			else
				return "";
		}
		
		
		Deck() {
			mycards = new Card[52];
			
			Suit suit = Suit.CLUBS;
			Rank rank = Rank.TWO;
			
			for (int ii=0; ii < 52; ii++) {	
				mycards[ii] = new Card();
				mycards[ii].mysuit = suit;
				mycards[ii].myrank = rank;
				
				if (suit == Suit.CLUBS)
					suit = Suit.DIAMONDS;
				else if (suit == Suit.DIAMONDS)
					suit = Suit.HEARTS;
				else if (suit == Suit.HEARTS)
					suit = Suit.SPADES;
				else if (suit == Suit.SPADES) {
					suit = Suit.CLUBS;
					if (rank == Rank.TWO)
						rank = Rank.THREE;
					else if (rank == Rank.THREE)
						rank = Rank.FOUR;
					else if (rank == Rank.FOUR)
						rank = Rank.FIVE;
					else if (rank == Rank.FIVE)
						rank = Rank.SIX;
					else if (rank == Rank.SIX)
						rank = Rank.SEVEN;
					else if (rank == Rank.SEVEN)
						rank = Rank.EIGHT;
					else if (rank == Rank.EIGHT)
						rank = Rank.NINE;
					else if (rank == Rank.NINE)
						rank = Rank.TEN;
					else if (rank == Rank.TEN)
						rank = Rank.JACK;
					else if (rank == Rank.JACK)
						rank = Rank.QUEEN;
					else if (rank == Rank.QUEEN)
						rank = Rank.KING;
					else if (rank == Rank.KING)
						rank = Rank.ACE;
					else
						rank = Rank.TWO;
				}
			}
		}
}