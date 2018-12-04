package com.example.buscamingas;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    //Lo cambiamos: private RecyclerView.Adapter adapter;
    private BuscaminasAdaptar adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Barra de accion
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Boton para reiniciar el huevo
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        Logica.getInstance().IniciarJuego(this,recyclerView);

        //layoutManager=new LinearLayoutManager(this);


        /*Paul es un follador nato
        * Listener para ocultar el boton de juego nuevo
        * */
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            //set
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                //si es hacia arriba lo muestra
                if (dy < 0) {

                    fab.show();
                }

                //Si el desplazamiento es hacia abajo lo oculta | > 0
                else if (dy > 0) {

                    fab.hide();
                }
            }
        });
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
        TextView t = (TextView) findViewById(R.id.textView);

        switch (id) {
            case R.id.instruc:
                t.setText("Opcion 1");
                return true;
            case R.id.startJuego:
                t.setText("Opcion 2");
                return true;
            case R.id.config:
                t.setText("Opcion 3");
            default:
                /*Devuelve falso para permitir el normal funcionamiento d la actividad
                  con los elementos originales del menu, con true el focus está en el
                  item que recibio el click*/
        }

        return super.onOptionsItemSelected(item);
    }
}
