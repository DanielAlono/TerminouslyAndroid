package com.example.terminously;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Toast;

import com.example.terminously.api.ApiService;
import com.example.terminously.api.CacheService;
import com.example.terminously.fragments.DiccionariosFragment;
import com.example.terminously.pojos.Diccionario;
import com.example.terminously.pojos.Ficha;
import com.example.terminously.pojos.Termino;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    //Fragments
    FragmentManager fm;
    FragmentTransaction ft;
    DiccionariosFragment diccionariosFragment;

    public static ApiService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiService = crearRetrofit();

        //Cargamos en nuestra clase Singleton la información de diccionarios del API
        Call<List<Diccionario>> call = apiService.getDiccionarios();
        call.enqueue(new Callback<List<Diccionario>>() {
            @Override
            public void onResponse(Call<List<Diccionario>> call, Response<List<Diccionario>> response) {
                if(response.isSuccessful()){
                    CacheService.GetInstance().setDICCIONARIO(response.body());
                    obtenerTerminosAPI();
                }else {
                    Toast.makeText(getApplicationContext(),"Error en el formato de respuesta", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Diccionario>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    /**Método para cargar nuestro Fragment que contiene la lista de diccionarios**/
    private void cargarDiccionariosFragment(){
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        diccionariosFragment = new DiccionariosFragment();
        fm.popBackStack();
        ft.replace(R.id.fragmentDiccionario, diccionariosFragment);
        ft.commit();
    }
    /**Método para crear conexión con nuestro API*/
    private ApiService crearRetrofit(){
        String url = "http://pdam01b.iesdoctorbalmis.info/apirest_terminously/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return  retrofit.create(ApiService.class);
    }
    /**Llamada al API para obtener los términos en nuestro Singleton*/
    private void obtenerTerminosAPI(){
        Call<List<Termino>> call = apiService.getTerminos();
        call.enqueue(new Callback<List<Termino>>() {
            @Override
            public void onResponse(Call<List<Termino>> call, Response<List<Termino>> response) {
                if(response.isSuccessful()){
                    CacheService.GetInstance().setTERMINOS(response.body());
                    //Una vez obtenemos los diccionarios y términos cargamos la vista
                    cargarDiccionariosFragment();
                }else {
                    Toast.makeText(getApplicationContext(),"Error en el formato de respuesta", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Termino>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}