package com.example.terminously.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Diccionario {

    @SerializedName("IdDiccionario")
    @Expose
    public int idDiccionario;
    @SerializedName("Nombre")
    @Expose
    public String nombre;

    public int getIdDiccionario() {
        return idDiccionario;
    }

    public void setIdDiccionario(int idDiccionario) {
        this.idDiccionario = idDiccionario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
