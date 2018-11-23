package com.example.buscamingas;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class BuscaminasAdaptar extends RecyclerView.Adapter<BuscaminasAdaptar.ExampleViewHolder> {

    private ArrayList<BuscaminasItem> mExampleList;
    //(2) Se define un atributo que es el listener
    private OnItemClickListener mListener;

    //(1) Se crea una interfaz dentro de la clase Adapter
    public interface OnItemClickListener{
        //Se define un método al que se le pasará la posición con el item (fila) que ha sido dada click
        void OnItemClick(int position);
    }

    //(3) Método set para fijar el listener asociado a la clase Adapter
    public void setOnClickListener(OnItemClickListener listener){
        mListener=listener;
    }

    public BuscaminasAdaptar(ArrayList<BuscaminasItem> exampleList) {
        mExampleList=exampleList;
    }

    //Crea vista
    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem,parent,false);
        ExampleViewHolder exampleViewHolder=new ExampleViewHolder(v,mListener);

        return exampleViewHolder;
    }

    //Rellena vista
    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        BuscaminasItem currentItem=mExampleList.get(position);

        holder.mImageView.setImageResource(currentItem.getImageResource());
        holder.mTextView1.setText(currentItem.getText1());


    }

    @Override
    public int getItemCount() {

        return mExampleList.size();
    }


    //Class estática por recomendación del API
    public static class ExampleViewHolder extends RecyclerView.ViewHolder{

        ImageView mImageView;
        TextView mTextView1;

        //(4) se le pasa como parámetro OnItemClickListener ya que la clase es estática
        //sino no tendría visibilidad del listener
        public ExampleViewHolder(View itemView, final OnItemClickListener listener){
            super(itemView);
            mImageView=itemView.findViewById(R.id.imageView);
            mTextView1=itemView.findViewById(R.id.textView);
            /*(4)Este es el método en el que se apoya todo (el onClick), tenemos un listener para cada
            ViewHolder, es decir para cada elemento de la lista asociado a una posición. Está
            dentro del contructor, de forma que al contruir un ViewHolder se le asocia un listener
            que es identificado por una posición (DE LOS ELEMENTOS VISIBLES)
             */
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (listener!=null){
                        //El adapter es el que sabe la posición absoluta dentro de la vista,
                        int position=getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            //Fijamos un listener para cada elemento de la lista
                            //Vamos al MainActivity...
                            listener.OnItemClick(position);
                        }
                    }
                }
            });
        }

    }
}
