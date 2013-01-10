/**
 * 
 */
package com.game.pileon;

import junit.framework.Assert;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * @author breeze4
 *
 */
public class PileView extends ImageView
implements DropTarget
{
	private Context mContext;
	private Drawable mCardGraphic;
	private Pile mPile;
	
	/**
	 * @param context
	 */
	public PileView(Context context, Pile pile)
	{
		this(context, null, 0, pile);
		// default constructor, not used
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public PileView(Context context, AttributeSet attrs, Pile pile)
	{
		this(context, attrs, 0, pile);
		// default constructor, not used
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public PileView(Context context, AttributeSet attrs, int defStyle, Pile pile)
	{
		super(context, attrs, defStyle);
		mContext = context;
		setPile(pile);
	    int pileImageResource = getDrawable(mContext, mPile.peek().getCardID());
	    setImageResource(pileImageResource);
		mCardGraphic = context.getResources().getDrawable(pileImageResource);

	    Log.i("PO CreateDeck", "pile LayoutParams width: " + getDrawable().getIntrinsicWidth() +
	    		" height: "+ getDrawable().getIntrinsicHeight());
//		mCardGraphic.setBounds(0, 0, mCardGraphic.getIntrinsicWidth(), mCardGraphic.getIntrinsicHeight());
	}
	
	public int getDrawable(Context context, String name)
	{
		Assert.assertNotNull(context);
		Assert.assertNotNull(name);
		
		return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
	}
	
	@Override
	public void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		canvas.save();
		mCardGraphic.draw(canvas);
		canvas.restore();

	}
	
	/* (non-Javadoc)
	 * @see com.game.pileon.DropTarget#onDrop(com.game.pileon.DragSource, int, int, int, int, com.game.pileon.DragView, java.lang.Object)
	 */
	public void onDrop(DragSource source, int x, int y, int xOffset,
			int yOffset, DragView dragView, Object dragInfo)
	{
		
		Log.i("PO Drag", "invalidate graphic and redraw");
	}

	/* (non-Javadoc)
	 * @see com.game.pileon.DropTarget#onDragEnter(com.game.pileon.DragSource, int, int, int, int, com.game.pileon.DragView, java.lang.Object)
	 */
	public void onDragEnter(DragSource source, int x, int y, int xOffset,
			int yOffset, DragView dragView, Object dragInfo)
	{
		mCardGraphic = mContext.getResources().getDrawable(R.drawable.card_pile_drag_over);
		mCardGraphic.setBounds(0, 0, mCardGraphic.getIntrinsicWidth(), mCardGraphic.getIntrinsicHeight());
		invalidate();
		Log.i("PO Drag", "Drag enters pileview's airspace");
	}

	/* (non-Javadoc)
	 * @see com.game.pileon.DropTarget#onDragOver(com.game.pileon.DragSource, int, int, int, int, com.game.pileon.DragView, java.lang.Object)
	 */
	public void onDragOver(DragSource source, int x, int y, int xOffset,
			int yOffset, DragView dragView, Object dragInfo)
	{
		//Log.i("PO Drag", "Drag enters pileview's airspace");
	}

	/* (non-Javadoc)
	 * @see com.game.pileon.DropTarget#onDragExit(com.game.pileon.DragSource, int, int, int, int, com.game.pileon.DragView, java.lang.Object)
	 */
	public void onDragExit(DragSource source, int x, int y, int xOffset,
			int yOffset, DragView dragView, Object dragInfo)
	{
		mCardGraphic = mContext.getResources().getDrawable(R.drawable.card_pile);
		mCardGraphic.setBounds(0, 0, mCardGraphic.getIntrinsicWidth(), mCardGraphic.getIntrinsicHeight());
		invalidate();
		Log.i("PO Drag", "Drag left");
	}

	/* (non-Javadoc)
	 * @see com.game.pileon.DropTarget#acceptDrop(com.game.pileon.DragSource, int, int, int, int, com.game.pileon.DragView, java.lang.Object)
	 */
	public boolean acceptDrop(DragSource source, int x, int y, int xOffset,
			int yOffset, DragView dragView, Object dragInfo)
	{
		HandView handView = (HandView)dragInfo;
		Hand hand = handView.mHand;
		DefaultGameCard cardToBeDropped = (DefaultGameCard)hand.mCard;
		Log.i("PO Drag", "card being dropped " + cardToBeDropped.toString());
		Log.i("PO Drag", "card being dropped onto " + mPile.peek().toString());
		Boolean checkIt = mPile.isMoveLegal(cardToBeDropped);
		Log.i("PO Drag", "is move legal? " + checkIt.toString());
		return true;
	}

	/* (non-Javadoc)
	 * @see com.game.pileon.DropTarget#estimateDropLocation(com.game.pileon.DragSource, int, int, int, int, com.game.pileon.DragView, java.lang.Object, android.graphics.Rect)
	 */
	public Rect estimateDropLocation(DragSource source, int x, int y,
			int xOffset, int yOffset, DragView dragView, Object dragInfo,
			Rect recycle)
	{
		return null;
	}
	
	public void setPile(Pile pile)
	{
		mPile = pile;
	}


}
