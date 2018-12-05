package com.example.buscamingas;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.buscamingas.Celdas.Casilla;

import java.util.ArrayList;
import java.util.Random;

public class Logica {

    private static final Logica ourInstance = new Logica();

    private int largo;
    private int ancho;
    private int nBombas;
    private int contBombas;

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

        largo = 8;
        ancho = 8;
        nBombas = 12;
        contBombas = nBombas;

        mapBoom = new ArrayList<>();

        CrearTablero();

        layoutManager = new GridLayoutManager(context, largo);
        buscaminasAdaptar = new BuscaminasAdaptar(context);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(buscaminasAdaptar);

    }

    public void CrearTablero() {

        tablero = new int[largo][ancho];

        Random num = new Random();

        int nbombas = nBombas;

        while(nbombas > 0){

            //Posiciones x,y para colocar la bomba de forma aleatoria
            int x = num.nextInt(largo);
            int y = num.nextInt(ancho);

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
        for (int i = 0; i < largo; i++) {

            for (int e = 0; e < ancho; e++) {

                tablero[i][e] = CalculaMinasCercanas(i,e);
            }
        }


        String valor = "";

        for(int i = 0; i < ancho; i ++){

            valor = " | ";

            for(int e = 0; e < largo; e ++){

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

                    if(i < largo && i >= 0 && e < ancho && e >= 0){

                        if(tablero[i][e] == -1){

                            Nbombascercanas++;
                        }
                    }
                }
            }

            return Nbombascercanas;
        }
    }

    public int getContBombas() {
        return contBombas;
    }

    public void restarBombas() {
        contBombas--;
    }

    public int getLargo() {
        return largo;
    }

    public void setLargo(int largo) {
        this.largo = largo;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getValorCelda(int pos){

        int x = pos % largo;
        int y = pos / largo;

        return tablero[x][y];
    }

    public int getnBombas() {
        return nBombas;
    }

    public void setnBombas(int nBombas) {
        this.nBombas = nBombas;
    }
}
