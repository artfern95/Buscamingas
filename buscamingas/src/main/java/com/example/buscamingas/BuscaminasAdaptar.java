package com.example.buscamingas;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buscamingas.Celdas.Casilla;

public class BuscaminasAdaptar extends RecyclerView.Adapter<BuscaminasAdaptar.CasillaHolder> {

    private LayoutInflater inflater;
    private Context context;

    public BuscaminasAdaptar(Context context) {

        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    //Crea vista
    @NonNull
    @Override
    public CasillaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);
        CasillaHolder exampleViewHolder=new CasillaHolder(v);
        return exampleViewHolder;
    }

    //Rellena vista
    @Override
    public void onBindViewHolder(@NonNull CasillaHolder holder, int position) {

        holder.casilla.setPosicion(position);
    }

    @Override
    public int getItemCount() {

        return Logica.getInstance().getLargo() * Logica.getInstance().getAncho();
    }

    //Class estática por recomendación del API
    public class CasillaHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private Casilla casilla;

        public CasillaHolder(View itemView) {
            super(itemView);

            casilla = itemView.findViewById(R.id.Casilla);
            casilla.setOnLongClickListener(this);
            casilla.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Toast.makeText(context,"Onclick",Toast.LENGTH_SHORT).show();
        }

        @Override
        public boolean onLongClick(View v) {

            Toast.makeText(context,"OnLongClick",Toast.LENGTH_SHORT).show();
            return false;
        }

    }
}
