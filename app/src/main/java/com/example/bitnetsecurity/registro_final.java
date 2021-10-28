package com.example.bitnetsecurity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class registro_final extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_final);

    }


    public void atras (View v){
        Intent i = new Intent(this,registro.class);
        startActivity(i);
    }

}