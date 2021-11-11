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

import com.example.bitnetsecurity.modelo.ArrayListReportesShared;
import com.example.bitnetsecurity.modelo.ArrayListShared;
import com.example.bitnetsecurity.modelo.Reporte;
import com.example.bitnetsecurity.modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Usuario> Supervisores;
    // List<Usuario> Guardias;
    List<Reporte> Reportes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Supervisores = new ArrayList<>();
        Reportes = new ArrayList<>();

        Reportes.add(new Reporte("REPORTE","reporte","reporte","Reporte","reporte","repote","reporte","asdasd","asdasd"));



        Supervisores.add(new Usuario("1","SERVAT","123","Dominga","Ema","+56 952161533","Supervisor","Full-time","123","",1));
        Supervisores.add(new Usuario("2","SERVAT","456","Ema","Dominga","+56 984165321","Supervisor","Full-time","456","",1));


        /**
        Guardias = new ArrayList<>();

        Guardias.add(new Usuario("1","SERVAT","11.111.111-1","Juanito","Perez","+56 989794112","Supervisor","Full-time","123","",1));
        Guardias.add(new Usuario("2","SERVAT","22.222.222-2","Pablito","Rojas","+56 952165431","Supervisor","Full-time","123","",1));
        **/
        ArrayListReportesShared.writeArrayReporte(getApplicationContext(),Reportes);

        ArrayListShared.writeArray(getApplicationContext(),Supervisores);

    }

    public void login (View v){


        //Recuperar los campos usuario & contraseña
        EditText user = this.findViewById(R.id.input_usuario);
        String usertxt = user.getText().toString();
        System.out.println(usertxt);
        EditText pass = this.findViewById(R.id.input_clave);
        String passtxt = pass.getText().toString();
        System.out.println(passtxt);

        try {
            //Recuperar la List desde SharedPreferences
            Supervisores = ArrayListShared.readArray(this);
            for (Usuario u : Supervisores
            ) {
                Toast.makeText(this, u.getNombre(), Toast.LENGTH_SHORT).show();

                if(usertxt.equals(u.getRut()) && passtxt.equals(u.getContrasenia())){

                    CheckBox cbRecuerdame = (CheckBox)findViewById(R.id.cbRecordar);
                    boolean check = cbRecuerdame.isChecked();
                    if(check==true){
                        SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(this);
                        SharedPreferences.Editor editor = datos.edit();
                        editor.putString("user",usertxt);
                        editor.apply();
                    }
                    SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(this);
                    SharedPreferences.Editor editor = datos.edit();
                    editor.putString("person",u.getRut());
                    editor.apply();
                    Intent i = new Intent(this,principal.class);
                    startActivity(i);
                    break;
                }else if(usertxt.equals(u.getRut())&& passtxt.equals("")){
                    Intent i = new Intent(this,registro.class);
                    startActivity(i);
                }else{
                    Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                }

            }
        }catch (Exception e){
            System.out.println("ERROR EN LA CARGA");

        }

        //Mediante la validacion enviar al layout principal
        /**
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
         **/

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

