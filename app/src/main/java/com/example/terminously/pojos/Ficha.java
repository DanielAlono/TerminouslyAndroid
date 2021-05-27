package com.example.terminously.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ficha {
    public enum CategoriaGramatical{
        Adjetivo, Adverbio, Conjunción,
        Determinante, Interjección, Preposición,
        Pronombre, Sustantivo, Verbo
    }
    public enum Registro{
        Latinismo, Cultismo, Tecnicismo, Formal, Coloquial, Vulgar
    }

    @SerializedName("IdFicha")
    @Expose
    public int idFicha;
    @SerializedName("IdTermino")
    @Expose
    public int idTermino;
    @SerializedName("Nombre")
    @Expose
    public String nombre;
    @SerializedName("CategoriaGramatical")
    @Expose
    public int categoriaGramatical;
    @SerializedName("Definicion")
    @Expose
    public String definicion;
    @SerializedName("FuenteDefinicion")
    @Expose
    public String fuenteDefinicion;
    @SerializedName("Registro")
    @Expose
    public int registro;
    @SerializedName("Comentario")
    @Expose
    public String comentario;
    @SerializedName("IdIdioma")
    @Expose
    public String idIdioma;

    public int getIdFicha() {
        return idFicha;
    }

    public void setIdFicha(int idFicha) {
        this.idFicha = idFicha;
    }

    public int getIdTermino() {
        return idTermino;
    }

    public void setIdTermino(int idTermino) {
        this.idTermino = idTermino;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCategoriaGramatical() {
        return categoriaGramatical;
    }

    public void setCategoriaGramatical(int categoriaGramatical) {
        this.categoriaGramatical = categoriaGramatical;
    }

    public String getDefinicion() {
        return definicion;
    }

    public void setDefinicion(String definicion) {
        this.definicion = definicion;
    }

    public String getFuenteDefinicion() {
        return fuenteDefinicion;
    }

    public void setFuenteDefinicion(String fuenteDefinicion) {
        this.fuenteDefinicion = fuenteDefinicion;
    }

    public int getRegistro() {
        return registro;
    }

    public void setRegistro(int registro) {
        this.registro = registro;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(String idIdioma) {
        this.idIdioma = idIdioma;
    }
}
