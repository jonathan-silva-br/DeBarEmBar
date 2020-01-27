package com.example.debarembar.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.debarembar.R;
import com.example.debarembar.model.Bar;
import com.example.debarembar.view.ContatosActivity;
import com.google.gson.Gson;

import java.util.ArrayList;

public class ListarBaresAdapter extends RecyclerView.Adapter<ListarBaresAdapter.ListarBaresAdapterViewHolder> {
    private Context mContext;
    private ArrayList<Bar> mListBar = new ArrayList<>();
    public static Bar bar;
    private SharedPreferences preferences;

    //Construtor
    public  ListarBaresAdapter(Context context, ArrayList<Bar> listBar, SharedPreferences preferences){
        this.mListBar = listBar;
        mContext = context;
        this.preferences = preferences;
    }


    /**
     * Método que adiciona um bar por vez no array do adapter
     *
     * Este método adiciona o bar vindo do parâmetro e chama o método
     * que salva os dados. Logo depois ele atualiza os dados da tela.
     *
     * @author Matheus Geiser <matheusgeiser@gmail.com>
     * @param barzika
     */
    public void addmListBar(Bar barzika){
            mListBar.add(barzika);
            salvarDados(preferences);
            notifyDataSetChanged();

    }

    /**
     * Método que salva os bares no sharedPreference em formato JSON
     *
     * verifica se a preferencia é nula, se não for ele percorre
     * o array do adapter e pega cada obj e transforma em uma string
     * JSON, para depois adicionar um separador que é o "====" e
     * depois do looping ele adiciona no SharedPreferences.
     *
     * @author Matheus Geiser <matheusgeiser@gmail.com>
     * @since 1.1.1
     *
     * @param preferencias
     */
    private void salvarDados(SharedPreferences preferencias){

        if(preferencias != null){

            Gson gson = new Gson();
            String arquivo = "";
            for(int i = 0; i < mListBar.size(); i++){

                String json = gson.toJson(mListBar.get(i));

                Log.e("JSON SALVADO",json);

                if(i == 0){
                    arquivo += json;
                }
                else{
                    arquivo += "===="+json;
                }

            }
            SharedPreferences.Editor editor = preferencias.edit();
            editor.putString("listaBares",arquivo);
            editor.apply();
        }

    }

    @NonNull
    @Override
    //Definindo onde vai ser inflado a lista com cardView
    public ListarBaresAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.card_view_bar_model, parent, false);
        return new ListarBaresAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListarBaresAdapterViewHolder holder, int position) {
        Bar bar = mListBar.get(position);


        holder.txtNomeBar.setText(bar.getNome());
        holder.txtCidadeEstado.setText("Cidade: " + bar.getMunicipio() + " / " + bar.getEstado());
        holder.txtBairo.setText("Bairro: " + bar.getBairro());
        holder.txtRuaNumero.setText("Rua: " + bar.getNomeRua() + "  n°:" + bar.getNumeroEndereco());
        holder.bebidaEPreco.setText(bar.getBebidaArrayList().toString());
        holder.avaliacao.setRating((float)bar.getClassificacao());
    }

    @Override
    public int getItemCount() {
        return mListBar.size();
    }

    public class ListarBaresAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView imgListBar;
        private TextView txtNomeBar;
        private TextView txtCidadeEstado;
        private TextView txtBairo;
        private TextView txtRuaNumero;
        private RatingBar avaliacao;
        private TextView bebidaEPreco;
        private ImageButton imageButton;

        public ListarBaresAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            //imgListBar = itemView.findViewById(R.id.imgListBar);
            txtNomeBar = itemView.findViewById(R.id.tvNomeBar);
            txtCidadeEstado = itemView.findViewById(R.id.tvCidadeEstado);
            txtBairo = itemView.findViewById(R.id.tvBairro);
            txtRuaNumero = itemView.findViewById(R.id.tvRuaNumero);
            avaliacao = itemView.findViewById(R.id.rbAvaliacao);
            bebidaEPreco = itemView.findViewById(R.id.tvBebidaPreco);
            imageButton = itemView.findViewById(R.id.imageButton);
            imageButton.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            bar = mListBar.get(getAdapterPosition());

            //Transformando o array em um vetor
            Intent intent = new Intent(mContext, ContatosActivity.class);
            mContext.startActivity(intent);

        }
    }
    public void updateList(ArrayList<Bar> list){
        mListBar = list;
        notifyDataSetChanged();
    }


}
