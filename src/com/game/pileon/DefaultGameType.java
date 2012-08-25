package com.game.pileon;

import android.graphics.Color;



/**
 * DefaultGameType
 * 
 * This is the default game.  4 colors (R G B Y) and ten cards of each (1 through 10)
 * @author nscross
 *
 */
public class DefaultGameType implements GameType {

	private Deck Deck;
	
	private final static int NumberOfColors = 4;
	private final static int NumberOfCardsPerColor = 10;
	
	private final static int CardColors[]  = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};
	
	public enum CardColorTypes
	{
		Red,
		Green,
		Blue,
		Yellow				
	}
	
	
	public DefaultGameType()
	{
		CreateDeck();
	}
	
	public void CreateDeck() {
		Deck = new Deck();

		for(int colorNdx = 0; colorNdx < NumberOfColors; colorNdx++)
		{
			for(int cardNdx = 0; cardNdx < NumberOfCardsPerColor; cardNdx++)
			{				
				DefaultGameCard cardToAdd = new DefaultGameCard(CardColors[colorNdx], cardNdx + 1);	
				Deck.AddCard(cardToAdd);
			}
		}
		
		Deck.Shuffle();
	}

}
