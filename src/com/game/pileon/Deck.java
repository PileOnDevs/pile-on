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
    private LinkedList<Card> DeckOfCards;
    
    public Deck() {
        DeckOfCards = new LinkedList<Card>();
        String cardID = "";
        for (int colorNdx = 0; colorNdx < GameEngine.NUMBEROFCOLORS; colorNdx++) {
            for (int cardNdx = 0; cardNdx < GameEngine.NUMBEROFCARDSPERCOLOR; cardNdx++) {
                cardID = GameEngine.CARDCOLORS[colorNdx] + Integer.toString(cardNdx + 1);
                DefaultGameCard cardToAdd = new DefaultGameCard(
                        GameEngine.CARDCOLORS[colorNdx], cardNdx + 1, cardID);
                addCard(cardToAdd);
                Log.i("PO CreateDeck", cardToAdd.toString());
            }
        }
    }
    
    public void addCard(Card cardToAdd) {
        if (DeckOfCards != null) {
            DeckOfCards.add(cardToAdd);
        }
    }
    
    public Card dealTopCard() {
        if (DeckOfCards.size() > 0) {
            return DeckOfCards.remove();
        } else {
            return new DefaultGameCard();
        }
    }
    
    public boolean isEmpty() {
        return DeckOfCards.isEmpty();
    }
    
    // shuffle deck using the Knuth shuffle
    public void Shuffle() {
        if ((DeckOfCards != null) && (DeckOfCards.size() > 0)) {
            Random randomGenerator = new Random();
            
            int howManyTimesToShuffle = DeckOfCards.size();
            int howManyCardsInDeck = DeckOfCards.size(); // save this now, it's
            // not going to
            // change
            
            for (int shuffleCount = 0; shuffleCount < howManyCardsInDeck; shuffleCount++) {
                int whichCardToMove = (randomGenerator
                        .nextInt(howManyTimesToShuffle));
                DefaultGameCard tempCard = (DefaultGameCard) DeckOfCards
                        .remove(whichCardToMove);
                DeckOfCards.addLast(tempCard);
                Log.i("PO Shuffle", tempCard.toString());
                howManyTimesToShuffle--;
            }
        }
        
    }
    
    public void addToEnd(Card card) {
        DeckOfCards.addLast(card);
    }
    
    @Override
    public String toString() {
        Iterator<Card> ListIter = DeckOfCards.iterator();
        String deckString = new String();
        while (ListIter.hasNext()) {
            deckString = deckString + " " + ListIter.next().toString();
            
        }
        return deckString;
    }
    
}
