package com.example.bitnetsecurity;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bitnetsecurity.modelo.Adaptador;
import com.example.bitnetsecurity.modelo.ArrayListShared;
import com.example.bitnetsecurity.modelo.Reporte;
import com.example.bitnetsecurity.modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class perfiles extends Fragment {

    private List<Usuario> Usuarios;
    private ArrayList<Usuario> guardiasArray;
    private ArrayList<Usuario> supervisorArray;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfiles, container, false);


    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Usuarios = ArrayListShared.readArray(getActivity());

        SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String usertxt = datos.getString("person","");

        Usuario u1 = buscarUsuario(usertxt);
        Button btn = getActivity().findViewById(R.id.btnAgregarUsuario);

        if(u1.getCargo().equals("Supervisor")){

            btn.setVisibility(View.VISIBLE);
            btn.setClickable(true);


        }else{
            btn.setVisibility(View.GONE);
            btn.setClickable(false);


        }


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Cargar layout del fragmento


                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.contenedor,new agregar_usuario()).commit();



            }
        });



         guardiasArray= new ArrayList<>();
         supervisorArray= new ArrayList<>();

        Usuarios = ArrayListShared.readArray(getActivity());
        for (Usuario u:Usuarios
             ) {
            if(u.getCargo().equals("Supervisor")&& u.getEstado()!=0){
                supervisorArray.add(u);
            }else if(u.getEstado()!=0){
                guardiasArray.add(u);
            }
        }


        ListView S = (ListView) view.findViewById(R.id.lvSupervisores);
        Adaptador adaptador = new Adaptador(getContext(),supervisorArray);
        S.setAdapter(adaptador);

        ListView G = (ListView) view.findViewById(R.id.lvGuardias);
        Adaptador adaptador1 = new Adaptador(getContext(),guardiasArray);
        G.setAdapter(adaptador1);

    }
    public Usuario buscarUsuario(String rut){

        Usuario usuario = new Usuario();
        Usuarios = ArrayListShared.readArray(getActivity());
        for (Usuario u:Usuarios
        ) {
            if(u.getRut().equals(rut)){
                usuario= (Usuario) u;
                return usuario;
            }

        }
        return null;
    }


}