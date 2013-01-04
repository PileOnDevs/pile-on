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
 * @since 2013-01-03
 *
 */
public class HandView extends ImageView
{
	private Hand mHand; //stores the underlying Hand that this HandView represents
	private Drawable mCardGraphic;
	private Context mContext;

	/**
	 * @param context
	 */
	public HandView(Context context)
	{
		this(context, null, 0);
		// default constructor, not used
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public HandView(Context context, AttributeSet attrs)
	{
		this(context, attrs, 0);
		// default constructor, not used
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public HandView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		mContext = context;
		mCardGraphic = mContext.getResources().getDrawable(R.drawable.card_hand);
		mCardGraphic.setBounds(0, 0, mCardGraphic.getIntrinsicWidth(), mCardGraphic.getIntrinsicHeight());
	}
}
