package com.example.terminously.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Termino {

    @SerializedName("IdTermino")
    @Expose
    public int idTermino;
    @SerializedName("IdDiccionario")
    @Expose
    public int idDiccionario;
    @SerializedName("Imagen")
    @Expose
    public String imagen;

    public int getIdTermino() {
        return idTermino;
    }

    public void setIdTermino(int idTermino) {
        this.idTermino = idTermino;
    }

    public int getIdDiccionario() {
        return idDiccionario;
    }

    public void setIdDiccionario(int idDiccionario) {
        this.idDiccionario = idDiccionario;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
