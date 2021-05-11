package com.example.terminously.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.terminously.MainActivity;
import com.example.terminously.R;
import com.example.terminously.api.ApiService;
import com.example.terminously.pojos.Diccionario;
import com.example.terminously.recyclers.AdaptadorDiccionarios;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiccionariosFragment extends Fragment {
    //Parámetros de la vista
    RecyclerView recyclerView;
    AdaptadorDiccionarios adaptador;
    List<Diccionario> diccionarios;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        //Montamos la vista
        View rootView = inflater.inflate(R.layout.activity_main,container,false);
        ApiService apiService = MainActivity.crearRetrofit();
        Call<List<Diccionario>> call = apiService.getDiccionarios();
        call.enqueue(new Callback<List<Diccionario>>() {
            @Override
            public void onResponse(Call<List<Diccionario>> call, Response<List<Diccionario>> response) {
                if(response.isSuccessful()){
                    diccionarios = response.body();
                }else {
                    Toast.makeText(getContext(),"Error en el formato de respuesta", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Diccionario>> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
        recyclerView = rootView.findViewById(R.id.diccionariosRecycler);
        adaptador = new AdaptadorDiccionarios(getContext(),diccionarios);
        recyclerView.setAdapter(adaptador);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        return rootView;
    }
}
