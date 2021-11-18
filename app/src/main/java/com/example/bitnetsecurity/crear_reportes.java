package com.example.bitnetsecurity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.bitnetsecurity.modelo.ArrayListReportesShared;
import com.example.bitnetsecurity.modelo.ArrayListShared;
import com.example.bitnetsecurity.modelo.Reporte;
import com.example.bitnetsecurity.modelo.Usuario;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;


public class crear_reportes extends Fragment {

    private List<Usuario> Supervisores;
    private List<Reporte> reportes;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_crear_reportes, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();


        //Recuperar el boton desde el layout/vista
        Button btn = vista.findViewById(R.id.btn_cancelar_reporte);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.contenedor,new reportes()).commit();

            }
        });
            Button btnenviar= vista.findViewById(R.id.btnenviar);
            btnenviar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                        // PENDIENTE

                        TextInputEditText ed1 =  getActivity().findViewById(R.id.crearEmpresa);
                        String empresa = ed1.getText().toString();

                        TextInputEditText ed2 =  getActivity().findViewById(R.id.crearFaccion);
                        String faccion = ed2.getText().toString();

                        TextInputEditText ed3 =  getActivity().findViewById(R.id.crearFecha);
                        String fecha = ed3.getText().toString();

                        TextInputEditText ed4 =  getActivity().findViewById(R.id.crearTurno);
                        String turno = ed4.getText().toString();

                        TextInputEditText ed5 =  getActivity().findViewById(R.id.crearGuardia);
                        String guardia = ed5.getText().toString();

                        TextInputEditText ed6 =  getActivity().findViewById(R.id.crearSupervisor);
                        String supervisor = ed6.getText().toString();

                        TextInputEditText ed7 =  getActivity().findViewById(R.id.crearReporte);
                        String reporte = ed7.getText().toString();

                        if(empresa.equals("") || faccion.equals("") || fecha.equals("") || turno.equals("") || guardia.equals("") || supervisor.equals("") || reporte.equals("")){
                            Toast.makeText(getActivity(), "Campos obligatorios", Toast.LENGTH_SHORT).show();

                        }else{
                            Reporte r = new Reporte("","",fecha,empresa,faccion,turno,guardia,supervisor,reporte);
                            reportes = ArrayListReportesShared.readArrayReporte(getContext());
                            reportes.add(r);
                            ArrayListReportesShared.writeArrayReporte(getActivity(),reportes);
                        }

                    }catch (Exception e){
                        System.out.println("EDIT TEXT NULO");
                    }

                }
            });

        return vista;
    }

    public void enviarReporte(View v){


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