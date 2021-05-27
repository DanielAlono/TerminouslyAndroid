package com.example.terminously.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.terminously.MainActivity;
import com.example.terminously.R;
import com.example.terminously.api.ApiService;
import com.example.terminously.api.CacheService;
import com.example.terminously.pojos.Diccionario;
import com.example.terminously.pojos.Ficha;
import com.example.terminously.pojos.Termino;
import com.example.terminously.recyclers.adapters.AdaptadorDiccionarios;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiccionariosFragment extends Fragment {
    //Parámetros de la vista
    RecyclerView recyclerView;
    AdaptadorDiccionarios adaptador;
    EditText buscador;

    //Fragments
    FragmentManager fm;
    FragmentTransaction ft;
    TerminosFragment terminosFragment;

    String nombreDiccionario;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        //Montamos la vista
        View rootView = inflater.inflate(R.layout.vista_diccionarios,container,false);
        recyclerView = rootView.findViewById(R.id.diccionariosRecycler);
        buscador = rootView.findViewById(R.id.buscador);

        adaptador = new AdaptadorDiccionarios(getActivity(), CacheService.GetInstance().getDICCIONARIO());
        cargarMiOnClick();
        recyclerView.setAdapter(adaptador);
        recyclerView.setHasFixedSize(true);

        //Filtrar la lista por Diccionarios
        buscador.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<Diccionario> subLista = new ArrayList<>();
                adaptador = new AdaptadorDiccionarios(getActivity(),CacheService.GetInstance().getDICCIONARIO());
                cargarMiOnClick();
                recyclerView.setAdapter(adaptador);
                if(!TextUtils.isEmpty(buscador.getText().toString())){
                    int longitud = buscador.getText().toString().length();
                    for(Diccionario x : CacheService.GetInstance().getDICCIONARIO()){
                        try {
                            if(buscador.getText().toString().compareTo(x.getNombre().substring(0,longitud))==0){
                                subLista.add(x);
                            }
                        }catch (StringIndexOutOfBoundsException e){}
                    }
                    adaptador = new AdaptadorDiccionarios(getActivity(),subLista);
                    adaptador.miOnClick(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            List<Termino> terminosPorDiccionario = new ArrayList<>();

                            nombreDiccionario = subLista.get(recyclerView.getChildAdapterPosition(v)).getNombre();
                            int idDiccionario = subLista.get(recyclerView.getChildAdapterPosition(v)).getIdDiccionario();
                            for(Termino termino : CacheService.GetInstance().getTERMINOS()){
                                if(termino.idDiccionario == idDiccionario)
                                    terminosPorDiccionario.add(termino);
                            }
                            CacheService.GetInstance().setTERMINOS_DICCIONARIO(terminosPorDiccionario);
                            cargarVistaTerminos(nombreDiccionario);
                        }
                    });
                    recyclerView.setAdapter(adaptador);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        return rootView;
    }
    /**Método para cargar el Fragment que contiene la lista de términos*/
    private void cargarTerminosFragment(){
        Bundle result = new Bundle();
        result.putString("DICCIONARIO", nombreDiccionario);
        fm = getActivity().getSupportFragmentManager();
        ft = fm.beginTransaction();
        terminosFragment = new TerminosFragment();
        terminosFragment.setArguments(result);
        ft.replace(R.id.fragmentDiccionario, terminosFragment);
        fm.popBackStack();
        ft.addToBackStack(null);
        ft.commit();
    }
    /**Método para obtener las Fichas de nuestro API*/
    private void cargarVistaTerminos(String nombreDiccionario){
        Call<List<Ficha>> call = MainActivity.apiService.getFichas();
        call.enqueue(new Callback<List<Ficha>>() {
            @Override
            public void onResponse(Call<List<Ficha>> call, Response<List<Ficha>> response) {
                if(response.isSuccessful()){
                    CacheService.GetInstance().setFICHAS(response.body());
                    //Cargamos el fragment
                    cargarTerminosFragment();
                } else {
                    Toast.makeText(getActivity(),"Error en el formato de respuesta", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Ficha>> call, Throwable t) {
                Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    /**Método para asignar View.OnClickListener a nuestro adaptador*/
    private void cargarMiOnClick(){
        adaptador.miOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Termino> terminosPorDiccionario = new ArrayList<>();

                nombreDiccionario = CacheService.GetInstance().getDICCIONARIO().get(recyclerView.getChildAdapterPosition(v)).getNombre();
                int idDiccionario = CacheService.GetInstance().getDICCIONARIO().get(recyclerView.getChildAdapterPosition(v)).getIdDiccionario();
                for(Termino termino : CacheService.GetInstance().getTERMINOS()){
                    if(termino.idDiccionario == idDiccionario)
                        terminosPorDiccionario.add(termino);
                }
                CacheService.GetInstance().setTERMINOS_DICCIONARIO(terminosPorDiccionario);
                cargarVistaTerminos(nombreDiccionario);
            }
        });
    }
}
