package com.game.pileon;

public class DefaultGameCard implements Card
{

	private String mColor; 
	private int mValue;
	private int mBehavior;
	private String mCardID;

	public DefaultGameCard()
	{
		this("",0,"card_pile");
	}

	public DefaultGameCard(String colorToSet, int valueToSet, String cardID)
	{
		mColor = colorToSet;
		mValue = valueToSet;
		mCardID = cardID;
	}

	public String getColor()
	{

		return mColor;
	}

	public int getValue()
	{
		return mValue;
	}

	public int getBehavior()
	{
		return mBehavior;
	}
	public String getCardID()
	{
		return mCardID;
	}
	public String toString()
	{
		return "Card ID: " + mCardID + " Color: " + mColor + " Value: " + mValue;
	}

	public boolean equalValueTo(Card cardToCompare)
	{
		return cardToCompare.getValue() == mValue;
	}

	public boolean equalColorTo(Card cardToCompare)
	{
		return cardToCompare.getColor() == mColor;
	}
	
	public boolean isPlaceholder()
	{
		return mCardID == "card_pile";
	}

}
