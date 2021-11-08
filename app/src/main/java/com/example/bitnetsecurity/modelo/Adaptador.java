package com.example.bitnetsecurity.modelo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bitnetsecurity.R;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {

    private Context contexto;
    private ArrayList<Usuario> usuarios;

    public Adaptador(Context contexto, ArrayList<Usuario> usuarios) {
        this.contexto = contexto;
        this.usuarios = usuarios;
    }

    @Override
    public int getCount() {
        return usuarios.size() ;
    }

    @Override
    public Object getItem(int i) {
        return usuarios.get(i);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        view = LayoutInflater.from(contexto).inflate(R.layout.listview, null);
        ImageView ivFoto = (ImageView) view.findViewById(R.id.fotoPerfil);
        TextView tvNombre = (TextView) view.findViewById(R.id.tvNombre);
        TextView tvRut = (TextView) view.findViewById(R.id.tvRut);
        TextView tvTelefono = (TextView) view.findViewById(R.id.tvTelefono);
        TextView tvJornada = (TextView) view.findViewById(R.id.tvJornada);

        Usuario u = (Usuario) getItem(i);
        Glide.with(contexto)
                .load(u.getUri())
                .centerCrop()
                .fitCenter()
                .into(ivFoto);
        ivFoto.getLayoutParams().height = 200;
        ivFoto.getLayoutParams().width = 200;
        tvNombre.setText(u.getNombre());
        tvRut.setText(u.getRut());
        tvTelefono.setText(u.getTelefono());
        tvJornada.setText(u.getJornada());
        return view;
    }
}
