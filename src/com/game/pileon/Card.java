package com.game.pileon;

import android.graphics.Color;

/**
 * Card Interface
 * 
 * Interface for different types of cards that share the same function calls and
 * properties but don't share common code
 * 
 * @author nscross
 * @since 2012-08-22
 */
public interface Card
{

	/**
	 * Gets the color of the card
	 * 
	 * @return int, The color of the card
	 */
	public int getColor();

	/**
	 * Gets the value of the card
	 * 
	 * @return int, Value of the card, up to the class that inherits to define
	 *         range
	 * 
	 */
	public int getValue();

	/**
	 * Gets the behavior of the card
	 * 
	 * @return int, Behavior of the card, Depending on the card this will change
	 */
	public int getBehavior();
	
	/**
	 * Gets a string representation of the card for debugging
	 * 
	 * @return String, Color of the card and value
	 */
	public String toString();

}
