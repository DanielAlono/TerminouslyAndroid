package com.example.terminously.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Idioma {

    @SerializedName("IdIdioma")
    @Expose
    public String idIdioma;
    @SerializedName("Nombre")
    @Expose
    public String nombre;
    @SerializedName("Imagen")
    @Expose
    public String imagen;

    public String getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(String idIdioma) {
        this.idIdioma = idIdioma;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
