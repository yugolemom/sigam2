package br.unitins.sigam2.controllers;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by hugo on 22/06/16.
 */
public class SessionManager {


    public void setPreferences(Context context, String key, String value) {

        SharedPreferences.Editor editor = context.getSharedPreferences("ids", Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.commit();

    }


    public String getPreferences(Context context, String key) {

        SharedPreferences prefs = context.getSharedPreferences("ids", Context.MODE_PRIVATE);
        String position = prefs.getString(key, "");

        return position;
    }

    public void setIntegerPreferences(Context context, String key, Integer value) {

        SharedPreferences.Editor editor = context.getSharedPreferences("ids", Context.MODE_PRIVATE).edit();
        editor.putInt(key, value);
        editor.commit();

    }

    public Integer getIntegerPreferences(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences("ids", Context.MODE_PRIVATE);
        Integer position = prefs.getInt(key, 0);

        return position;
    }


}
