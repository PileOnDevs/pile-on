package com.game.pileon;

import android.graphics.Color;

public class DefaultGameCard implements Card
{

	private int mColor;
	private int mValue;
	private int mBehavior;

	public DefaultGameCard()
	{

	}

	public DefaultGameCard(int colorToSet, int valueToSet)
	{
		mColor = colorToSet;
		mValue = valueToSet;
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
	public String toString()
	{
		return "Color:" + mColor + " Value:" + mValue;
	}

}
