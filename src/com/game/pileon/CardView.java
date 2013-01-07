/**
 * 
 */
package com.game.pileon;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * @author breeze4
 * TODO I think this class can be deleted, not sure yet. HandView, DeckView and PileView do all the hard work as far as views go..
 */
public class CardView extends ImageView
{
	public Card mCard; //stores the underlying Card that this CardView represents
	private Drawable mCardGraphic;
	private Context mContext;

	/**
	 * @param context
	 */
	public CardView(Context context)
	{
		this(context, null, 0);
		// default constructor, not used
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public CardView(Context context, AttributeSet attrs)
	{
		this(context, attrs, 0);
		// default constructor, not used
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public CardView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		mContext = context;
		mCardGraphic = mContext.getResources().getDrawable(R.drawable.card_hand);
		mCardGraphic.setBounds(13, 13, mCardGraphic.getIntrinsicWidth()+13, mCardGraphic.getIntrinsicHeight()+13);
	}
	
	public void setCard(Card card)
	{
		mCard = card;
	}
}
