package com.example.bitnetsecurity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.bitnetsecurity.modelo.ArrayListShared;
import com.example.bitnetsecurity.modelo.Usuario;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;


public class crear_reportes extends Fragment {

    List<Usuario> Supervisores;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_crear_reportes, container, false);



        //Recuperar el boton desde el layout/vista
        Button btn = vista.findViewById(R.id.btn_cancelar_reporte);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.contenedor,new reportes()).commit();

            }
        });


        try{
            // PENDIENTE
            SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(getActivity());
            String usertxt = datos.getString("person","");

            System.out.println("RUT DESDE SHAREDPREF "+usertxt);
            Usuario u = buscarUsuario(usertxt);
            System.out.println("CREAR REPORTE "+u.getCargo());

            TextInputLayout editText =  getActivity().findViewById(R.id.crearEmpresa);
            String txt = editText.toString();
            System.out.println("EDITEXT "+txt);
        }catch (Exception e){
            System.out.println("USUARUIO NULO");
        }







        return vista;
    }









    public Usuario buscarUsuario(String rut){

        Usuario usuario = new Usuario();
        Supervisores = ArrayListShared.readArray(getActivity());
        for (Usuario u:Supervisores
             ) {
            if(u.getRut().equals(rut)){
                usuario= (Usuario) u;
                return usuario;
            }

        }
        return null;
    }


}