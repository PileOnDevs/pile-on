package com.game.pileon;

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

	public boolean isGameOver;

	private final static String CARDCOLORS[] =
		{ "red", "green", "blue", "yellow" };

	public GameEngine()
	{
		createDeck();
		setupGame();
		isGameOver = false;
	}

	public void createDeck()
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
		isGameOver = areHandsEmpty() || !legalMovesAvailable();
		Log.i("PO GameEngine", "is game over?: " + Boolean.toString(isGameOver));
		return isGameOver;
	}

	public boolean areHandsEmpty()
	{
		boolean areHandsEmpty = false;
		boolean tempHand0 = Hand0.isEmpty();
		boolean tempHand1 = Hand1.isEmpty();
		boolean tempHand2 = Hand2.isEmpty();
		boolean tempHand3 = Hand3.isEmpty();
		boolean tempHand4 = Hand4.isEmpty();
		
		if( tempHand0 && tempHand1 && tempHand2 && tempHand3 && tempHand4)
//		if( Hand0.isEmpty() && Hand1.isEmpty() &&
//				Hand2.isEmpty() && Hand3.isEmpty() &&
//				Hand4.isEmpty() )
		{
			Log.i("PO GameEngine", "is this hand empty?: " + Boolean.toString(tempHand0));
			Log.i("PO GameEngine", "is this hand empty?: " + Boolean.toString(tempHand1));
			Log.i("PO GameEngine", "is this hand empty?: " + Boolean.toString(tempHand2));
			Log.i("PO GameEngine", "is this hand empty?: " + Boolean.toString(tempHand3));
			Log.i("PO GameEngine", "is this hand empty?: " + Boolean.toString(tempHand4));

			areHandsEmpty = true;
		}
		Log.i("PO GameEngine", "are hands empty?: " + Boolean.toString(areHandsEmpty));
		return areHandsEmpty;
	}

	public boolean legalMovesAvailable()
	{
		//TODO refactor this to a cleaner format and code that can be re-used for a game solving class
		boolean legalMovesAvailable = false;
		if( Pile0.isMoveLegal(Hand0.mCard) || Pile0.isMoveLegal(Hand1.mCard) || Pile0.isMoveLegal(Hand2.mCard) ||
				Pile0.isMoveLegal(Hand3.mCard) || Pile0.isMoveLegal(Hand4.mCard))
		{
			legalMovesAvailable = true;
		}
		else if( Pile1.isMoveLegal(Hand0.mCard) || Pile1.isMoveLegal(Hand1.mCard) || Pile1.isMoveLegal(Hand2.mCard) ||
				Pile1.isMoveLegal(Hand3.mCard) || Pile1.isMoveLegal(Hand4.mCard))
		{
			legalMovesAvailable = true;
		}
		else if( Pile2.isMoveLegal(Hand0.mCard) || Pile2.isMoveLegal(Hand1.mCard) || Pile2.isMoveLegal(Hand2.mCard) ||
				Pile2.isMoveLegal(Hand3.mCard) || Pile2.isMoveLegal(Hand4.mCard))
		{
			legalMovesAvailable = true;
		}
		Log.i("PO GameEngine", "legal moves available?: " + Boolean.toString(legalMovesAvailable));
		return legalMovesAvailable;
	}

}
