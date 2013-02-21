package com.game.pileon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AboutScreen extends Activity {
    private boolean gameInProgress;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_screen);
        
        Intent intent = getIntent();
        gameInProgress = intent.getBooleanExtra(
                "com.game.pileon.GameInProgress", false);
    }
    
    public void backToMain(View view) {
        Intent intent = new Intent(AboutScreen.this, GameMenu.class);
        intent.putExtra("com.game.pileon.GameInProgress", gameInProgress);
        startActivity(intent);
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    
}