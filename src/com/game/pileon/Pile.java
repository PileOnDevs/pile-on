package com.game.pileon;

import java.util.Stack;

/**
 * Pile class
 * 
 * Wrapper class for a Stack that adds some additional game-specific functionality and coordination with PileView
 * 
 * @author breeze4
 * @since 2012-12-30
 */

public class Pile
{
	private Stack<Card> mPile;
	
	public Pile(Card card){
		mPile = new Stack<Card>();
		
		mPile.push(card);
		
		
	}
	//Wrapper methods for the underlying stack, push and pop are private to private
	//unauthorized changes to the pile without appropriate graphical updating and such
	/**
	 * Tests if this stack is empty
	 * @return true if and only if this stack contains no items; false otherwise
	 */
	public boolean isEmpty(){
		return !(mPile.size() != 0);
	}
	
	/**
	 * Looks at the object at the top of the stack without removing it from the stack
	 * @return the object at the top of the stack (the last item of the Vector object)
	 */
	public Card peek(){
		return mPile.peek();
	}
	
	/**
	 * Removes the object at the top of the stack and returns that object as the value of this function
	 * @return The object at the top of this stack (the last item of the Vector object)
	 */
	private Card pop(){
		return mPile.pop();
	}
	
	/**
	 * Pushes an item onto the top of this stack
	 * @return true if the item pushed is at the top of the stack now
	 */
	private boolean push(Card card){
		mPile.push(card);
		return card.equals(mPile.peek());
	}
	
	//Game specific methods
	/**
	 * Determines whether or not the card can be played to the pile based on color or value
	 * @return true if the move is legal (either the colors match or the values match)
	 */
	public boolean isMoveLegal(Card cardPlayed){
		if (cardPlayed == null){
			return false;
		}
		else if (cardPlayed.equalValueTo(peek())){
			return true;
		}
		else if (cardPlayed.equalColorTo(peek())){
			return true;
		}
		return false;
	}
	
	/**
	 * Computes the point gain by playing the card to the pile
	 * @return points gained by drop
	 */
	public int handleDrop(Card cardPlayed){
		int pointsGained = 0;
		if (cardPlayed.equalValueTo(peek())){
			pointsGained = 2*cardPlayed.getValue();
		}
		else if (cardPlayed.equalColorTo(peek())){
			pointsGained = 2*cardPlayed.getValue();
		}
		return pointsGained;
	}
}
