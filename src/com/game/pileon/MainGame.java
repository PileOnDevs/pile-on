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
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

@TargetApi(11)
public class MainGame extends Activity
implements View.OnTouchListener
{

	private GameType mGameType;
	private DragController mDragController;   // Object that sends out drag-drop events while a view is being moved.
	private DragLayer mDragLayer;             // The ViewGroup that supports drag-drop.

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		mGameType = new DefaultGameType();
		setContentView(R.layout.activity_game_screen);
		mDragController = new DragController(this);

	    mDragLayer = (DragLayer) findViewById(R.id.drag_layer);
	    mDragLayer.setDragController(mDragController);
	    
	    PileView pile0 = (PileView)findViewById(R.id.pile0);
	    mDragController.addDropTarget(pile0);
	    

		CardView hand0 = (CardView)findViewById(R.id.hand0);
		//cardTouchEventListener mTouchListen = new cardTouchEventListener();
		hand0.setOnTouchListener(this);

		//		ImageView hand1 = (ImageView)findViewById(R.id.hand1);
		//		ImageView hand2 = (ImageView)findViewById(R.id.hand2);
		//		ImageView hand3 = (ImageView)findViewById(R.id.hand3);
		//		ImageView hand4 = (ImageView)findViewById(R.id.hand4);
		//		cardTouchEventListener mTouchListen = new cardTouchEventListener();
		//		hand0.setOnTouchListener(mTouchListen);
		//		hand1.setOnTouchListener(mTouchListen);
		//		hand2.setOnTouchListener(mTouchListen);
		//		hand3.setOnTouchListener(mTouchListen);
		//		hand4.setOnTouchListener(mTouchListen);
		//
		//		ImageView pile0 = (ImageView)findViewById(R.id.pile0);
		//		ImageView pile1 = (ImageView)findViewById(R.id.pile1);
		//		ImageView pile2 = (ImageView)findViewById(R.id.pile2);
		//		cardDragEventListener mDragListen = new cardDragEventListener();
		//		pile0.setOnDragListener(mDragListen);
		//		pile1.setOnDragListener(mDragListen);
		//		pile2.setOnDragListener(mDragListen);

	}

	public void backToMain(View view)
	{
		Intent intent = new Intent(MainGame.this, GameMenu.class);
		startActivity(intent);
	}
	
	public boolean onTouch(View v, MotionEvent ev) 
	{
	    final int action = ev.getAction();

	    if (action == MotionEvent.ACTION_DOWN) {
	       startDrag(v);
	    }
	    
	    return true;
	}
	
	/**
	 * Start dragging a view.
	 *
	 */    

	public boolean startDrag (View v)
	{
	    // Let the DragController initiate a drag-drop sequence.
	    // I use the dragInfo to pass along the object being dragged.
	    // I'm not sure how the Launcher designers do this.
	    Object dragInfo = v;
	    Log.i("PO Drag", "startDrag in MainGame runs");
	    mDragController.startDrag (v, mDragLayer, dragInfo, DragController.DRAG_ACTION_MOVE);
	    return true;
	}
	
	protected void onDestroy()
	{
		super.onDestroy();
	}



}