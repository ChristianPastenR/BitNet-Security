package com.example.bitnetsecurity.modelo;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ArrayListReportesShared {


    private static final String LIST_KEY = "list_reportes_key";


    //JSON WRITE ARRAYREPORTES EN SHAREDPREF
    public static void writeArrayReporte(Context context, List<Reporte> list){

        Gson gson = new Gson();
        String jsonString = gson.toJson(list);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(LIST_KEY,jsonString);
        editor.apply();


    }
    public static List<Reporte> readArrayReporte(Context context){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        String jsonString = pref.getString(LIST_KEY,"");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Reporte>>() {}.getType();
        List<Reporte> list =gson.fromJson(jsonString,type);
        return list;
    }
}
