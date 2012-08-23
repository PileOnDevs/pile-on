package com.game.pileon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class GameMenu extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_game_menu, menu);
        return true;
    }
    public void about(View view) {
    	Intent intent = new Intent(this, AboutScreen.class);
    	startActivity(intent);
    }
    
    public void help(View view) {
    	Intent intent = new Intent(this, HelpScreen.class);
    	startActivity(intent);
    }
    
    public void launchGame(View view) {
    	Intent intent = new Intent(this, MainGame.class);
    	startActivity(intent);
    }    
    
    
    protected void onDestroy() {
        super.onDestroy();
    }
    
}
