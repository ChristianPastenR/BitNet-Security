package com.example.bitnetsecurity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bitnetsecurity.modelo.Usuario;

public class registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registro);
        Bundle paquete =getIntent().getExtras();
        if(paquete!=null){
            Usuario u = (Usuario) paquete.getSerializable("usuario");
            EditText carg = this.findViewById(R.id.txt_cargo);
            EditText rut = this.findViewById(R.id.txt_rut);
            rut.setText(u.getRut());
            rut.setEnabled(false);
            carg.setText(u.getCargo());
            carg.setEnabled(false);
        }
    }

    public void atras (View v){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

    public void btn_siguiente (View v){
        Usuario u;
        Bundle paquete =getIntent().getExtras();
        if(paquete!=null){
           u = (Usuario) paquete.getSerializable("usuario");

            EditText nombr = this.findViewById(R.id.txt_nombre);
            String nombre = nombr.getText().toString();

            EditText apellid = this.findViewById(R.id.txt_apellido);
            String apellido = apellid.getText().toString();

            EditText phon = this.findViewById(R.id.txt_phone);
            String phone = phon.getText().toString();

            Spinner sp = this.findViewById(R.id.spJornada);
            String jornada = sp.getSelectedItem().toString();

            u.setNombre(nombre);
            u.setApellido(apellido);
            u.setTelefono(phone);
            u.setJornada(jornada);

            Intent i = new Intent(this,registro_final.class);
            i.putExtra("usuario",u);
            startActivity(i);
        }


    }



}
