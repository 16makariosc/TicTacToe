package com.cis195calculator.maki.tictactoe;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by tsartang on 10/23/2017.
 */

public class ListPlayerAdapter extends ArrayAdapter<Player> {

    LayoutInflater inflater;
    public ListPlayerAdapter(Context context, ArrayList<Player> players){
        super(context, R.layout.individual_player, players);
        inflater = LayoutInflater.from(context);
    }

    @Override
    @NonNull
    public View getView(int position, View view, @NonNull ViewGroup parent){
        if (view == null){
            view = inflater.inflate(R.layout.individual_player, parent, false);
        }
        Player player = getItem(position);
        String text = player.getName() + " W:" + player.getWinCount()  + " L:" + player.getLossCount() + " T:" + player.getTieCount();
        ((TextView) view.findViewById(R.id.result)).setText(text);
        return view;
    }

}
