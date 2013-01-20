/**
 * 
 */
package com.game.pileon;

/**
 * @author breeze4
 *
 */
public class PointTracker
{
	private int points;
	
	public PointTracker(){
		points = 0;
	}
	
	
	public int computePoints(){
		return points;
	}
	
	public String displayPoints(){
		return Integer.toString(points);
	}
	
}
