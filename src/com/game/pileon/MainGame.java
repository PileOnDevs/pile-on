package com.game.pileon;


import junit.framework.Assert;
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
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
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

	private GameEngine mGameEngine;
	private DragController mDragController;   // Object that sends out drag-drop events while a view is being moved.
	private DragLayer mDragLayer;             // The ViewGroup that supports drag-drop.
    private static Context mContext;


	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		mGameEngine = new GameEngine();
		MainGame.mContext = getApplicationContext();
		
		setContentView(R.layout.activity_game_screen);
		mDragController = new DragController(this);
		
		mDragLayer = (DragLayer) findViewById(R.id.drag_layer);
	    mDragLayer.setDragController(mDragController);
	    
	    setupViews();

	}
	/**
	 * One-time setup of initial PileViews and HandViews. After this is done, the GameEngine will update
	 * the PileViews and HandViews with the underlying card(s) as the game progresses
	 */
	public void setupViews()
	{
		//setup PileViews - three each
		PileView pileView0 = new PileView(this, mGameEngine.Pile0);		
	    //sizing the PileView
	    DragLayer.LayoutParams pileView0params = new DragLayer.LayoutParams(pileView0.getDrawable().getIntrinsicWidth(), 
	    		pileView0.getDrawable().getIntrinsicHeight(), 120, 320);
	    mDragLayer.addView(pileView0, pileView0params);
	    mDragController.addDropTarget(pileView0);

	    //setup HandViews - five each
	    HandView handView0 = new HandView(this, mGameEngine.Hand0);
		handView0.setOnTouchListener(this);
	    Log.i("PO CreateDeck", "handView0 LayoutParams width: " + handView0.getDrawable().getIntrinsicWidth() +
	    		" height: "+ handView0.getDrawable().getIntrinsicHeight());	    
	    DragLayer.LayoutParams handView0params = new DragLayer.LayoutParams(handView0.getDrawable().getIntrinsicWidth(), 
	    		handView0.getDrawable().getIntrinsicHeight(), 120, 120);
	    
	    mDragLayer.addView(handView0, handView0params);
		handView0.setHand(mGameEngine.Hand0);
	}
	
	public int getDrawable(Context context, String name)
	{
		Assert.assertNotNull(context);
		Assert.assertNotNull(name);
		
		return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
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
	
	public static Context getAppContext()
	{
		return MainGame.mContext;
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