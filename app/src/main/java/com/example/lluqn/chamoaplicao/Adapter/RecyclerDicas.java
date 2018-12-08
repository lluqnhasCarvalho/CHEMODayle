package com.example.lluqn.chamoaplicao.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.lluqn.chamoaplicao.R;
import com.example.lluqn.chamoaplicao.modelo.Dica;

import java.util.List;

public class RecyclerDicas  extends RecyclerView.Adapter<RecyclerDicas.ViewHolder> {

    private List<Dica> lista;
    private Context context;
    private Dialog dialog;

    public RecyclerDicas(Context context, List<Dica> lista) {
        this.lista = lista;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_dicas, viewGroup, false);

        final ViewHolder viewHolder = new ViewHolder( view);

        dialog = new Dialog( context );
        dialog.setContentView( R.layout.item_dicas );

        viewHolder.cardView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView txt_msg = (TextView) dialog.findViewById( R.id.texto_dica );
                ImageView img_msg = (ImageView) dialog.findViewById(R.id.img_dicas);

                txt_msg.setText( lista.get( viewHolder.getAdapterPosition() ).getMenssagem() );
                img_msg.setVisibility(View.VISIBLE);
               // img_msg.setImageResource(R.drawable.support);
                dialog.show();

            }
        } );



        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.txtTexto.setText(lista.get(i).getTitulo());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtTexto;
        private ImageView imageView;
        private CardView cardView;

        public ViewHolder(View view) {
            super(view);

            cardView = (CardView) view.findViewById(R.id.card_dicas);
            txtTexto = (TextView) view.findViewById(R.id.texto_dica);
            imageView = (ImageView) view.findViewById(R.id.img_dicas);

        }
    }
}
