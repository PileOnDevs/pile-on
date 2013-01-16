package com.game.pileon;

import android.util.Log;

/**
 * Hand class
 * 
 * Holds a single Card in your hand
 * 
 * @author breeze4
 * @since 2013-01-03
 */

public class Hand
{
	public Card mCard;
	private Deck mDeck;
	
	public Hand(Card card)
	{
		mCard = card;
		
	}
	//Wrapper methods for the underlying stack, push and pop are private to prevent
	//unauthorized changes to the pile without appropriate graphical updating and such
	/**
	 * Tests if this stack is empty
	 * @return true if and only if this stack contains no items; false otherwise
	 */
	public boolean isEmpty()
	{
		return (mCard == null);
	}
	
	/**
	 * Sends the Card this Hand is holding to the game for play and asks for another
	 * @return the Card this hand is holding
	 */
	public Card playCard()
	{
		Card newCard = getCardFromDeck();
		//TODO add in check to make sure the card isn't a placeholder, if so, display it but disable Hand
		Card oldCard = mCard;
		mCard = newCard;
		Log.i("PO Hand", "oldCard: " + oldCard.toString() + " newCard: " + newCard.toString());
		return oldCard; //TODO this returns the old card, but not used right now
	}
	
	
	public Card getCardFromDeck()
	{
		return mDeck.dealTopCard();
	}
	
	public String toString()
	{
		return mCard.toString();
	}
	
	public void setHand(Card card)
	{
		mCard = card;
	}
	
	public void setDeck(Deck deck){
		mDeck = deck;
	}
	
}
