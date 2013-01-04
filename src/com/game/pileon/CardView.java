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
 *
 */
public class CardView extends ImageView
{
	private Card mCard; //stores the underlying Card that this CardView represents
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
}
