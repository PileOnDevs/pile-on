package com.game.pileon;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.widget.ImageView;

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

		public cardDragShadowBuilder(View v) 
		{
			super(v);
			shadow = new ColorDrawable(Color.LTGRAY);
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
			shadow.draw(canvas);
		}


	}
	private static class cardTouchEventListener implements View.OnTouchListener
	{
		public boolean onTouch(View v, MotionEvent event) 
		{
			switch(event.getActionMasked()){
				case MotionEvent.ACTION_DOWN:
					ClipData dragData = ClipData.newPlainText("", "");
					DragShadowBuilder dsb = new cardDragShadowBuilder(v);
					v.startDrag(dragData, dsb, null, 0);
					v.setVisibility(View.INVISIBLE);
					break;
				case MotionEvent.ACTION_CANCEL:
					v.setVisibility(View.VISIBLE);
					break;
			}
			
			return false;
		}
	}
}