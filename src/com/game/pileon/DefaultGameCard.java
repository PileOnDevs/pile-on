package com.game.pileon;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="root")
public class DefaultGameCard implements Card
{
	
	@Element(name="mColor", required=false)
	private String mColor;
	@Attribute(name="mValue")
	private int mValue;
	private int mBehavior;
	@Element(name="mCardID")
	private String mCardID;

	public DefaultGameCard()
	{
		this("",0,"blank");
	}

	public DefaultGameCard( @Element(name="mColor")String colorToSet, @Attribute(name="mValue")int valueToSet, @Element(name="mCardID")String cardID)
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
		return "Card ID: " + mCardID;
		//+ " Color: " + mColor + " Value: " + mValue;
	}

	public boolean equalValueTo(Card cardToCompare)
	{
		return cardToCompare.getValue() == mValue;
	}

	public boolean equalColorTo(Card cardToCompare)
	{		
		return cardToCompare.getColor().equals(mColor);
	}
	
	public boolean isPlaceholder()
	{
		return mCardID.equals("blank");
	}

}
