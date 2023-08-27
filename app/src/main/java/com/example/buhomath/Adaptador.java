package com.example.buhomath;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {
    private Context contexto;
    private LayoutInflater inflater;
    private ArrayList<UsuarioRanking> usuarios;

    public Adaptador(Context contexto, ArrayList<UsuarioRanking> usuarios){
        this.contexto = contexto;
        this.usuarios = usuarios;
        inflater = LayoutInflater.from(contexto);
    }

    @Override
    public int getCount() {
        return usuarios.size();
    }

    @Override
    public Object getItem(int position) {
        return usuarios.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vista = convertView;
        if (vista == null) {
            vista = inflater.inflate(R.layout.activity_ranking2, null);
        }

        TextView nombre = vista.findViewById(R.id.t2);
        TextView puntaje = vista.findViewById(R.id.t1);

        UsuarioRanking usuario = usuarios.get(position);

        nombre.setText(usuario.getNombre());
        puntaje.setText(String.valueOf(usuario.getPuntaje()));

        return vista;
    }
}
