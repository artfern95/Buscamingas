package com.example.buscamingas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    //Lo cambiamos: private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private BuscaminasAdaptar buscaminasAdaptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Barra de accion
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        buscaminasAdaptar = new BuscaminasAdaptar(this);

        layoutManager = new GridLayoutManager(this,8);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(buscaminasAdaptar);

        //layoutManager=new LinearLayoutManager(this);
    }

    public void ReiniciarJuego(){

        buscaminasAdaptar = new BuscaminasAdaptar(this);
        recyclerView.setAdapter(buscaminasAdaptar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.instruc:

                AlertDialog.Builder alertdialogBuilder = new AlertDialog.Builder(this);

                alertdialogBuilder.setTitle("Instrucciones");

                alertdialogBuilder.setMessage("COPIAR INSTRUCCIONES");

                alertdialogBuilder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog alertDialog = alertdialogBuilder.create();
                alertDialog.show();
                return true;
            case R.id.startJuego:

                ReiniciarJuego();

                return true;
            case R.id.config:

            default:
                /*Devuelve falso para permitir el normal funcionamiento d la actividad
                  con los elementos originales del menu, con true el focus está en el
                  item que recibio el click*/
        }

        return super.onOptionsItemSelected(item);
    }
}
