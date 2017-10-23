package com.cis195calculator.maki.tictactoe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LBActivity extends AppCompatActivity {
    public static final String SCOREFILE = "scoreFile";

    Intent intent;
    Bundle extras;

    String winner;
    String loser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lb);

        intent = getIntent();
        extras = intent.getExtras();

        if (extras != null){
            if (extras.containsKey("winner")){
                winner = extras.getString("winner");
            }
            if (extras.containsKey("loser")){
                loser = extras.getString("loser");
            }
        }

        SharedPreferences prefs = this.getSharedPreferences(SCOREFILE, 0);

    }

    @Override
    public void onBackPressed(){
        Intent goToMenuIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(goToMenuIntent);
    }

}
