package com.example.terminously.recyclers.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.terminously.R;
import com.example.terminously.pojos.Ficha;
import com.example.terminously.recyclers.holders.HolderFichas;

import java.util.List;

public class AdaptadorFichas extends RecyclerView.Adapter implements View.OnClickListener {
    Context context;
    HolderFichas holder;
    List<Ficha> fichas;
    View.OnClickListener clickListener;

    public AdaptadorFichas(Context context, List<Ficha> fichas) {
        this.context = context;
        this.fichas = fichas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fichas_recycler, parent, false);
        view.setOnClickListener(this);
        holder = new HolderFichas(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((HolderFichas) holder).bind(fichas.get(position));
    }

    @Override
    public int getItemCount() {
        if (fichas != null) {
            return fichas.size();
        }
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
