package com.example.bitnetsecurity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
    }




    public void atras (View v){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

    public void btn_siguiente (View v){

        EditText carg = this.findViewById(R.id.txt_cargo);
        String cargo = carg.getText().toString();

        EditText ru = this.findViewById(R.id.txt_rut);
        String rut = ru.getText().toString();

        EditText nombr = this.findViewById(R.id.txt_nombre);
        String nombre = nombr.getText().toString();

        EditText apellid = this.findViewById(R.id.txt_apellido);
        String apellido = apellid.getText().toString();

        EditText phon = this.findViewById(R.id.txt_phone);
        String phone = phon.getText().toString();





        Usuario u = new Usuario();
        u.setCargo(cargo);
        u.setRut(rut);
        u.setNombre(nombre);
        u.setApellido(apellido);
        u.setTelefono(phone);
        System.out.println(u.toString());

    }

}
