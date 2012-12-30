package com.game.pileon;

import android.graphics.Color;

public class DefaultGameCard implements Card
{

	private int mColor;
	private int mValue;
	private int mBehavior;
	private int mCardID;

	public DefaultGameCard()
	{
		this(0,0,0);
	}

	public DefaultGameCard(int colorToSet, int valueToSet, int cardID)
	{
		mColor = colorToSet;
		mValue = valueToSet;
		mCardID = cardID;
	}

	public int getColor()
	{

		return mColor;
	}

	public int getValue()
	{
		//
		return mValue;
	}

	public int getBehavior()
	{
		// TODO Auto-generated method stub
		return mBehavior;
	}
	public int getCardID()
	{
		return mCardID;
	}
	public String toString()
	{
		return "Card ID: " + mCardID + " Color: " + mColor + " Value: " + mValue;
	}

}
