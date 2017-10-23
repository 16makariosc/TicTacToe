package com.cis195calculator.maki.tictactoe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class LBActivity extends AppCompatActivity {

    public static final String SCOREFILE = "scoreFile";
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    private final String WIN_POSTFIX = "_w";
    private final String LOSE_POSTFIX = "_l";
    private final String TIE_POSTFIX = "_t";

    ListView list;

    Intent intent;
    Bundle extras;

    Boolean isTie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lb);

        intent = getIntent();
        extras = intent.getExtras();

        prefs = this.getSharedPreferences(SCOREFILE, 0);
        editor = prefs.edit();
        Set<String> usernames = prefs.getStringSet("users", new HashSet<String>());

        Log.d("TEST", "TEST");

        if (extras.getString("parent").equalsIgnoreCase("game")) {

            Log.d("TEST", "TEST2");

            isTie = extras.getBoolean("tie");

            String potentialWinner = extras.getString("winner");
            String potentialLoser = extras.getString("loser");

            usernames.add(potentialWinner);
            usernames.add(potentialLoser);


            for (String s : usernames){
                Log.d("LBActivity", s);
            }

            if (isTie) {
                editor.putInt(potentialWinner + TIE_POSTFIX, prefs.getInt(potentialWinner + TIE_POSTFIX, 0) + 1);
                editor.putInt(potentialLoser + TIE_POSTFIX, prefs.getInt(potentialLoser + TIE_POSTFIX, 0) + 1);
            } else {
                editor.putInt(potentialWinner + WIN_POSTFIX, prefs.getInt(potentialWinner + WIN_POSTFIX, 0) + 1);
                editor.putInt(potentialLoser + LOSE_POSTFIX, prefs.getInt(potentialLoser + LOSE_POSTFIX, 0) + 1);
            }
            editor.putStringSet("users", usernames);
            editor.apply();
        }

        ArrayList<Player> players = new ArrayList<>();

        for (String username : usernames){
            int w = prefs.getInt(username+WIN_POSTFIX, 0);
            int l = prefs.getInt(username+LOSE_POSTFIX, 0);
            int t = prefs.getInt(username+TIE_POSTFIX, 0);
            players.add(new Player(username, w, l, t));
        }

        list = (ListView) findViewById(R.id.lblist);
        ListPlayerAdapter lpadapter = new ListPlayerAdapter(this, players);
        list.setAdapter(lpadapter);

        final TextView deleteLeaderboard = (TextView) findViewById(R.id.deleteData);

        deleteLeaderboard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                deleteAlert();
            }
        });


    }

    void deleteAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to erase all data? This cannot be undone.");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int id){
                editor.clear();
                editor.apply();
                Intent selfIntent = new Intent(getApplicationContext(), LBActivity.class);
                selfIntent.putExtra("parent", "self");
                startActivity(selfIntent);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int id){
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();


    }

    @Override
    public void onBackPressed(){
        Intent goToMenuIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(goToMenuIntent);
    }

}
