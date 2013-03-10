package com.game.pileon;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ScoreScreen extends Activity {
    private boolean gameInProgress;
    private ArrayList<TextView> scoreList;
    private HighScore scores;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);
        
        Intent intent = getIntent();
        gameInProgress = intent.getBooleanExtra(
                "com.game.pileon.GameInProgress", false);
        
        scores = new HighScore(this);
        scoreList = new ArrayList<TextView>(10);
        
        setupScoreList();
        displayScores();
    }
    
    private void setupScoreList(){
        
        for(int i = 0; i < 10; i++){
            String scoreID = "score" + (i+1);
            // Log.i("PO Scores", "scoreID: " + scoreID);
            int resID = getResources().getIdentifier(scoreID, "id", getPackageName());
            TextView scoreEntry = (TextView)findViewById(resID);
            scoreList.add(i, scoreEntry);
            // Log.i("PO Scores", "added score view to list: " + scoreEntry.getText());
        }
    }
    
    private void displayScores(){
        for(int i = 0; i < 10; i++){
            scores.addScore(i);
            TextView scoreEntry = scoreList.get(i);
            // Log.i("PO Scores", "displaying score: " + scores.getScore(i));
            scoreEntry.setText(String.valueOf(scores.getScore(i)));
        }
    }
    
    public void backToMain(View view) {
        Intent intent = new Intent(ScoreScreen.this, GameMenu.class);
        intent.putExtra("com.game.pileon.GameInProgress", gameInProgress);
        startActivity(intent);
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    
}