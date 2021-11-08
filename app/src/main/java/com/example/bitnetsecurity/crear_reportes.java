package com.example.bitnetsecurity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class crear_reportes extends Fragment {





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
        return vista;
    }

}