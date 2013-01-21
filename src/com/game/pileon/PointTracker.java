/**
 * 
 */
package com.game.pileon;

import android.widget.TextView;

/**
 * @author breeze4
 *
 */
public class PointTracker
{
	private int points;
	private TextView pointDisplay;
	
	public PointTracker(TextView pointView){
		points = 0;
		pointDisplay = pointView;
	}
	
	public String toString(){
		return Integer.toString(points);
	}
	
	public void updateDisplay(){
		pointDisplay.setText("points: " + toString());
	}
	
	public void processMove(Card cardPlayed, Card pileCard){
		int pointsGained = 0;
		if (cardPlayed.equalValueTo(pileCard))
		{
			pointsGained = 2*cardPlayed.getValue();
		}
		else if (cardPlayed.equalColorTo(pileCard))
		{
			pointsGained = 2*cardPlayed.getValue();
		}
		points = points + pointsGained;
		
		updateDisplay();
	}
	
}
