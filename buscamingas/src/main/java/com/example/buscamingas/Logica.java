package com.example.buscamingas;

import com.example.buscamingas.Celdas.Casilla;

import java.util.ArrayList;

public class Logica {

    private static final Logica ourInstance = new Logica();

    private int Largo;
    private int Ancho;
    private int NBombas;

    private ArrayList<Casilla> mapBoom;

    static Logica getInstance() {
        return ourInstance;
    }

    private Logica() {

    }

    public void IniciarJuego(){

        Largo = 8;
        Ancho = 8;
        NBombas = 12;

        mapBoom = new ArrayList<>();
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

    public int getNBombas() {
        return NBombas;
    }

    public void setNBombas(int NBombas) {
        this.NBombas = NBombas;
    }
}
