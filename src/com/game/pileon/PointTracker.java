/**
 * 
 */
package com.game.pileon;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import android.widget.TextView;

/**
 * @author breeze4
 * 
 */
@Root(name = "PointTracker")
public class PointTracker {
    @Attribute(name = "points")
    private int points;
    private TextView pointDisplay;
    private TextView timeDisplay;
    @Attribute(name = "bonusMultiplier")
    private int bonusMultiplier;
    @Attribute(name = "timeBonus")
    public int timeBonus;
    
    public PointTracker(@Attribute(name = "points") int startingPoints,
            @Attribute(name = "timeBonus") int startingBonus,
            @Attribute(name = "bonusMultiplier") int startingMultiplier) {
        points = startingPoints;
        timeBonus = startingBonus;
        bonusMultiplier = startingMultiplier;
    }
    
    @Override
    public String toString() {
        return "points: " + Integer.toString(points) + " time bonus: "
                + Integer.toString(timeBonus);
    }
    
    public void updateDisplay() {
        pointDisplay.setText("points: " + Integer.toString(points));
        timeDisplay.setText("time bonus: " + Integer.toString(timeBonus));
    }
    
    public void setPointView(TextView pointView, TextView timeView) {
        pointDisplay = pointView;
        timeDisplay = timeView;
        updateDisplay();
    }
    
    public void processMove(Card cardPlayed, Card pileCard) {
        int pointsGained = 0;
        if (cardPlayed.equalValueTo(pileCard)
                && !cardPlayed.equalColorTo(pileCard)) {
            bonusMultiplier = 3;
        } else if (!cardPlayed.equalValueTo(pileCard)
                && cardPlayed.equalColorTo(pileCard)) {
            bonusMultiplier = 1;
        }
        
        if (cardPlayed.equalValueTo(pileCard)) {
            pointsGained = bonusMultiplier * 10 * cardPlayed.getValue();
        } else if (cardPlayed.equalColorTo(pileCard)) {
            pointsGained = bonusMultiplier * 10 * cardPlayed.getValue();
        }
        points = points + pointsGained;
        
        updateDisplay();
    }
    
    public int getFinalScore(){
        updateDisplay();
        return points + timeBonus;
    }
}
