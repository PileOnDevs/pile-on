package com.game.pileon;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.widget.ImageView;
import android.widget.Toast;

@TargetApi(11)
public class MainGame extends Activity
{

	private GameType mGameType;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_screen);
		mGameType = new DefaultGameType();

		ImageView hand0 = (ImageView)findViewById(R.id.hand0);
		ImageView hand1 = (ImageView)findViewById(R.id.hand1);
		ImageView hand2 = (ImageView)findViewById(R.id.hand2);
		ImageView hand3 = (ImageView)findViewById(R.id.hand3);
		ImageView hand4 = (ImageView)findViewById(R.id.hand4);
		cardTouchEventListener mTouchListen = new cardTouchEventListener();
		hand0.setOnTouchListener(mTouchListen);
		hand1.setOnTouchListener(mTouchListen);
		hand2.setOnTouchListener(mTouchListen);
		hand3.setOnTouchListener(mTouchListen);
		hand4.setOnTouchListener(mTouchListen);

		ImageView pile0 = (ImageView)findViewById(R.id.pile0);
		ImageView pile1 = (ImageView)findViewById(R.id.pile1);
		ImageView pile2 = (ImageView)findViewById(R.id.pile2);
		cardDragEventListener mDragListen = new cardDragEventListener();
		pile0.setOnDragListener(mDragListen);
		pile1.setOnDragListener(mDragListen);
		pile2.setOnDragListener(mDragListen);

	}

	public void backToMain(View view)
	{
		Intent intent = new Intent(MainGame.this, GameMenu.class);
		startActivity(intent);
	}

	protected void onDestroy()
	{
		super.onDestroy();
	}


	private static class cardDragShadowBuilder extends View.DragShadowBuilder 
	{
		private static Drawable shadow;
		private static ImageView draggedCard;

		public cardDragShadowBuilder(View v) 
		{
			super(v);
			shadow = new ColorDrawable(Color.LTGRAY);
			draggedCard = (ImageView)v;
			draggedCard.setImageResource(R.drawable.card_hand_drag);
			draggedCard.invalidate();
		}

		@Override
		public void onProvideShadowMetrics (Point size, Point touch)
		{
			int width = getView().getWidth();
			int height = getView().getHeight();

			shadow.setBounds(0, 0, width, height);
			size.set(width, height);
			touch.set(width / 2, height / 2);
		}

		@Override
		public void onDrawShadow(Canvas canvas) 
		{
			//shadow.draw(canvas);
			draggedCard.setImageResource(R.drawable.card_hand_drag);
			draggedCard.invalidate();
		}


	}
	private static class cardTouchEventListener implements View.OnTouchListener
	{
		public boolean onTouch(View v, MotionEvent event) 
		{
			switch(event.getActionMasked()){
			case MotionEvent.ACTION_DOWN:
				ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());
				String[] clipDescription = {ClipDescription.MIMETYPE_TEXT_PLAIN};
				ClipData dragData = new ClipData((CharSequence)v.getTag(), clipDescription, item);					
				DragShadowBuilder dsb = new cardDragShadowBuilder(v);
				v.startDrag(dragData, dsb, v, 0);
				v.setVisibility(View.INVISIBLE);
				return true;
			}

			return false;
		}
	}
	private static class cardDragEventListener implements View.OnDragListener
	{
		public boolean onDrag(View v, DragEvent event) 
		{
			int dragAction = event.getAction();

			if (dragAction == DragEvent.ACTION_DRAG_ENTERED)
			{
				Log.d("PO DRAG", "Entered drop box");
				
			} 
			else if (dragAction == DragEvent.ACTION_DRAG_EXITED)
			{
				Log.d("PO DRAG", "Exited drop box");
			} 
			else if (dragAction == DragEvent.ACTION_DRAG_STARTED)
			{			
				Log.d("PO DRAG", "Started drag");
			}
			else if (dragAction == DragEvent.ACTION_DRAG_ENDED)
			{
				Log.d("PO DRAG", "Ended drag");
			}
			else if (dragAction == DragEvent.ACTION_DROP)
			{
				Log.d("PO DRAG", "Dropped");
				ClipData.Item item = event.getClipData().getItemAt(0);

			}
			else
			{
				return false;
			}
			return true;
		}

		private boolean dropEventNotHandled(DragEvent event)
		{
			return !event.getResult();
		}
	}
}