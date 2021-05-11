package com.example.terminously.api;

import com.example.terminously.pojos.Diccionario;
import com.example.terminously.pojos.Ficha;
import com.example.terminously.pojos.Idioma;
import com.example.terminously.pojos.Termino;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApiService {
    @GET("Diccionarios")
    @Headers({"Accept: application/json","Content-Type: application/json"})
    Call<List<Diccionario>> getDiccionarios();

    @GET("Terminos")
    @Headers({"Accept: application/json","Content-Type: application/json"})
    Call<List<Termino>> getTerminos();

    @GET("Fichas")
    @Headers({"Accept: application/json","Content-Type: application/json"})
    Call<List<Ficha>> getFichas();

    @GET("Idioma")
    @Headers({"Accept: application/json","Content-Type: application/json"})
    Call<List<Idioma>> getIdiomas();
}
