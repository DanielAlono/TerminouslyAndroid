package com.example.terminously.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.terminously.R;
import com.example.terminously.api.CacheService;
import com.example.terminously.pojos.Ficha;
import com.squareup.picasso.Picasso;

public class FichasFragment extends Fragment{

    private Ficha ficha;
    private String urlImagen;
    private Bundle bundle;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int idFicha = 0;
        bundle = this.getArguments();
        if(bundle != null){
            idFicha = bundle.getInt("FICHA");
            urlImagen = bundle.getString("IMAGEN");
        }
        for(Ficha x : CacheService.GetInstance().getFICHAS())
            if(x.idFicha == idFicha)
                ficha = x;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.vista_ficha,container,false);
        if(ficha != null){
            ((TextView) rootView.findViewById(R.id.nombreFicha_vista)).setText(ficha.getNombre());
            ((TextView) rootView.findViewById(R.id.categoriaGramatical)).setText(
                    Ficha.CategoriaGramatical.values()[ficha.getCategoriaGramatical()].toString());
            ((TextView) rootView.findViewById(R.id.definicion)).setText(ficha.getDefinicion());
            ((TextView) rootView.findViewById(R.id.fuenteDefinicion)).setText(ficha.getFuenteDefinicion());
            ((TextView) rootView.findViewById(R.id.registro)).setText(
                    Ficha.CategoriaGramatical.values()[ficha.getRegistro()].toString());
            ((TextView) rootView.findViewById(R.id.comentario)).setText(ficha.getComentario());
            if(!urlImagen.isEmpty())
                Picasso.get().load(urlImagen).into((ImageView) rootView.findViewById(R.id.imagenTermino));
        }
        return rootView;
    }
}
