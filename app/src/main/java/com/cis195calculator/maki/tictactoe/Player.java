package com.cis195calculator.maki.tictactoe;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tsartang on 10/23/2017.
 */

public class Player implements Parcelable {

    String name;
    int win;
    int loss;
    int tie;

    public Player(String name, int win, int loss, int tie){
        this.name = name;
        this.win = win;
        this.loss = loss;
        this.tie = tie;
    }

    public Player(Parcel parcel){
        this.win = parcel.readInt();
        this.loss = parcel.readInt();
        this.tie = parcel.readInt();
    }

    public String getName(){
        return name;
    }

    public int getWinCount(){
        return win;
    }

    public void incWinCount(){
        win++;
    }

    public int getLossCount(){
        return loss;
    }

    public void incLossCount(){
        loss++;
    }

    public int getTieCount(){
        return tie;
    }

    public void incTieCount(){
        tie++;
    }

    @Override
    public int describeContents() {
        return 0; //so... is this just like, not used?
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(win);
        dest.writeInt(loss);
        dest.writeInt(tie);
    }

    public static final Parcelable.Creator<Player> CREATOR
            = new Parcelable.Creator<Player>() {
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        public Player[] newArray(int size){
            return new Player[size];
        }

    };
}
