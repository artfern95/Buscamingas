package com.example.buscamingas;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.buscamingas.Celdas.Casilla;

import java.util.ArrayList;
import java.util.Random;

public class BuscaminasAdaptar extends RecyclerView.Adapter<BuscaminasAdaptar.BuscaminasItem> {

    private LayoutInflater inflater;
    private Context context;

    private int Largo;
    private int Ancho;
    private int NBombas;
    private int contBombas;

    //Arraylist con las vistas del tablero
    //para controlar los estados y hacerlas visibles
    //o al inversa
    private ArrayList<Casilla> mapBoom;

    //Array con las bombas y los valores de las casillas
    //dichos valores son el número de bombas que tiene cerca
    private int [][] tablero ;

    public BuscaminasAdaptar(Context context) {

        this.context = context;
        inflater = LayoutInflater.from(context);
        mapBoom = new ArrayList<>();

        Largo = 8;
        Ancho = 8;
        NBombas = 12;
        contBombas = NBombas;

        CrearTablero();
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
        holder.casilla.setValor(getValorCasilla(position));
    }

    public void Click(int x, int y){

        if((x>=0) && (x<Largo) && (y>=0) && (y<Ancho) &&
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

                finJuego();
            }
        }
    }

    public void LongCLick(int x, int y){

        //Comprobamos que no este con click o longclick
        //if((!getCasilla(x, y).isOnlongclick()) && (!getCasilla(x, y).isVisible()) && (!getCasilla(x, y).isClick())){

            //Si hay bomba
            if(getCasilla(x, y).getBomba() == true){//Hay bomba, se marca la bomba

                //Descubrimos la casilla
                getCasilla(x, y).setVisible();
                contBombas--;//Restamos al contador de bombas restantes

                //Revisamos el numero de bombas que quedan
                if(contBombas == 0){
                    //FIN DEL JUEGO
                    finJuego();
                }
            }

            //Si no hay bomba: fin del juego
            else{

                finJuego();
            }
        //}
    }

    public void DialogFinDelJuego(){

        AlertDialog.Builder alertdialogBuilder = new AlertDialog.Builder(context);

        alertdialogBuilder.setTitle("Fin del juego");

        alertdialogBuilder.setMessage("¿Quieres volver a jugar?").setCancelable(false);
        alertdialogBuilder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //Reinicas el juego
                //Logica.getInstance().IniciarJuego();
            }
        });

        alertdialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //Salir
                System.exit(0);
            }
        });

        alertdialogBuilder.show();
    }

    public void finJuego(){

        //Recorre array de casillas comprobando que tenga boma para hacerlas visibles

        for(int i=0;i<mapBoom.size();i++){

            if(mapBoom.get(i).getBomba() == true){

                mapBoom.get(i).setVisible();
            }
        }

        for(int i = 0; i < mapBoom.size(); i ++) {

            mapBoom.get(i).setOnClickListener(null);
            mapBoom.get(i).setOnLongClickListener(null);
        }

        DialogFinDelJuego();
    }

    public int getValorCasilla(int pos){

        int x = pos % Largo;
        int y = pos / Largo;

        return tablero[x][y];
    }

    public Casilla getCasilla(int x, int y){

        int pos = y * Largo + x;

        return mapBoom.get(pos);
    }

    public int getLargo() {

        return Largo;
    }

    public void setLargo(int largo) {

        Largo = largo;
    }

    public int getAncho() {

        return Ancho;
    }

    public void setAncho(int ancho) {

        Ancho = ancho;
    }

    public int getContBombas() {

        return contBombas;
    }

    public void setContBombas(int contBombas) {

        this.contBombas = contBombas;
    }

    public void CrearTablero() {

        tablero = new int[Largo][Ancho];

        Random num = new Random();

        int nbombas = NBombas;

        while(nbombas > 0){

            //Posiciones x,y para colocar la bomba de forma aleatoria
            int x = num.nextInt(Largo);
            int y = num.nextInt(Ancho);

            /*Solo pondra en aquellas posiciones que esten libres
             * de lo conrtrario el numero de bombas no disminuye*/
            if (tablero[x][y] != -1) {

                tablero[x][y] = -1;
                nbombas --;
            }
        }


        /*Recorremos posicion por posicion para establecer su valor
        Este valor depende del numero de bombas que tenga cerca, una casiila
        en todas las direcciones*/
        for (int i = 0; i < Largo; i++) {

            for (int e = 0; e < Ancho; e++) {

                tablero[i][e] = BuscarMinas(i,e);
            }
        }


        //
        String valor = "";

        for(int i = 0; i < Ancho; i ++){

            valor = " | ";

            for(int e = 0; e < Largo; e ++){

                valor = valor + String.valueOf(tablero[e][i]).replace("9","B") + " | ";
            }
            //Log.i("Bombas",valor);

            valor += "\n";

            System.out.print(valor);
        }
    }

    private int BuscarMinas(int x, int y){

        if(tablero[x][y] == -1){

            return -1;
        }

        else {

            int Nbombascercanas = 0;

            for (int i = x - 1; i <= x + 1; i++) {

                for (int e = y - 1; e <= y + 1; e++) {

                    if(i < Largo && i >= 0 && e < Ancho && e >= 0){

                        if(tablero[i][e] == -1){

                            Nbombascercanas++;
                        }
                    }
                }
            }

            return Nbombascercanas;
        }
    }

    @Override
    public int getItemCount() {

        //Calculamos el numero de elementos a mostrar en el tablero
        return Largo * Ancho;
    }

    public class BuscaminasItem extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        //Casilla individual
        private Casilla casilla;

        public BuscaminasItem(View itemView) {
            super(itemView);

            casilla = itemView.findViewById(R.id.Casilla);
            mapBoom.add(casilla);

            //Establecemos los listener
            casilla.setOnLongClickListener(this);
            casilla.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Click(casilla.getXp(),casilla.getYp());
            //Respuesta al hacer click en la celda
            //Toast.makeText(context,"Onclick",Toast.LENGTH_SHORT).show();
        }

        @Override
        public boolean onLongClick(View v) {

            LongCLick(casilla.getXp(),casilla.getYp());
            //Respuesta al hacer long click en la celda
            //Toast.makeText(context,"OnLongClick",Toast.LENGTH_SHORT).show();
            return false;
        }

    }
}
