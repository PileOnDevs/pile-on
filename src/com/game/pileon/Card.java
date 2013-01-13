package com.game.pileon;


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
	 * @return String, The color of the card
	 */
	public String getColor();

	/**
	 * Gets the value of the card
	 * 
	 * @return int, Value of the card, up to the class that inherits to define
	 *         range
	 * 
	 */
	public int getValue();
	
	/**
	 * Gets the ID of the card
	 * 
	 * @return String, ID of the card
	 * 
	 */
	public String getCardID();

	/**
	 * Gets the behavior of the card
	 * 
	 * @return int, Behavior of the card, Depending on the card this will change
	 */
	public int getBehavior();
	
	/**
	 * Determines if the value of the cards are the same
	 * 
	 * @return true if cards have equal value
	 */
	public boolean equalValueTo(Card cardToCompare);
	
	/**
	 * Determines if the color of the cards are the same
	 * 
	 * @return true if cards have the same color
	 */
	public boolean equalColorTo(Card cardToCompare);
	
	/**
	 * Gets a string representation of the card for debugging
	 * 
	 * @return String, Color of the card and value
	 */
	public String toString();

}
