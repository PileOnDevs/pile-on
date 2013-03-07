package com.game.pileon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

public class AboutScreen extends Activity {
    private boolean gameInProgress;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_screen);
        
        Intent intent = getIntent();
        gameInProgress = intent.getBooleanExtra(
                "com.game.pileon.GameInProgress", false);
        
        TextView linkView = (TextView) findViewById(R.id.aboutLink);
        linkView.setText(Html.fromHtml("<a href=https://github.com/PileOnDevs/pile-on> github "));
        linkView.setMovementMethod(LinkMovementMethod.getInstance());
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