package com.example.bitnetsecurity.modelo;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ArrayListShared {




    private static final String LIST_KEY = "list_key";


    //WRITE ARRAYUSUARIOS EN SHAREDPREF
    public static void writeArray(Context context, List<Usuario> list){
        Gson gson = new Gson();
        String jsonString = gson.toJson(list);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(LIST_KEY,jsonString);
        editor.apply();
    }
    public static List<Usuario> readArray(Context context){
        try {
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
            String jsonString = pref.getString(LIST_KEY, "");
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<Usuario>>() {
            }.getType();
            List<Usuario> list = gson.fromJson(jsonString, type);
            return list;
        }catch (Exception e){
            return null;
        }
    }




}
