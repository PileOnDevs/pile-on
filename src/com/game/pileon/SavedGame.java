/**
 * 
 */
package com.game.pileon;

import java.util.ArrayList;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

/**
 * @author breeze4
 * 
 *         Object that gets serialized, contains save game data and methods for
 *         adding and getting pieces of saved game
 * 
 */

@Root(name = "SavedGame")
public class SavedGame {
    @ElementList(name = "savePiles")
    public ArrayList<Pile> savePiles;
    @ElementList(name = "saveHands")
    public ArrayList<Hand> saveHands;
    @Element(name = "saveDeck")
    public Deck saveDeck;
    @Element(name = "savePointTracker")
    public PointTracker savePointTracker;
    
    public SavedGame(
            @ElementList(name = "savePiles") ArrayList<Pile> toSavePiles,
            @ElementList(name = "saveHands") ArrayList<Hand> toSaveHands,
            @Element(name = "saveDeck") Deck toSaveDeck,
            @Element(name = "savePointTracker") PointTracker toSavePointTracker) {
        savePiles = toSavePiles;
        saveHands = toSaveHands;
        saveDeck = toSaveDeck;
        savePointTracker = toSavePointTracker;
    }
    
    @Override
    public String toString() {
        String savedGameString = "";
        savedGameString += "Piles: " + savePiles.toString() + " Hands: "
                + saveHands.toString() + " Deck: " + saveDeck.toString()
                + savePointTracker.toString();
        return savedGameString;
    }
    
}
