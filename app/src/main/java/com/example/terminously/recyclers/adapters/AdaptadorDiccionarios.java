package com.example.terminously.recyclers.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.terminously.R;
import com.example.terminously.pojos.Diccionario;
import com.example.terminously.recyclers.holders.HolderDiccionarios;

import java.util.List;

public class AdaptadorDiccionarios extends RecyclerView.Adapter implements View.OnClickListener {

    Context context;
    HolderDiccionarios holder;
    List<Diccionario> diccionarios;
    View.OnClickListener clickListener;
    public AdaptadorDiccionarios(Context context, List<Diccionario> diccionarios){
        this.context = context;
        this.diccionarios = diccionarios;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.diccionarios_recycler,parent,false);
        holder = new HolderDiccionarios(view);
        view.setOnClickListener(this);
        return holder;
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

    public void miOnClick(View.OnClickListener clickListener){
        this.clickListener = clickListener;
    }
    @Override
    public void onClick(View v) {
        if(clickListener != null){
            clickListener.onClick(v);
        }
    }
}
