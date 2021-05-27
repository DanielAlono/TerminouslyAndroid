package com.example.terminously.api;

import android.content.res.Resources;

import com.example.terminously.pojos.Diccionario;
import com.example.terminously.pojos.Ficha;
import com.example.terminously.pojos.Idioma;
import com.example.terminously.pojos.Termino;

import java.util.List;

public class CacheService {
    private List<Diccionario> DICCIONARIO;
    private List<Termino> TERMINOS;
    private List<Ficha> FICHAS;
    private List<Idioma> IDIOMA;

    private List<Termino> TERMINOS_DICCIONARIO;
    private List<Ficha> FICHA_DICCIONARIO;

    private CacheService(){}

    private static CacheService _instance;
    public static CacheService GetInstance(){
        if(_instance == null){
            _instance = new CacheService();
        }
        return _instance;
    }

    public List<Diccionario> getDICCIONARIO() {
        return DICCIONARIO;
    }

    public void setDICCIONARIO(List<Diccionario> DICCIONARIO) {
        this.DICCIONARIO = DICCIONARIO;
    }

    public List<Termino> getTERMINOS() {
        return TERMINOS;
    }

    public void setTERMINOS(List<Termino> TERMINOS) {
        this.TERMINOS = TERMINOS;
    }

    public List<Ficha> getFICHAS() {
        return FICHAS;
    }

    public void setFICHAS(List<Ficha> FICHAS) {
        this.FICHAS = FICHAS;
    }
    public List<Idioma> getIDIOMA() {
        return IDIOMA;
    }

    public void setIDIOMA(List<Idioma> IDIOMA) {
        this.IDIOMA = IDIOMA;
    }

    public List<Termino> getTERMINOS_DICCIONARIO() {
        return TERMINOS_DICCIONARIO;
    }

    public void setTERMINOS_DICCIONARIO(List<Termino> TERMINOS_DICCIONARIO) {
        this.TERMINOS_DICCIONARIO = TERMINOS_DICCIONARIO;
    }
}
