package com.example.terminously.recyclers.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.terminously.R;
import com.example.terminously.pojos.Termino;
import com.example.terminously.recyclers.holders.HolderTerminos;

import java.util.List;

public class AdaptadorTerminos extends RecyclerView.Adapter {

    Context context;
    HolderTerminos holder;
    List<Termino> terminos;
    public AdaptadorTerminos(Context context, List<Termino> terminos){
        this.context = context;
        this.terminos = terminos;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.terminos_recycler,parent,false);
        holder = new HolderTerminos(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((HolderTerminos)holder).bind(terminos.get(position));
    }

    @Override
    public int getItemCount() {
        if(terminos != null){
            return terminos.size();
        }
        return 0;
    }
}
