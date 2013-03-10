package com.game.pileon;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import android.util.Log;

/**
 * 
 * @author nscross, breeze4
 * 
 */
@Root
public class Deck {
    
    @ElementList
    private LinkedList<Card> deckOfCards;
    
    public Deck() {
        deckOfCards = new LinkedList<Card>();
        String cardID = "";
        for (int colorNdx = 0; colorNdx < GameEngine.NUMBEROFCOLORS; colorNdx++) {
            for (int cardNdx = 0; cardNdx < GameEngine.NUMBEROFCARDSPERCOLOR; cardNdx++) {
                cardID = GameEngine.CARDCOLORS[colorNdx] + Integer.toString(cardNdx + 1);
                DefaultGameCard cardToAdd = new DefaultGameCard(
                        GameEngine.CARDCOLORS[colorNdx], cardNdx + 1, cardID);
                addCard(cardToAdd);
                // Log.i("PO CreateDeck", cardToAdd.toString());
            }
        }
    }
    
    public void addCard(Card cardToAdd) {
        if (deckOfCards != null) {
            deckOfCards.add(cardToAdd);
        }
    }
    
    public Card dealTopCard() {
        if (deckOfCards.size() > 0) {
            return deckOfCards.remove();
        } else {
            return new DefaultGameCard();
        }
    }
    
    public boolean isEmpty() {
        return deckOfCards.isEmpty();
    }
    
    // shuffle deck using the Knuth shuffle
    public void Shuffle() {
        if ((deckOfCards != null) && (deckOfCards.size() > 0)) {
            Random randomGenerator = new Random();
            
            int howManyTimesToShuffle = deckOfCards.size();
            int howManyCardsInDeck = deckOfCards.size(); // save this now, it's
            // not going to
            // change
            
            for (int shuffleCount = 0; shuffleCount < howManyCardsInDeck; shuffleCount++) {
                int whichCardToMove = (randomGenerator
                        .nextInt(howManyTimesToShuffle));
                DefaultGameCard tempCard = (DefaultGameCard) deckOfCards
                        .remove(whichCardToMove);
                deckOfCards.addLast(tempCard);
                // Log.i("PO Shuffle", tempCard.toString());
                howManyTimesToShuffle--;
            }
        }
        
    }
    
    public void addToEnd(Card card) {
        deckOfCards.addLast(card);
    }
    
    @Override
    public String toString() {
        String deckString = new String();
        for(Card c : deckOfCards)
            deckString = deckString + " " + c.toString();
        return deckString;
    }
    
}
