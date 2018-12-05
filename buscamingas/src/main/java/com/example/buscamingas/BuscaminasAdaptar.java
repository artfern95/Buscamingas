package com.example.buscamingas;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.buscamingas.Celdas.Casilla;

import java.util.ArrayList;

public class BuscaminasAdaptar extends RecyclerView.Adapter<BuscaminasAdaptar.BuscaminasItem> {

    private LayoutInflater inflater;
    private Context context;
    private ArrayList<Casilla> casillas;

    public BuscaminasAdaptar(Context context) {

        this.context = context;
        inflater = LayoutInflater.from(context);
        casillas = new ArrayList<>();
    }

    //Crea vista
    @NonNull
    @Override
    public BuscaminasItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View celda = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);
        BuscaminasItem exampleViewHolder = new BuscaminasItem(celda);
        return exampleViewHolder;
    }

    //Rellena vista
    @Override
    public void onBindViewHolder(@NonNull BuscaminasItem holder, int position) {

        //posicion y a la vez coordenadas X e Y
        holder.casilla.setPosicion(position);

        //Valor de la celda: si es mina o el número de minas cercas
        holder.casilla.setValor(Logica.getInstance().getValorCelda(position));
    }

    public void Click(int x, int y){

        if((x>=0) && (x<Logica.getInstance().getLargo()) && (y>=0) && (y<Logica.getInstance().getAncho()) &&
                !getCasilla(x,y).isClick() && !getCasilla(x,y).isVisible() && !getCasilla(x,y).isOnlongclick()){

            getCasilla(x,y).setClick();

            //si es celda libre 'valor = 0'
            if(getCasilla(x,y).getValor() == 0){

                for(int i = -1; i <= 1; i ++){

                    for(int e = -1; e <= 1; e ++){

                        if(i != e) {
                            Click(x + i, y + e);
                        }
                    }
                }
            }

            //Si es bomba, en este caso seria fin del juego
            if(getCasilla(x,y).getValor() == -1){

            }
        }
    }

    public void LongCLick(){

    }

    public Casilla getCasilla(int x, int y){

        int pos = y * Logica.getInstance().getLargo() + x;

        return casillas.get(pos);
    }

    @Override
    public int getItemCount() {

        //Calculamos el numero de elementos a mostrar en el tablero
        return Logica.getInstance().getLargo() * Logica.getInstance().getAncho();
    }

    public class BuscaminasItem extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        //Casilla individual
        private Casilla casilla;

        public BuscaminasItem(View itemView) {
            super(itemView);

            casilla = itemView.findViewById(R.id.Casilla);
            casillas.add(casilla);

            //Establecemos los listener
            casilla.setOnLongClickListener(this);
            casilla.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            casilla.setVisible();
            casilla.setClick();
            //Respuesta al hacer click en la celda
            Toast.makeText(context,"Onclick",Toast.LENGTH_SHORT).show();
        }

        @Override
        public boolean onLongClick(View v) {

            //Respuesta al hacer long click en la celda
            Toast.makeText(context,"OnLongClick",Toast.LENGTH_SHORT).show();
            return false;
        }

    }
}
