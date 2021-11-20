package com.example.bitnetsecurity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class registroExito extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_exito);
    }

    public void inicio (View v){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
}