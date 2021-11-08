package com.example.bitnetsecurity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void login (View v){


        //Recuperar los campos usuario & contraseña
        EditText user = this.findViewById(R.id.input_usuario);
        String usertxt = user.getText().toString();
        System.out.println(usertxt);
        EditText pass = this.findViewById(R.id.input_clave);
        String passtxt = pass.getText().toString();
        System.out.println(passtxt);

        //Mediante la validacion enviar al layout principal
        if(usertxt.equals("user") && passtxt.equals("123")){

            CheckBox cbRecuerdame = (CheckBox)findViewById(R.id.cbRecordar);
            boolean check = cbRecuerdame.isChecked();
            if(check==true){
                SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = datos.edit();
                editor.putString("user",usertxt);
                editor.apply();
            }

            Intent i = new Intent(this,principal.class);
            startActivity(i);
        }else if(usertxt.equals("user")&& passtxt.equals("")){
            Intent i = new Intent(this,registro.class);
            startActivity(i);
        }else{
            Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
        }

    }

    public void salir (View v){

        Intent i = new Intent(this,MainActivity.class);

        startActivity(i);

    }

    public void btnCrearCuenta (View v){


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(Html.fromHtml("¡Saludos!"));
        builder.setMessage(Html.fromHtml("Para ser miembro del equipo BitNet Security, " +
                "debes contactarnos vía Whatsapp o Email " +
                "con los siguientes datos: <br><br>"+
                "RUT supervisor o admin (1 por contratación).<br><br>"+
                "Nombre empresa.<br><br>"+
                "Teléfono(s) de contacto.<br><br>"+
                "Comprobante de pago (Jpg, png, pdf)<br><br><br><br>"+
                "Contacto@bitnetsecurity.cl <br><br>" +
                        "+56 955161534<br><br>" +
                        "99.254.116-1 Chequera electrónica"
                ));



        builder.setPositiveButton("OK",null);
        builder.create();
        builder.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(this);
        String usertxt = datos.getString("user","");
        if(!usertxt.equals("")){
            Intent i = new Intent(this,principal.class);
            startActivity(i);
        }
    }
}

