package com.example.terminously;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.terminously.api.ApiService;
import com.example.terminously.fragments.DiccionariosFragment;
;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    //Fragments
    FragmentManager fm;
    FragmentTransaction ft;
    DiccionariosFragment diccionariosFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Cargamos la lista de diccionarios
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        diccionariosFragment = new DiccionariosFragment();
        fm.popBackStack();
        ft.replace(R.id.vistaDiccionarios, diccionariosFragment);
        ft.commit();
    }

    public static ApiService crearRetrofit(){
        String url = "http://pdam01b.iesdoctorbalmis.info/apirest_terminously/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return  retrofit.create(ApiService.class);
    }
}