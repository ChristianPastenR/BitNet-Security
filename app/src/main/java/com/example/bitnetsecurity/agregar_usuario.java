package com.example.bitnetsecurity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bitnetsecurity.modelo.ArrayListShared;
import com.example.bitnetsecurity.modelo.Usuario;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;


public class agregar_usuario extends Fragment {
    Usuario u;
    List<Usuario> usuarios;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment








        return inflater.inflate(R.layout.fragment_agregar_usuario, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnCancelar = getActivity().findViewById(R.id.btnCancelarUsuario);
        Button btnEnviar = getActivity().findViewById(R.id.btnAgregarPersona);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Cargar layout del fragmento
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.contenedor,new perfiles()).commit();
            }
        });
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {


                    EditText ed1 = getActivity().findViewById(R.id.editNuevoRut);
                    String rut = ed1.getText().toString();

                    EditText ed2 = getActivity().findViewById(R.id.editNuevoPass);
                    String pass = ed2.getText().toString();

                    Spinner sp = getActivity().findViewById(R.id.spNuevoUser);
                    String cargo = sp.getSelectedItem().toString();

                    if (rut.equals("") || pass.equals("") || cargo.equals("")) {
                        Toast.makeText(getActivity(), "Campos obligatorios", Toast.LENGTH_SHORT).show();
                    } else {
                        usuarios = ArrayListShared.readArray(getActivity());
                        u = new Usuario("", "", rut, "", "", "", cargo, "", pass, "", 0);
                        usuarios.add(u);
                        ArrayListShared.writeArray(getActivity(), usuarios);
                        Toast.makeText(getActivity(), "Usuario agregado", Toast.LENGTH_SHORT).show();
                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        fm.beginTransaction().replace(R.id.contenedor,new perfiles()).commit();

                    }
                }catch (Exception e){
                    Toast.makeText(getActivity(), "Error al agregar", Toast.LENGTH_SHORT).show();
                }




            }
        });

    }
}