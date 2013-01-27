package com.game.pileon;

import java.util.Stack;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import android.util.Log;

/**
 * Pile class
 * 
 * Wrapper class for a Stack that adds some additional game-specific functionality and coordination with PileView
 * 
 * @author breeze4
 * @since 2012-12-30
 */
@Root(name="Pile")
public class Pile
{
	@ElementList(name="PileStack")
	private Stack<Card> mPile;
	
	private PointTracker mPointTracker;
	
	public Pile(Card card)
	{
		mPile = new Stack<Card>();
		
		mPile.push(card);
	}
	
	public Pile(@ElementList(name="PileStack")Stack<Card> PileStack)
	{
		mPile = PileStack;
	}
	
	//Wrapper methods for the underlying stack, push and pop are private to prevent
	//unauthorized changes to the pile without appropriate graphical updating and such
	/**
	 * Tests if this stack is empty
	 * @return true if and only if this stack contains no items; false otherwise
	 */
	public boolean isEmpty()
	{
		return !(mPile.size() != 0);
	}
	
	/**
	 * Looks at the object at the top of the stack without removing it from the stack
	 * @return the object at the top of the stack (the last item of the Vector object)
	 */
	public Card peek()
	{
		return mPile.peek();
	}
	
	/**
	 * Removes the object at the top of the stack and returns that object as the value of this function
	 * @return The object at the top of this stack (the last item of the Vector object)
	 */
	private Card pop()
	{
		return mPile.pop();
	}
	
	/**
	 * Pushes an item onto the top of this stack
	 * @return true if the item pushed is at the top of the stack now
	 */
	private boolean push(Card card)
	{
		mPile.push(card);
		return card.equals(mPile.peek());
	}
	
	//Game specific methods
	/**
	 * Determines whether or not the card can be played to the pile based on color or value
	 * @return true if the move is legal (either the colors match or the values match)
	 */
	public boolean isMoveLegal(Card cardPlayed)
	{
		if (cardPlayed == null){
			Log.i("PO Drag", "card being dropped is null");
			return false;
		}
		else if (cardPlayed.equalValueTo(peek()))
		{
			Log.i("PO Drag", "card being dropped has same value");
			return true;
		}
		else if (cardPlayed.equalColorTo(peek()))
		{
			Log.i("PO Drag", "card being dropped has same color");
			return true;
		}
		Log.i("PO Drag", "move is not legal " + cardPlayed.toString());
		return false;
	}
	
	/**
	 * Takes in a card, updates the pile and computes the points gained
	 * @return points gained by drop
	 */
	public boolean handleDrop(Card cardPlayed)
	{
		boolean dropSuccess = isMoveLegal(cardPlayed);
		
		if(dropSuccess)
		{
			mPointTracker.processMove(cardPlayed, peek());
			push(cardPlayed);
		}

		return dropSuccess;
	}
	
	public int cardCount()
	{
		return mPile.size();
	}
	
	public String toString()
	{
		return mPile.peek().toString();
	}
	
	public void setPointTracker(PointTracker pointTracker)
	{
		mPointTracker = pointTracker;
	}
}
