/**
 * 
 */
package com.game.pileon;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import android.widget.TextView;

/**
 * @author breeze4
 *
 */
@Root(name="PointTracker")
public class PointTracker
{
	@Attribute(name="points")
	private int points;
	private TextView pointDisplay;
	
	public PointTracker(@Attribute(name="points")int startingPoints){
		points = startingPoints;
	}
	
	public String toString(){
		return Integer.toString(points);
	}
	
	public void updateDisplay(){
		pointDisplay.setText("points: " + toString());
	}
	
	public void setPointView(TextView pointView){
		pointDisplay = pointView;
		updateDisplay();
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
