package com.example.debarembar.presenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.debarembar.R;
import com.example.debarembar.model.Bar;
import com.example.debarembar.model.BarTeste;

import java.util.ArrayList;

public class ListarBaresAdapter extends RecyclerView.Adapter<ListarBaresAdapter.ListarBaresAdapterViewHolder> {
    private Context mContext;
    private ArrayList<Bar> mListBar;

    //Construtor
    public  ListarBaresAdapter(Context context, ArrayList<Bar> listBar){
        mListBar = listBar;
        mContext = context;
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

        holder.txtListBarName.setText(bar.getNome());
        //holder.txtListBarAvaliacao.setText("Nota: "+bar.getAvaliacao());
        //holder.txtListProduct.setText(bar.getProduct().toString());
        //holder.imgListBar.setImageResource(bar.getImagemCerta());
    }

    @Override
    public int getItemCount() {
        return mListBar.size();
    }

    public class ListarBaresAdapterViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgListBar;
        private TextView txtListBarName;
        private TextView txtListBarAvaliacao;
        private TextView txtListProduct;

        public ListarBaresAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            imgListBar = itemView.findViewById(R.id.imgListBar);
            txtListBarName = itemView.findViewById(R.id.txtListNameBar);
            txtListBarAvaliacao = itemView.findViewById(R.id.txtListNota);
            txtListProduct = itemView.findViewById(R.id.txtListProduct);
        }
    }
    public void updateList(ArrayList<Bar> list){
        mListBar = list;
        notifyDataSetChanged();
    }
}
