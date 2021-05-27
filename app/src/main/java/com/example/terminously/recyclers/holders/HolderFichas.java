package com.example.terminously.recyclers.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.terminously.R;
import com.example.terminously.pojos.Ficha;

public class HolderFichas extends RecyclerView.ViewHolder {

    TextView nombreIdioma;
    ImageView imagenIdioma;
    public HolderFichas(@NonNull View itemView) {
        super(itemView);
        nombreIdioma = itemView.findViewById(R.id.nombreIdioma);
        imagenIdioma = itemView.findViewById(R.id.imagenIdioma);
    }
    public void bind(Ficha ficha){
        switch (ficha.idIdioma){
            case "AR":
                imagenIdioma.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ar));
                nombreIdioma.setText("Árabe");
                break;
            case "ARG":
                imagenIdioma.setImageDrawable(itemView.getResources().getDrawable(R.drawable.arg));
                nombreIdioma.setText("Argentina");
            case "CN":
                imagenIdioma.setImageDrawable(itemView.getResources().getDrawable(R.drawable.cn));
                nombreIdioma.setText("Chino");
                break;
            case "DE":
                imagenIdioma.setImageDrawable(itemView.getResources().getDrawable(R.drawable.de));
                nombreIdioma.setText("Alemán");
                break;
            case "EN":
                imagenIdioma.setImageDrawable(itemView.getResources().getDrawable(R.drawable.en));
                nombreIdioma.setText("Inglés");
                break;
            case "ES":
                imagenIdioma.setImageDrawable(itemView.getResources().getDrawable(R.drawable.es));
                nombreIdioma.setText("Español");
                break;
            case "FR":
                imagenIdioma.setImageDrawable(itemView.getResources().getDrawable(R.drawable.fr));
                nombreIdioma.setText("Francés");
                break;
            case "IT":
                imagenIdioma.setImageDrawable(itemView.getResources().getDrawable(R.drawable.it));
                nombreIdioma.setText("Italiano");
                break;
            case "JP":
                imagenIdioma.setImageDrawable(itemView.getResources().getDrawable(R.drawable.jp));
                nombreIdioma.setText("Japonés");
                break;
            case "PT":
                imagenIdioma.setImageDrawable(itemView.getResources().getDrawable(R.drawable.pt));
                nombreIdioma.setText("Portugués");
                break;
            case "RU":
                imagenIdioma.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ru));
                nombreIdioma.setText("Ruso");
                break;
        }
    }
}
