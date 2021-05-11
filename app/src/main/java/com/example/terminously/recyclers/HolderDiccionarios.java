package com.example.terminously.recyclers;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.terminously.MainActivity;
import com.example.terminously.R;
import com.example.terminously.pojos.Diccionario;
import com.example.terminously.pojos.Termino;

public class HolderDiccionarios extends RecyclerView.ViewHolder {

    TextView nombreDiccionario, numeroTerminos;
    public HolderDiccionarios(View itemView) {
        super(itemView);
        nombreDiccionario = itemView.findViewById(R.id.nombreDiccionario);
        numeroTerminos = itemView.findViewById(R.id.numeroTerminos);
    }

    public void bind(Diccionario diccionario){
        nombreDiccionario.setText(diccionario.getNombre());
        int cantidadTerminos = 0;
        numeroTerminos.setText("Terminos: " + cantidadTerminos);
    }
}
