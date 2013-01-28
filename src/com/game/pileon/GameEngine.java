package com.game.pileon;

import java.util.ArrayList;
import java.util.HashMap;

import android.util.Log;

/**
 * GameEngine
 * 
 * This is the default game. 4 colors (R G B Y) and ten cards of each (1 through
 * 10)
 * 
 * @author nscross, breeze4
 * 
 */
public class GameEngine
{

	public Deck Deck;

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
	
	public boolean isGameOver;

	private final static String CARDCOLORS[] =
		{ "red", "green", "blue", "yellow" };
	
	private PointTracker mPointTracker;

	public GameEngine()
	{
		createDeck();
		setupGame();
		addPlaceholderCardsToDeck();
		isGameOver = false;
	}
	
	public GameEngine(SavedGame savedGameState)
	{
		Deck = savedGameState.saveDeck;
		Pile0 = savedGameState.savePiles.get(0);
		Pile1 = savedGameState.savePiles.get(1);
		Pile2 = savedGameState.savePiles.get(2);
		
		Hand0 = savedGameState.saveHands.get(0);
		Hand1 = savedGameState.saveHands.get(1);
		Hand2 = savedGameState.saveHands.get(2);
		Hand3 = savedGameState.saveHands.get(3);
		Hand4 = savedGameState.saveHands.get(4);
		
		Hand0.setDeck(Deck);
		Hand1.setDeck(Deck);
		Hand2.setDeck(Deck);
		Hand3.setDeck(Deck);
		Hand4.setDeck(Deck);
		
		setPointTracker(savedGameState.savePointTracker);
		
		isGameOver = false;
	}

	public Deck createDeck()
	{
		Deck = new Deck();
		String cardID = "";
		for (int colorNdx = 0; colorNdx < NUMBEROFCOLORS; colorNdx++)
		{
			for (int cardNdx = 0; cardNdx < NUMBEROFCARDSPERCOLOR; cardNdx++)
			{
				cardID = CARDCOLORS[colorNdx] + Integer.toString(cardNdx+1);
				DefaultGameCard cardToAdd = new DefaultGameCard(
						CARDCOLORS[colorNdx], cardNdx + 1, cardID);
				Deck.AddCard(cardToAdd);
				Log.i("PO CreateDeck", cardToAdd.toString());
			}
		}

		Deck.Shuffle();
		return Deck;
	}
	
	public void addPlaceholderCardsToDeck()
	{
		for (int cardNdx = 0; cardNdx < 5; cardNdx++)
		{
			Deck.addToEnd(createPlaceholderCard());
		}

	}
	
	public Card createPlaceholderCard()
	{
		DefaultGameCard placeholder = new DefaultGameCard();
		Log.i("PO CreateDeck", "adding placeholder card: " + placeholder.toString());
		return placeholder;
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
		Hand0.setDeck(Deck);
		Log.i("PO CreateDeck", "Hand0 gets: " + Hand0.toString());
		Hand1 = new Hand(dealTopCard());
		Hand1.setDeck(Deck);
		Log.i("PO CreateDeck", "Hand1 gets: " + Hand1.toString());
		Hand2 = new Hand(dealTopCard());
		Hand2.setDeck(Deck);
		Log.i("PO CreateDeck", "Hand2 gets: " + Hand2.toString());
		Hand3 = new Hand(dealTopCard());
		Hand3.setDeck(Deck);
		Log.i("PO CreateDeck", "Hand3 gets: " + Hand3.toString());
		Hand4 = new Hand(dealTopCard());
		Hand4.setDeck(Deck);
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

	public boolean isGameOver()
	{
		isGameOver = areHandsEmpty();
		Log.i("PO GameEngine", "is game over?: " + Boolean.toString(isGameOver));
		return isGameOver;
	}

	public boolean areHandsEmpty()
	{
		boolean areHandsEmpty = false;
		
		if( Hand0.isEmpty() && Hand1.isEmpty() &&
			Hand2.isEmpty() && Hand3.isEmpty() &&
			Hand4.isEmpty() )
		{
			areHandsEmpty = true;
		}
		Log.i("PO GameEngine", "are hands empty?: " + Boolean.toString(areHandsEmpty));
		return areHandsEmpty;
	}
	
	public void setPointTracker(PointTracker pointTracker)
	{
		mPointTracker = pointTracker;
		Pile0.setPointTracker(mPointTracker);
		Pile1.setPointTracker(mPointTracker);
		Pile2.setPointTracker(mPointTracker);
	}
	
	public ArrayList<Pile> getPileList()
	{
		ArrayList<Pile> pileList = new ArrayList<Pile>();
		pileList.add(0, Pile0);
		pileList.add(1, Pile1);
		pileList.add(2, Pile2);
		return pileList;
	}
	
	public ArrayList<Hand> getHandList()
	{
		ArrayList<Hand> handList = new ArrayList<Hand>();
		handList.add(0, Hand0);
		handList.add(1, Hand1);
		handList.add(2, Hand2);
		handList.add(3, Hand3);
		handList.add(4, Hand4);
		return handList;
	}
	
}
