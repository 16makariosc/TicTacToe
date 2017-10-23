package com.cis195calculator.maki.tictactoe;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button startGameButton = (Button) findViewById(R.id.start_game);
        final Button leaderboardButton = (Button) findViewById(R.id.leaderboard);

        startGameButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                Intent startGameIntent = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(startGameIntent);
            }
        });

        leaderboardButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                Intent leaderboardIntent = new Intent(getApplicationContext(), LBActivity.class);
                leaderboardIntent.putExtra("parent", "menu");
                startActivity(leaderboardIntent);
            }
        });


    }
}
