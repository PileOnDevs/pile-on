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

public class PileView extends ImageView implements DropTarget {
    private Context mContext;
    private Drawable mCardGraphic;
    private Pile mPile;
    private GameEngine mGameEngine;
    
    // default constructor, not used
    public PileView(Context context, Pile pile, GameEngine gameEngine) {
        this(context, null, 0, pile, gameEngine);
        
    }
    
    // default constructor, not used
    public PileView(Context context, AttributeSet attrs, Pile pile,
            GameEngine gameEngine) {
        this(context, attrs, 0, pile, gameEngine);
    }
    
    // constructor used in the game, requires the underlying Pile to be passed
    // in
    public PileView(Context context, AttributeSet attrs, int defStyle,
            Pile pile, GameEngine gameEngine) {
        super(context, attrs, defStyle);
        mContext = context;
        mGameEngine = gameEngine;
        mPile = pile;
        updateGraphic();
        
        // Log.i("PO CreateDeck", "pile LayoutParams width: "
        //        + getDrawable().getIntrinsicWidth() + " height: "
        //        + getDrawable().getIntrinsicHeight());
    }
    
    private void updateGraphic() {
        updateGraphic(mPile.peek().getCardID());
    }
    
    // general method to get the underlying card for the pile and show it
    private void updateGraphic(String cardID) {
        int pileImageResource = getDrawable(mContext, cardID);
        setImageResource(pileImageResource);
        mCardGraphic = mContext.getResources().getDrawable(pileImageResource);
        invalidate();
    }
    
    // this shows the graphic when you drag over a droppable card
    private void updateDropGraphic() {
        int pileImageResource = getDrawable(mContext, "grey"
                + mPile.peek().getValue());
        setImageResource(pileImageResource);
        mCardGraphic = mContext.getResources().getDrawable(pileImageResource);
        invalidate();
    }
    
    private int getDrawable(Context context, String name) {
        Assert.assertNotNull(context);
        Assert.assertNotNull(name);
        
        return context.getResources().getIdentifier(name, "drawable",
                context.getPackageName());
    }
    
    private Card getCardToBeDropped(Object dragObject) {
        HandView handView = (HandView) dragObject;
        Hand hand = handView.mHand;
        DefaultGameCard cardToBeDropped = (DefaultGameCard) hand.mCard;
        return cardToBeDropped;
    }
    
    private void tellHandToPlayCard(Object dragObject) {
        HandView handView = (HandView) dragObject;
        Hand hand = handView.mHand;
        if (hand.playCard() != null) {
            handView.updateGraphic();
        }
    }
    
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        mCardGraphic.draw(canvas);
        canvas.restore();
    }
    
    // see DropTarget for method descriptions for the onDrop/onDrag series
    public void onDrop(DragSource source, int x, int y, int xOffset,
            int yOffset, DragView dragView, Object dragInfo) {
        Assert.assertEquals(true,
                acceptDrop(source, x, y, xOffset, yOffset, dragView, dragInfo));
        
        Card cardToBeDropped = getCardToBeDropped(dragInfo);
        boolean dropSuccess = mPile.handleDrop(cardToBeDropped);
        
        if (dropSuccess) {
            updateGraphic(mPile.peek().getCardID());
            // Log.i("PO Drag", "invalidate graphic and redraw");
            tellHandToPlayCard(dragInfo);
            mGameEngine.isGameOver();
        } else {
            updateGraphic(mPile.peek().getCardID());
            // Log.i("PO Game", "this game is OVER (or the drop failed somehow)");
        }
    }
    
    public void onDragEnter(DragSource source, int x, int y, int xOffset,
            int yOffset, DragView dragView, Object dragInfo) {
        Card cardToBeDropped = getCardToBeDropped(dragInfo);
        Boolean checkIt = mPile.isMoveLegal(cardToBeDropped);
        // Log.i("PO Drag", "onDragEnter is move legal? " + checkIt.toString());
        
        if (checkIt) {
            updateDropGraphic();
        }
        // Log.i("PO Drag", "Drag enters pileview's airspace");
    }
    
    public void onDragOver(DragSource source, int x, int y, int xOffset,
            int yOffset, DragView dragView, Object dragInfo) {
    }
    
    public void onDragExit(DragSource source, int x, int y, int xOffset,
            int yOffset, DragView dragView, Object dragInfo) {
        updateGraphic();
        // Log.i("PO Drag", "Drag exited");
    }
    
    public boolean acceptDrop(DragSource source, int x, int y, int xOffset,
            int yOffset, DragView dragView, Object dragInfo) {
        Card cardToBeDropped = getCardToBeDropped(dragInfo);
        // Log.i("PO Drag", "card being dropped " + cardToBeDropped.toString());
        // Log.i("PO Drag", "card being dropped onto " + mPile.peek().toString());
        Boolean checkIt = mPile.isMoveLegal(cardToBeDropped);
        // Log.i("PO Drag", "is move legal? " + checkIt.toString());
        return checkIt;
    }
    
    public Rect estimateDropLocation(DragSource source, int x, int y,
            int xOffset, int yOffset, DragView dragView, Object dragInfo,
            Rect recycle) {
        return null;
    }
    
    @Override
    public String toString() {
        return mPile.toString();
    }
    
}
