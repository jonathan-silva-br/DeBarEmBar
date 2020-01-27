package com.example.debarembar.presenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.debarembar.R;
import com.example.debarembar.model.Bar;
import com.example.debarembar.model.Contatos;
import com.google.gson.Gson;

import java.util.ArrayList;

public class AdapterContatos extends RecyclerView.Adapter<AdapterContatos.MyViewHolder>  {

    private ArrayList<Contatos> listaContatos;
    private Context mContext;
    private Bar bar;

    public AdapterContatos(ArrayList<Contatos> listaContatos, Context context, Bar bar){
        this.listaContatos = listaContatos;
        this.mContext = context;
        this.bar = bar;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contatos,parent,false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Contatos contato = listaContatos.get(position);
        holder.lblNomeContato.setText(contato.getNomeContato());
    }

    @Override
    public int getItemCount() {
        return listaContatos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView lblNomeContato;

        /**
         * Construtor da classe MyViewHolder
         *
         * @author Matheus Geiser <matheusgeiser@gmail.com>
         * @since 1.0.0
         *
         * @param itemView que é um item, utilizado para conseguir fazer
         *                 interações com esta view
         */
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            lblNomeContato = itemView.findViewById(R.id.lblNomeContato);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {

            FuncoesSms sms = new FuncoesSms();
            Contatos contato = listaContatos.get(getAdapterPosition());

            Gson gson = new Gson();
            String json = gson.toJson(bar);

            String mensagem = "app@barembar===="+json;
            sms.SendSms(mContext,contato.getNumeroContato(),mensagem);

        }
    }

}
