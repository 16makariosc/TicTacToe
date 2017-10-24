package com.cis195calculator.maki.tictactoe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class GameActivity extends FragmentActivity implements DialogInterface.OnDismissListener{

    Boolean isPlayer1Turn;
    HashMap<String, Integer> p1WinTable;
    HashMap<String, Integer> p2WinTable;

    EditText p1;
    EditText p2;

    String potentialWinner;
    String potentialLoser;

    Boolean tie;
    Boolean gameOver;
    int turnsTaken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        isPlayer1Turn = true;
        p1WinTable = new HashMap<String, Integer>();
        p2WinTable = new HashMap<String, Integer>();

        p1 = (EditText) findViewById(R.id.player1name);
        p2 = (EditText) findViewById(R.id.player2name);

        tie = false;
        gameOver = false;
        turnsTaken = 0;

        final ImageView i11 = (ImageView) findViewById(R.id.i11);
        final ImageView i21 = (ImageView) findViewById(R.id.i21);
        final ImageView i31 = (ImageView) findViewById(R.id.i31);
        final ImageView i12 = (ImageView) findViewById(R.id.i12);
        final ImageView i22 = (ImageView) findViewById(R.id.i22);
        final ImageView i32 = (ImageView) findViewById(R.id.i32);
        final ImageView i13 = (ImageView) findViewById(R.id.i13);
        final ImageView i23 = (ImageView) findViewById(R.id.i23);
        final ImageView i33 = (ImageView) findViewById(R.id.i33);

        i11.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (setSquare(i11)) {
                    updateWinTable("r1");
                    updateWinTable("c1");
                    updateWinTable("d1");
                    checkForTie();
                    isPlayer1Turn = !isPlayer1Turn;
                }
            }
        });

        i21.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (setSquare(i21)) {
                    updateWinTable("r1");
                    updateWinTable("c2");
                    checkForTie();
                    isPlayer1Turn = !isPlayer1Turn;
                }
            }
        });

        i31.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (setSquare(i31)){
                    updateWinTable("r1");
                    updateWinTable("c3");
                    updateWinTable("d2");
                    checkForTie();
                    isPlayer1Turn = !isPlayer1Turn;
                }
            }
        });

        i12.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (setSquare(i12)){
                    updateWinTable("r2");
                    updateWinTable("c1");
                    checkForTie();
                    isPlayer1Turn = !isPlayer1Turn;
                }
            }
        });

        i22.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (setSquare(i22)){
                    updateWinTable("r2");
                    updateWinTable("c2");
                    updateWinTable("d1");
                    updateWinTable("d2");
                    checkForTie();
                    isPlayer1Turn = !isPlayer1Turn;
                }
            }
        });

        i32.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (setSquare(i32)){
                    updateWinTable("r2");
                    updateWinTable("c3");
                    checkForTie();
                    isPlayer1Turn = !isPlayer1Turn;
                }
            }
        });

        i13.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (setSquare(i13)){
                    updateWinTable("r3");
                    updateWinTable("c1");
                    updateWinTable("d2");
                    checkForTie();
                    isPlayer1Turn = !isPlayer1Turn;
                }
            }
        });

        i23.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (setSquare(i23)){
                    updateWinTable("r3");
                    updateWinTable("c2");
                    checkForTie();
                    isPlayer1Turn = !isPlayer1Turn;
                }
            }
        });

        i33.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (setSquare(i33)){
                    updateWinTable("r3");
                    updateWinTable("c3");
                    updateWinTable("d1");
                    checkForTie();
                    isPlayer1Turn = !isPlayer1Turn;
                }
            }
        });


    }

    //Returns true if the square is updated, false otherwise
    Boolean setSquare(ImageView iv) {

        if (iv.getDrawable() == null) {
            if (isPlayer1Turn) {
                iv.setImageResource(R.drawable.x);
            } else {
                iv.setImageResource(R.drawable.o);
            }
            turnsTaken++;
            return true;
        }

        return false;
    }

    void updateWinTable(String winRowId) {
        HashMap<String, Integer> copy;

        if (isPlayer1Turn) {
            if (p1WinTable.get(winRowId) == null){
                p1WinTable.put(winRowId, 1);
            } else {
                p1WinTable.put(winRowId, p1WinTable.get(winRowId) + 1);
            }
            copy = p1WinTable;

            potentialWinner = p1.getText().toString();
            potentialWinner = potentialWinner.equalsIgnoreCase("") ? p1.getHint().toString() : potentialWinner;

            potentialLoser = p2.getText().toString();
            potentialLoser = potentialLoser.equalsIgnoreCase("") ? p2.getHint().toString() : potentialLoser;

        } else {
            if (p2WinTable.get(winRowId) == null) {
                p2WinTable.put(winRowId, 1);
            } else {
                p2WinTable.put(winRowId, p2WinTable.get(winRowId) + 1);
            }
            copy = p2WinTable;

            potentialWinner = p2.getText().toString();
            potentialWinner = potentialWinner.equalsIgnoreCase("") ? p2.getHint().toString() : potentialWinner;

            potentialLoser = p1.getText().toString();
            potentialLoser = potentialLoser.equalsIgnoreCase("") ? p1.getHint().toString() : potentialLoser;
        }

        if (copy.get(winRowId) >= 3) {
            //Toast.makeText(this, "someone just won but idk who", Toast.LENGTH_LONG).show();
            gameOver = true;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(potentialWinner + " " + getString(R.string.wonDialog));
            builder.setNeutralButton(potentialLoser + " " + getString(R.string.lostButton), new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialog, int id){
                    dialog.dismiss();
                }
            });

            AlertDialog winAlert = builder.create();
            winAlert.setOnDismissListener(this);
            winAlert.show();
        }
    }

    void checkForTie(){
        if (turnsTaken >= 9 && !gameOver){
            tie = true;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(getString(R.string.tie));
            builder.setNeutralButton(getString(R.string.tieDialog), new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialog, int id){
                    dialog.dismiss();
                }
            });

            AlertDialog winAlert = builder.create();
            winAlert.setOnDismissListener(this);
            winAlert.show();
        }
    }

    @Override
    public void onDismiss(final DialogInterface dialog){
        //Toast.makeText(this, "dialog gone", Toast.LENGTH_LONG).show();

        Intent goToLeaderboardIntent = new Intent(getApplicationContext(), LBActivity.class);

        if (tie) {
            goToLeaderboardIntent.putExtra("tie", true);
        } else {
            goToLeaderboardIntent.putExtra("tie", false);
        }

        goToLeaderboardIntent.putExtra("winner", potentialWinner);
        goToLeaderboardIntent.putExtra("loser", potentialLoser);
        goToLeaderboardIntent.putExtra("parent", "game");
        startActivity(goToLeaderboardIntent);

    }


}


