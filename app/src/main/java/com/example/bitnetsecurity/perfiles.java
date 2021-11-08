package com.example.bitnetsecurity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.bitnetsecurity.modelo.Adaptador;
import com.example.bitnetsecurity.modelo.Usuario;

import java.util.ArrayList;

public class perfiles extends Fragment {
    private ArrayList<Usuario> usuarios;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfiles, container, false);


    }

    public ArrayList<Usuario> usuarios(){
        usuarios = new ArrayList<>();
        usuarios.add(new Usuario("","","19.125.157-1","Christian","Pasten","+569 52161533","Guardia","Full-time","",
                "https://firebasestorage.googleapis.com/v0/b/bitnet-739b0.appspot.com/o/images%2Ffoto.jpg?alt=media&token=88ad2434-27f7-4253-b541-231f65f0cfa1"
                ,0));
        usuarios.add(new Usuario("","","12.434.543-9","Juanito","Perez","+569 6213563","Guardia","Part-time","",
                "https://firebasestorage.googleapis.com/v0/b/bitnet-739b0.appspot.com/o/images%2F1.jpg?alt=media&token=89d733f6-1507-4628-a50d-80ca2ed5383e"
                ,0));
        usuarios.add(new Usuario("","","20.953.125-1","Dominga","Rojas","+569 679843","Guardia","Part-time","",
                "https://firebasestorage.googleapis.com/v0/b/bitnet-739b0.appspot.com/o/images%2F2.jpeg?alt=media&token=f5096a73-3f68-41a0-889d-b764ba1fb5ed"
                ,0));



        return usuarios;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListView l = (ListView) view.findViewById(R.id.lvUsuarios);
        Adaptador adaptador = new Adaptador(getContext(),usuarios());
        l.setAdapter(adaptador);


    }
}