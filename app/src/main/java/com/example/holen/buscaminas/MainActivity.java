package com.example.holen.buscaminas;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.holen.buscaminas.BuscaminasItem;
import com.example.holen.buscaminas.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    //Lo cambiamos: private RecyclerView.Adapter adapter;
    private BuscaminasAdaptar adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<BuscaminasItem> casillaList=new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Añade ContentView a nuestra actividad
        setContentView(R.layout.activity_main);
        //Añade ActionBar a nuestra actividad
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
        * for(int j=0;j<=143;j++){//30 bombas
        *      casillaList.addd(new BuscaminasItem(R.drawable:ic_audio,""+j));
        * }
        *   layoutManager=new GridLayoutManager(this,12);
        *
        * for(int z=0;z<=255;z++){//60 bombas
        *      casillaList.addd(new BuscaminasItem(R.drawable:ic_audio,""+j));
        * }
        * layoutManager=new GridLayoutManager(this,16);
        *
        * */
        for(int i=0;i<=63;i++){//10 bombas
            casillaList.add(new BuscaminasItem(R.drawable.ic_audio,""+i));
        }

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        //layoutManager=new LinearLayoutManager(this);

        layoutManager=new GridLayoutManager(this,8);
        adapter=new BuscaminasAdaptar(casillaList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnClickListener(new BuscaminasAdaptar.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                //(5) Aqui metemos la logica de respuesta
                casillaList.get(position).setmText1("Clicked");
                casillaList.get(position).setmImageResource(R.drawable.ic_sun);
                //hay que indicar al adapter que un elemento de la lista ha cambiado
                // para que lo gestione.
                adapter.notifyItemChanged(position);
                Toast.makeText(MainActivity.this, "Elemento pulsado:"+position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        TextView t = (TextView) findViewById(R.id.textview);

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
