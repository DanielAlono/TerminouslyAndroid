package com.example.terminously.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.terminously.R;
import com.example.terminously.api.CacheService;
import com.example.terminously.pojos.Diccionario;
import com.example.terminously.pojos.Ficha;
import com.example.terminously.pojos.Termino;
import com.example.terminously.recyclers.adapters.AdaptadorTerminos;

import java.util.ArrayList;
import java.util.List;

public class TerminosFragment extends Fragment {
    RecyclerView recyclerView;
    EditText buscador;
    TextView nombreDiccionario;
    AdaptadorTerminos adaptador;

    public static FragmentManager fragmentManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.vista_terminos, container, false);
        recyclerView = rootView.findViewById(R.id.terminosRecycler);
        nombreDiccionario = rootView.findViewById(R.id.nombreDiccionario_terminos);
        buscador = rootView.findViewById(R.id.buscador);
        fragmentManager = getActivity().getSupportFragmentManager();

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            nombreDiccionario.setText(bundle.getString("DICCIONARIO", "DICCIONARIO"));
        }
        //Adaptador + recycler
        adaptador = new AdaptadorTerminos(getActivity(), CacheService.GetInstance().getTERMINOS_DICCIONARIO());
        recyclerView.setAdapter(adaptador);
        recyclerView.setHasFixedSize(true);

        //Filtrar lista términos
        buscador.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                List<Termino> subLista = new ArrayList<>();
                adaptador = new AdaptadorTerminos(getActivity(), CacheService.GetInstance().getTERMINOS_DICCIONARIO());
                recyclerView.setAdapter(adaptador);
                if (!TextUtils.isEmpty(buscador.getText().toString())) {
                    int longitud = buscador.getText().toString().length();
                    for (Termino x : CacheService.GetInstance().getTERMINOS_DICCIONARIO()) {
                        try {
                            //Asignamos nombre al término
                            String nombreTermino = "";
                            for (Ficha ficha : CacheService.GetInstance().getFICHAS()) {
                                if (ficha.idTermino == x.idTermino) {
                                    if (ficha.idIdioma.contains("ES"))
                                        nombreTermino = ficha.nombre;
                                }
                            }
                            if (buscador.getText().toString().compareTo(nombreTermino.substring(0, longitud)) == 0) {
                                subLista.add(x);
                            }

                        } catch (StringIndexOutOfBoundsException e) {
                        }
                        adaptador = new AdaptadorTerminos(getActivity(), subLista);
                        recyclerView.setAdapter(adaptador);
                    }
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        return rootView;
    }
}
