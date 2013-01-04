package com.game.pileon;

import android.graphics.Color;
import android.util.Log;

/**
 * DefaultGameType
 * 
 * This is the default game. 4 colors (R G B Y) and ten cards of each (1 through
 * 10)
 * 
 * @author nscross
 * 
 */
public class DefaultGameType implements GameType
{

	private Deck Deck;

	private final static int NUMBEROFCOLORS = 4;
	private final static int NUMBEROFCARDSPERCOLOR = 10;
	
	private Pile Pile0;
	private Pile Pile1;
	private Pile Pile2;
	
	private Hand Hand0;
	private Hand Hand1;
	private Hand Hand2;
	private Hand Hand3;
	private Hand Hand4;

	private final static int CARDCOLORS[] =
	{ Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW };

	public enum CardColorTypes
	{
		Red, Green, Blue, Yellow
	}

	public DefaultGameType()
	{
		createDeck();
		setupGame();
	}

	public void createDeck()
	{
		Deck = new Deck();
		int cardID = 0;
		for (int colorNdx = 0; colorNdx < NUMBEROFCOLORS; colorNdx++)
		{
			for (int cardNdx = 0; cardNdx < NUMBEROFCARDSPERCOLOR; cardNdx++)
			{
				DefaultGameCard cardToAdd = new DefaultGameCard(
						CARDCOLORS[colorNdx], cardNdx + 1, cardID);
				Deck.AddCard(cardToAdd);
				cardID++;
				Log.i("PO CreateDeck", cardToAdd.toString());
			}
		}

		Deck.Shuffle();
	}
	
	public void createPiles()
	{
		Pile0 = new Pile(dealTopCard());
		Pile1 = new Pile(dealTopCard());
		Pile2 = new Pile(dealTopCard());
	}
	
	public void createHands()
	{
		Hand0 = new Hand(dealTopCard());
		Hand1 = new Hand(dealTopCard());
		Hand2 = new Hand(dealTopCard());
		Hand3 = new Hand(dealTopCard());
		Hand4 = new Hand(dealTopCard());
	}
	
	public void setupGame()
	{
		createPiles();
		createHands();
	}
	
	public Card dealTopCard()
	{
		return Deck.dealTopCard();
	}
	
	

}
