package com.game.pileon;

import java.util.LinkedList;
import java.util.Random;

/**
 * 
 * @author nscross, breeze4
 * 
 */
public class Deck
{

	private LinkedList<Card> DeckOfCards;

	public Deck()
	{
		DeckOfCards = new LinkedList<Card>();
	}

	public void AddCard(Card cardToAdd)
	{
		if (DeckOfCards != null)
		{
			DeckOfCards.add(cardToAdd);
		}
	}

	/**
	 * Shuffle
	 * 
	 * Shuffles the deck like a little kid would, very scientific
	 */
	public void Shuffle()
	{
		if (DeckOfCards != null && DeckOfCards.size() > 0)
		{
			Random randomGenerator = new Random();

			int howManyTimesToShuffle = randomGenerator.nextInt(1024) + 512; // at
																				// least
																				// 512
																				// times,
																				// up
																				// to
																				// 1536
																				// times
			int howManyCardsInDeck = DeckOfCards.size(); // save this now, it's
															// not going to
															// change

			for (int shuffleCount = 0; shuffleCount < howManyTimesToShuffle; shuffleCount++)
			{
				int whichCardToMove = (randomGenerator
						.nextInt(howManyCardsInDeck));
				DeckOfCards.addFirst(DeckOfCards.remove(whichCardToMove));
			}
		}

	}

}
