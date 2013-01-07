package com.game.pileon;

import android.graphics.Color;
import android.util.Log;

/**
 * GameEngine
 * 
 * This is the default game. 4 colors (R G B Y) and ten cards of each (1 through
 * 10)
 * 
 * @author nscross
 * 
 */
public class GameEngine
{

	private Deck Deck;

	private final static int NUMBEROFCOLORS = 4;
	private final static int NUMBEROFCARDSPERCOLOR = 10;
	
	public Pile Pile0;
	public Pile Pile1;
	public Pile Pile2;
	
	public Hand Hand0;
	public Hand Hand1;
	public Hand Hand2;
	public Hand Hand3;
	public Hand Hand4;

	private final static int CARDCOLORS[] =
	{ Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW };

	public enum CardColorTypes
	{
		Red, Green, Blue, Yellow
	}

	public GameEngine()
	{
		createDeck();
		setupGame();
	}

//	public void createDeck()
//	{
//		Deck = new Deck();
//		int cardID = 0;
//		for (int cardNdx = 0; cardNdx < NUMBEROFCARDSPERCOLOR*NUMBEROFCOLORS; cardNdx++)
//			{
//				DefaultGameCard cardToAdd = new DefaultGameCard(
//						CARDCOLORS[0], 1, cardID);
//				Deck.AddCard(cardToAdd);
//				cardID++;
//				Log.i("PO CreateDeck", cardToAdd.toString());
//			}
//
//		Deck.Shuffle();
//	}
//	
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
		Log.i("PO CreateDeck", "Pile0 gets: " + Pile0.toString());
		Pile1 = new Pile(dealTopCard());
		Log.i("PO CreateDeck", "Pile1 gets: " + Pile1.toString());
		Pile2 = new Pile(dealTopCard());
		Log.i("PO CreateDeck", "Pile2 gets: " + Pile2.toString());
	}
	
	public void createHands()
	{
		Hand0 = new Hand(dealTopCard());
		Log.i("PO CreateDeck", "Hand0 gets: " + Hand0.toString());
		Hand1 = new Hand(dealTopCard());
		Log.i("PO CreateDeck", "Hand1 gets: " + Hand1.toString());
		Hand2 = new Hand(dealTopCard());
		Log.i("PO CreateDeck", "Hand2 gets: " + Hand2.toString());
		Hand3 = new Hand(dealTopCard());
		Log.i("PO CreateDeck", "Hand3 gets: " + Hand3.toString());
		Hand4 = new Hand(dealTopCard());
		Log.i("PO CreateDeck", "Hand4 gets: " + Hand4.toString());
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
