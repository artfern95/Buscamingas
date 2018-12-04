package com.example.buscamingas;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.buscamingas.Celdas.Casilla;

import java.util.ArrayList;
import java.util.Random;

public class Logica {

    private static final Logica ourInstance = new Logica();

    private int Largo;
    private int Ancho;
    private int NBombas;

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private BuscaminasAdaptar buscaminasAdaptar;

    private ArrayList<Casilla> mapBoom;
    private int [][] tablero ;

    private Context context;

    public static Logica getInstance() {
        return ourInstance;
    }

    private Logica() {

    }

    public void IniciarJuego(Context context, RecyclerView recyclerView){

        this.recyclerView = recyclerView;
        this.context = context;

        Largo = 8;
        Ancho = 8;
        NBombas = 12;

        mapBoom = new ArrayList<>();

        CrearTablero();

        layoutManager = new GridLayoutManager(context,Largo);
        buscaminasAdaptar = new BuscaminasAdaptar(context);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(buscaminasAdaptar);

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

                tablero[i][e] = CalculaMinasCercanas(i,e);
            }
        }

        String valor = "";

        for(int i = 0 ; i < Ancho ; i ++){

            valor = " | ";

            for(int e = 0 ; e < Largo ; e ++){

                valor = valor + String.valueOf(tablero[e][i]).replace("9","B") + " | ";
            }
            //Log.i("Bombas",valor);

            valor += "\n";

            System.out.print(valor);
        }
    }

    private int CalculaMinasCercanas(int x, int y){

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

    public int getValorCelda(int pos){

        int x = pos % Largo;
        int y = pos / Largo;

        return tablero[x][y];
    }

    public int getNBombas() {
        return NBombas;
    }

    public void setNBombas(int NBombas) {
        this.NBombas = NBombas;
    }
}
