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

public class AdaptadorReportes extends BaseAdapter {

    private Context contexto;
    private ArrayList<Reporte> reportes;

    public AdaptadorReportes(Context contexto, ArrayList<Reporte> reportes) {
        this.contexto = contexto;
        this.reportes = reportes;
    }

    @Override
    public int getCount() {
        return reportes.size();
    }

    @Override
    public Object getItem(int i) {
        return reportes.get(i);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        view = LayoutInflater.from(contexto).inflate(R.layout.lvreportes, null);


        TextView lvrUsuario = (TextView) view.findViewById(R.id.lvrusuario);
        TextView lvrFecha = (TextView) view.findViewById(R.id.lvrfecha);

        try {
            Reporte r = (Reporte) getItem(i);
            /**
             Glide.with(contexto)
             .load(u.getUri())
             .centerCrop()
             .fitCenter()
             .into(ivFoto);
             **/

            lvrUsuario.setText(r.getUsuario());
            lvrFecha.setText(r.getFecha());
        }catch (Exception e){

        }

        return view;
    }
}
