package com.example.terminously.recyclers.holders;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.terminously.MainActivity;
import com.example.terminously.R;
import com.example.terminously.api.CacheService;
import com.example.terminously.fragments.FichasFragment;
import com.example.terminously.fragments.TerminosFragment;
import com.example.terminously.pojos.Ficha;
import com.example.terminously.pojos.Termino;
import com.example.terminously.recyclers.adapters.AdaptadorFichas;

import java.util.ArrayList;
import java.util.List;

public class HolderTerminos extends RecyclerView.ViewHolder {

    TextView nombreTermino, numeroFichas, nombreDiccionario;
    RecyclerView recyclerView;
    AdaptadorFichas adaptador;
    List<Ficha> fichaList;

    public HolderTerminos(@NonNull View itemView) {
        super(itemView);
        nombreDiccionario = itemView.findViewById(R.id.nombreDiccionario_terminos);
        nombreTermino = itemView.findViewById(R.id.nombreTermino);
        recyclerView = itemView.findViewById(R.id.fichasRecycler);
    }

    public void bind(Termino termino) {
        //Array para llenar la lista de fichas
        fichaList = new ArrayList<>();
        //Recorremos todas las fichas
        for (Ficha ficha : CacheService.GetInstance().getFICHAS()) {
            //Si coincide, la añadimos a la lista
            if (ficha.idTermino == termino.idTermino) {
                fichaList.add(ficha);
                if (ficha.idIdioma.contains("ES"))
                    nombreTermino.setText(ficha.nombre);
            }
        }
        //Le pasamos la lista al adaptador
        adaptador = new AdaptadorFichas(itemView.getContext(), fichaList);
        //Asignamos el evento OnClick para cargar el fragment de la ficha al pulsar
        adaptador.miOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarFichaFragment(fichaList.get(recyclerView.getChildAdapterPosition(v)).getIdFicha(),termino.getImagen());
            }
        });
        recyclerView.setAdapter(adaptador);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(itemView.getContext(), 4));
    }
    private void cargarFichaFragment(int idFicha, String urlImagen) {
        //Fragments
        FragmentManager fm;
        FragmentTransaction ft;
        FichasFragment fragment;

        Bundle result = new Bundle();
        result.putInt("FICHA", idFicha);
        result.putString("IMAGEN",urlImagen);

        fragment = new FichasFragment();
        fm = TerminosFragment.fragmentManager;
        ft = fm.beginTransaction();
        fragment.setArguments(result);
        ft.replace(R.id.fragmentDiccionario, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}
