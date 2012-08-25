package com.game.pileon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainGame extends Activity {

	private GameType mGameType;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        
        mGameType = new DefaultGameType();
    }

    
    public void backToMain(View view) {
    	Intent intent = new Intent(MainGame.this, GameMenu.class);
    	startActivity(intent);
    }
    protected void onDestroy() {
        super.onDestroy();
    }
    
    
}