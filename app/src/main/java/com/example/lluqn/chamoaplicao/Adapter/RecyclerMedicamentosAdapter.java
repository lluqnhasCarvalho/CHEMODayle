package com.example.lluqn.chamoaplicao.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.lluqn.chamoaplicao.R;
import com.example.lluqn.chamoaplicao.modelo.Medicamento;

import java.util.List;

public class RecyclerMedicamentosAdapter extends RecyclerView.Adapter<RecyclerMedicamentosAdapter.ViewHolder> {

    Context context;
    List<Medicamento> list;

    public RecyclerMedicamentosAdapter(Context context, List<Medicamento> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from( context ).inflate( R.layout.item_medicamento, parent, false );
        final ViewHolder viewHolder = new ViewHolder( v );
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_nome.setText( list.get( position ).getNome() );
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_nome;

        public ViewHolder(View itemView) {
            super( itemView );

            tv_nome = itemView.findViewById( R.id.nome_item );

        }
    }
}