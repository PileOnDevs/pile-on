/**
 * 
 */
package com.game.pileon;


import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.widget.ImageView;

/**
 * @author breeze4
 *
 */
public class CardView extends ImageView
{
	private int mCardID; //stores the ID number of the card this CardView is displaying at any given time
	private Drawable mCardGraphic;
	private float mPosX;
	private float mPosY;
	private Context mContext;

	private float mLastTouchX;
	private float mLastTouchY;

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
		

		// default constructor, not used
	}
	//	public CardView(Context context, AttributeSet attrs, int defStyle, Card card)
	//	{
	//		super(context, attrs, defStyle);
	//		mCardGraphic = context.getResources().getDrawable(R.drawable.card_hand);
	//		mCardGraphic.setBounds(0, 0, mCardGraphic.getIntrinsicWidth(), mCardGraphic.getIntrinsicHeight());
	//		mCard = card;
	//		// specific constructor for CardView that associates a Card up with a graphic
	//	}

//	@Override
//	public void onDraw(Canvas canvas)
//	{
//		super.onDraw(canvas);
//		canvas.save();
//		canvas.translate(mPosX, mPosY);
//		mCardGraphic.draw(canvas);
//		canvas.restore();
//
//	}
//
//	@Override
//	public boolean onTouchEvent(MotionEvent event) 
//	{
//		final int action = event.getAction();
//		switch (action) 
//		{
//		case MotionEvent.ACTION_DOWN: 
//		{
//			final float x = event.getX();
//			final float y = event.getY();
//
//			// Remember where we started
//			mLastTouchX = x;
//			mLastTouchY = y;
//			break;
//		}
//
//		case MotionEvent.ACTION_MOVE: 
//		{
//			final float x = event.getX();
//			final float y = event.getY();
//
//			// Calculate the distance moved
//			final float dx = x - mLastTouchX;
//			final float dy = y - mLastTouchY;
//
//			// Move the object
//			mPosX += dx;
//			mPosY += dy;
//
//			// Remember this touch position for the next move event
//			mLastTouchX = x;
//			mLastTouchY = y;
//
//			// Assign the dragging graphic
//			mCardGraphic = mContext.getResources().getDrawable(R.drawable.card_hand_drag);
//			mCardGraphic.setBounds(13, 13, mCardGraphic.getIntrinsicWidth()+13, mCardGraphic.getIntrinsicHeight()+13);
//			Log.d("PO CARD", "Setting width: " + mCardGraphic.getIntrinsicWidth() + " Setting height: " + mCardGraphic.getIntrinsicHeight());
//
//
//			// Invalidate to request a redraw
//			invalidate();
//			break;
//		}
//		}
//
//		return true;
//	}
	
//	private static class cardDragShadowBuilder extends View.DragShadowBuilder 
//	{
//		private static Drawable shadow;
//		private static CardView draggedCard;
//
//		public cardDragShadowBuilder(View v) 
//		{
//			super(v);
//			shadow = new ColorDrawable(Color.LTGRAY);
//			draggedCard = (CardView)v;
//			draggedCard.setImageResource(R.drawable.card_hand_drag);
//			draggedCard.invalidate();
//		}
//
//		@Override
//		public void onProvideShadowMetrics (Point size, Point touch)
//		{
//			int width = getView().getWidth();
//			int height = getView().getHeight();
//
//			shadow.setBounds(0, 0, width, height);
//			size.set(width, height);
//			touch.set(width / 2, height / 2);
//		}
//
//		@Override
//		public void onDrawShadow(Canvas canvas) 
//		{
//			//shadow.draw(canvas);
//			draggedCard.setImageResource(R.drawable.card_hand_drag);
//			draggedCard.invalidate();
//		}
//
//
//	}
//	private static class cardTouchEventListener implements View.OnTouchListener
//	{
//		public boolean onTouch(View v, MotionEvent event) 
//		{
//			switch(event.getActionMasked()){
//			case MotionEvent.ACTION_DOWN:
//				ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());
//				String[] clipDescription = {ClipDescription.MIMETYPE_TEXT_PLAIN};
//				ClipData dragData = new ClipData((CharSequence)v.getTag(), clipDescription, item);					
//				DragShadowBuilder dsb = new cardDragShadowBuilder(v);
//				v.startDrag(dragData, dsb, v, 0);
//				v.setVisibility(View.INVISIBLE);
//				return true;
//			}
//
//			return false;
//		}
//	}
//	private static class cardDragEventListener implements View.OnDragListener
//	{
//		public boolean onDrag(View v, DragEvent event) 
//		{
//			int dragAction = event.getAction();
//
//			if (dragAction == DragEvent.ACTION_DRAG_ENTERED)
//			{
//				Log.d("PO DRAG", "Entered drop box");
//
//			} 
//			else if (dragAction == DragEvent.ACTION_DRAG_EXITED)
//			{
//				Log.d("PO DRAG", "Exited drop box");
//			} 
//			else if (dragAction == DragEvent.ACTION_DRAG_STARTED)
//			{			
//				Log.d("PO DRAG", "Started drag");
//			}
//			else if (dragAction == DragEvent.ACTION_DRAG_ENDED)
//			{
//				Log.d("PO DRAG", "Ended drag");
//			}
//			else if (dragAction == DragEvent.ACTION_DROP)
//			{
//				Log.d("PO DRAG", "Dropped");
//				ClipData.Item item = event.getClipData().getItemAt(0);
//
//			}
//			else
//			{
//				return false;
//			}
//			return true;
//		}
//
//		private boolean dropEventNotHandled(DragEvent event)
//		{
//			return !event.getResult();
//		}
//	}
}
