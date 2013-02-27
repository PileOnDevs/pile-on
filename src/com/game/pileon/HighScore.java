package com.game.pileon;

import android.content.Context;
import android.content.SharedPreferences;

public class HighScore {
    private SharedPreferences preferences;
    private int score[];
    
    public HighScore(Context context) {
        preferences = context.getSharedPreferences("Highscore", 0);
        score = new int[10];
        
        for (int x = 0; x < 10; x++) {
            score[x] = preferences.getInt("score" + x, 0);
        }
        
    }
    
    public int getScore(int x) {
        // get the score of the x-th position in the Highscore-List
        return score[x];
    }
    
    public boolean inHighscore(int score) {
        // test, if the score is in the Highscore-List
        int position;
        for (position = 0; (position < 10) && (this.score[position] > score); position++) {
            ;
        }
        
        if (position == 10) {
            return false;
        }
        return true;
    }
    
    public boolean addScore(int score) {
        // add the score with the name to the Highscore-List
        int position;
        for (position = 0; (position < 10) && (this.score[position] > score); position++) {
            ;
        }
        
        if (position == 10) {
            return false;
        }
        
        for (int x = 9; x > position; x--) {
            this.score[x] = this.score[x - 1];
        }
        this.score[position] = score;
        
        SharedPreferences.Editor editor = preferences.edit();
        for (int x = 0; x < 10; x++) {
            editor.putInt("score" + x, this.score[x]);
        }
        editor.commit();
        return true;
        
    }
    
}