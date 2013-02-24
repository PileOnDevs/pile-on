package com.game.pileon;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Hand class
 * 
 * Holds a single Card in your hand
 * 
 * @author breeze4
 * @since 2013-01-03
 */

@Root(name = "Hand")
public class Hand {
    @Element(name = "HandCard")
    public Card mCard;
    private Deck mDeck;
    private GameEngine mGameEngine;
    
    public Hand(@Element(name = "HandCard") Card card) {
        mCard = card;
    }
    
    // Wrapper methods for the underlying stack, push and pop are private to
    // prevent
    // unauthorized changes to the pile without appropriate graphical updating
    // and such
    /**
     * Tests if this stack is empty
     * 
     * @return true if and only if this stack contains no items; false otherwise
     */
    public boolean isEmpty() {
        return (mCard == null);
    }
    
    /**
     * Sends the Card this Hand is holding to the game for play and asks for
     * another
     * 
     * @return the Card this hand is holding
     */
    public Card playCard() {
        Card oldCard = mCard;
        getCardFromDeck();
        // //Log.i("PO Hand", "oldCard: " + oldCard.toString() + " newCard: "
        // + newCard.toString());
        return oldCard; // TODO this returns the old card, but not used right
                        // now
    }
    
    public Card getCardFromDeck() {
        mCard = mGameEngine.dealTopCard();
        return mCard;
    }
    
    @Override
    public String toString() {
        return mCard.toString();
    }
    
    public void setHand(Card card) {
        mCard = card;
    }
    
    public void setDeck(Deck deck) {
        mDeck = deck;
    }
    
    public void setGameEngine(GameEngine gameEngine) {
        mGameEngine = gameEngine;
    }
    
}
