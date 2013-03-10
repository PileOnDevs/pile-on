package com.game.pileon;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

/**
 * GameEngine
 * 
 * This is the default game. 4 colors (R G B Y) and ten cards of each (1 through
 * 10)
 * 
 * @author nscross, breeze4
 * 
 */
public class GameEngine {
    
    public Deck Deck;
    
    public final static int NUMBEROFCOLORS = 4;
    public final static int NUMBEROFCARDSPERCOLOR = 10;
    public final static int NUMBEROFPILES = 3;
    public final static int NUMBEROFHANDS = 5;
    public final static int STARTINGTIMEBONUS = 10000;
    public final static int STARTINGBONUSMULTIPLIER = 1;
    public final static String CARDCOLORS[] = { "red", "green", "blue",
            "yellow" };
    
    public boolean isGameOver = false;
    
    private PointTracker mPointTracker;
    private ArrayList<Pile> Piles;
    private ArrayList<Hand> Hands;
    
    // default constructor in case of a new game
    public GameEngine() {
        createDeck();
        setupGame();
        addPlaceholderCardsToDeck();
        isGameOver = false;
    }
    
    // constructor called if a game in progress is being continued
    public GameEngine(SavedGame savedGameState) {
        Deck = savedGameState.saveDeck;
        Piles = new ArrayList<Pile>();
        
        for (int pileNdx = 0; pileNdx < NUMBEROFPILES; pileNdx++) {
            Piles.add(pileNdx, savedGameState.savePiles.get(pileNdx));
        }
        Hands = new ArrayList<Hand>();
        for (int handNdx = 0; handNdx < NUMBEROFHANDS; handNdx++) {
            Hands.add(handNdx, savedGameState.saveHands.get(handNdx));
            Hands.get(handNdx).setDeck(Deck);
            Hands.get(handNdx).setGameEngine(this);
        }
        
        setPointTracker(savedGameState.savePointTracker);
        
        isGameOver = false;
    }
    
    private Deck createDeck() {
        Deck = new Deck();
        Deck.Shuffle();
        return Deck;
    }
    
    private void addPlaceholderCardsToDeck() {
        for (int cardNdx = 0; cardNdx < NUMBEROFHANDS; cardNdx++) {
            Deck.addToEnd(new DefaultGameCard());
        }
    }
    
    private void createPiles() {
        Piles = new ArrayList<Pile>();
        
        for (int pileNdx = 0; pileNdx < NUMBEROFPILES; pileNdx++) {
            Piles.add(pileNdx, new Pile(Deck.dealTopCard()));
        }
    }
    
    private void createHands() {
        Hands = new ArrayList<Hand>();
        
        for (int handNdx = 0; handNdx < NUMBEROFHANDS; handNdx++) {
            Hands.add(new Hand(Deck.dealTopCard()));
            Hands.get(handNdx).setDeck(Deck);
            Hands.get(handNdx).setGameEngine(this);
        }
    }
    
    private void setupGame() {
        createPiles();
        createHands();
    }
    
    public Card dealTopCard() {
        return Deck.dealTopCard();
    }
    
    private boolean moveAvailable() {
        if ((Piles == null) || (Hands == null) || (Piles.size() == 0)
                || (Hands.size() == 0)) {
            return true;
        }
        
        boolean moveAvailable = false;
        
        for (int i = 0; i < NUMBEROFPILES; i++) {
            for (int j = 0; j < NUMBEROFHANDS; j++) {
                if (Piles.get(i).isMoveLegal(Hands.get(j).mCard)) {
                    // Log.i("PO Game Over", "comparing pile: " + Piles.get(i)
                    //        + " to hand: " + Hands.get(j));
                    moveAvailable = true;
                }
            }
        }
        // Log.i("PO Game Over",
        //        "any moves left?: " + Boolean.toString(moveAvailable));
        return moveAvailable;
    }
    
    // horrifying method to take care of what happens when a game is over
    @SuppressLint("ShowToast")
    public boolean isGameOver() {
        if (Deck.isEmpty() || !moveAvailable()) {
            isGameOver = true;
            // Log.i("PO Game Over",
            //        "is game over?: " + Boolean.toString(isGameOver));
            // stop the time countdown
            MainGame.myTimer.cancel();
            
            // create and show the toast
            CharSequence text = "";
            if (Deck.isEmpty()) {
                text = "You won!";
            } else if (!moveAvailable()) {
                text = "No more moves available.";
            }
            text = text + " Final score: " + mPointTracker.getFinalScore();
            
            MainGame.mToast = Toast.makeText(MainGame.getAppContext(), text,
                    Toast.LENGTH_SHORT);
            MainGame.mToast.setGravity(Gravity.CENTER, 0, 0);
            makeLongToast();
            
            // add the score to the high score list
            HighScore scoreboard = new HighScore(MainGame.getAppContext());
            scoreboard.addScore(mPointTracker.getFinalScore());
            // Log.i("PO Game Over",
            //        "recorded score: " + mPointTracker.getFinalScore());
        }
        
        return isGameOver;
    }
    
    public void setPointTracker(PointTracker pointTracker) {
        mPointTracker = pointTracker;
        
        for (int pileNdx = 0; pileNdx < NUMBEROFPILES; pileNdx++) {
            Piles.get(pileNdx).setPointTracker(mPointTracker);
        }
    }
    
    public ArrayList<Pile> getPileList() {
        return Piles;
    }
    
    public ArrayList<Hand> getHandList() {
        return Hands;
    }
    
    // in order to keep the Toast on the screen for more than a few seconds,
    // this hacky solution was implemented
    private void makeLongToast() {
        Thread t = new Thread() {
            @Override
            public void run() {
                int count = 0;
                try {
                    while (MainGame.showToast && (count < 5)) {
                        MainGame.mToast.show();
                        sleep(1850);
                        count++;
                    }
                    
                } catch (Exception e) {
                    Log.e("LongToast", "", e);
                }
            }
        };
        t.start();
    }
    
}
