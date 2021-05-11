package com.example.terminously.recyclers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.terminously.R;
import com.example.terminously.pojos.Diccionario;

import java.util.List;

public class AdaptadorDiccionarios extends RecyclerView.Adapter {

    Context context;
    HolderDiccionarios holder;
    List<Diccionario> diccionarios;
    public AdaptadorDiccionarios(Context context, List<Diccionario> diccionarios){
        this.context = context;
        this.diccionarios = diccionarios;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.diccionarios_recycler,parent,false);
        holder = new HolderDiccionarios(view);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((HolderDiccionarios)holder).bind(diccionarios.get(position));
    }

    @Override
    public int getItemCount() {
        if(diccionarios != null)
            return diccionarios.size();
        return 0;
    }
}
