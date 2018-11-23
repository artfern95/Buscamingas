package com.example.buscamingas.Celdas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;

import com.example.buscamingas.R;

public class Casilla extends android.support.v7.widget.AppCompatButton {

    private int x, y ;
    private int valor;//Bombas alrededor
    private int posicion;

    private boolean bomba;
    private boolean click;
    private boolean onlongclick;
    private boolean visible;

    /*Estos dos construcotres son llamados en el momento que se crea la vista en el xml*/

    public Casilla(Context context) {
        super(context);
    }

    public Casilla(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //Xp para que no coga la X de la clase madre
    public int getXp(){

        return x;
    }

    public int getYp(){

        return y;
    }

    public void setPosicion(int posicion){

        this.posicion = posicion;

        //Dividir posicion entre largo del tablero para obtener
        //las coordenadas x e y

        x = posicion % 8;
        y = posicion / 8;
    }

    public void setPosicion(int x, int y){

        this.x = x;
        this.y = y;

        posicion = y * 8 + x;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {

        //Inicializamos las celdas por defecto

        bomba = false;
        visible = false;
        onlongclick = false;
        click = false;

        //si su valor es -1 quiere decir que es una bomba;

        if(valor == -1){

            bomba = true;
        }

        this.valor = valor;
    }

    public boolean isClick() {
        return click;
    }

    public void setClick() {
        click = true;
    }

    public void setClick(boolean b) {
        click = b;
    }

    public boolean isOnlongclick() {
        return onlongclick;
    }

    public void setOnlongclick() {
        onlongclick = true;
    }

    public void setOnlongclick(boolean b) {
        onlongclick = b;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible() {
        visible = true;
    }

    public boolean getBomba(){

        return bomba;
    }

    //Canvas = libreria android
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawCeldaLibre(canvas);
        Log.i("Celda","Dibujando");

        if(onlongclick == true && bomba == true){

            drawBomba(canvas);
        }

        //Muestra todas las bombas una vez terminada la partida
        else if(visible == true && bomba == true && click == false){

            drawBomba(canvas);
        }

        else{

            if(click == true){

                if(valor == -1){

                    drawBombaExplota(canvas);
                }

                else{

                    drawNumero(canvas);
                }
            }

            else{

                drawCeldaLibre(canvas);
            }
        }
    }

    private void drawBombaExplota(Canvas canvas ){
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.bomb_exploded);
        drawable.setBounds(0,0,getWidth(),getHeight());//Hace que sea escalable
        drawable.draw(canvas);
    }

    private void drawFlag( Canvas canvas ){
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.flag);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }

    private void drawCeldaLibre(Canvas canvas ){
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.button);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }

    private void drawBomba(Canvas canvas ){
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.bomb_normal);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }

    private void drawNumero( Canvas canvas ){
        Drawable drawable = null;

        switch (getValor() ){
            case 0:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_0);
                break;
            case 1:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_1);
                break;
            case 2:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_2);
                break;
            case 3:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_3);
                break;
            case 4:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_4);
                break;
            case 5:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_5);
                break;
            case 6:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_6);
                break;
            case 7:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_7);
                break;
            case 8:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_8);
                break;
        }

        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }
}
