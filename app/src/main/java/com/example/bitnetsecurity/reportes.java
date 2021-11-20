package com.example.bitnetsecurity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.bitnetsecurity.modelo.Adaptador;
import com.example.bitnetsecurity.modelo.AdaptadorReportes;
import com.example.bitnetsecurity.modelo.ArrayListReportesShared;
import com.example.bitnetsecurity.modelo.ArrayListShared;
import com.example.bitnetsecurity.modelo.Reporte;
import com.example.bitnetsecurity.modelo.Usuario;

import java.util.ArrayList;
import java.util.List;


public class reportes extends Fragment {

    private List<Reporte> reportes;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Cargar layout del fragmento
        View vista = inflater.inflate(R.layout.fragment_reportes, container, false);

        //Recuperar el boton desde el layout/vista
        Button btn = vista.findViewById(R.id.btn_crear_reporte);

        ((AppCompatActivity)getActivity()).getSupportActionBar().show();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.contenedor,new crear_reportes()).commit();

            }
        });





        return vista;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        try {
            ListView S = (ListView)view.findViewById(R.id.lvReportes);
            reportes = ArrayListReportesShared.readArrayReporte(getContext());
            ArrayList<Reporte> reportesArray = new ArrayList<>();

            for (Reporte r : reportes
            ) {
                System.out.println(r.getFecha());
                reportesArray.add(r);
            }

            AdaptadorReportes adaptadorReportes = new AdaptadorReportes(getContext(), reportesArray);
            S.setAdapter(adaptadorReportes);
        }catch (Exception e){

        }



    }
}
