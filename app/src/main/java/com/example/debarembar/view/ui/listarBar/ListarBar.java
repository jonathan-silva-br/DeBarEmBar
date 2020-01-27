package com.example.debarembar.view.ui.listarBar;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.debarembar.R;
import com.example.debarembar.model.Bar;
import com.example.debarembar.presenter.CadastroPresenter;
import com.example.debarembar.presenter.ListarBaresAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;

public class ListarBar extends Fragment {

    private RecyclerView mRecycleViewListBar;
    private ListarBaresAdapter mListarBaresAdapter;
    private ArrayList<Bar> mBarList;
    CadastroPresenter cadastroPresenter;
    private EditText etSearch;
    public SharedPreferences preferences;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_listar_bares, container, false);

        preferences = root.getContext().getSharedPreferences("json_bank",Context.MODE_PRIVATE);
        mRecycleViewListBar = root.findViewById(R.id.recycleViewListBar);
        mRecycleViewListBar.setLayoutManager(new LinearLayoutManager(root.getContext()));
        etSearch = root.findViewById(R.id.etBuscarBar);
        mBarList = new ArrayList<>();
        start(root.getContext());
        searchBar();

        return root;
    }

    /**
     *
     * Método que seta o adapter do Recycler e pega os dados salvos
     *
     *
     * Pega o valor do sharedPreference que contém o ultimo bar
     * recebido por SMS para depois adicioná-lo na lista.
     * Pega o valor do sharedPreferences que contém todos os bares
     * que foram registrados no app, para exibí-los na lista.
     * Seta as informações do adapter e logo em seguida adiciona-o
     * no recyclerView.
     *
     * @author Matheus Geiser <matheusgeiser@gmail.com>
     * @author Guilherme Lamim <guilherme.lamim96@gmail.com.br>
     * @param context
     */
    public void start(Context context) {

        String jsonObj = preferences.getString("barSMS","");
        String listaBares = preferences.getString("listaBares","");

        selectBares(listaBares);

        mListarBaresAdapter = new ListarBaresAdapter(context, mBarList,preferences);
        mRecycleViewListBar.setAdapter(mListarBaresAdapter);

        verifica(CadastroPresenter.listBar);

        if(!jsonObj.equals("")){
            Log.e("sms",jsonObj);
            addSMSObject(jsonObj);
        }

        verifica(mBarList);
    }

    /**
     * Método que transforma o json em Bar object e adiciona no recycler
     *
     * Através da biblioteca GSON, transformamos o json em formato de String para
     * um objeto do tipo Bar.
     * Logo depois fazemos uma verificação para saber se esse objeto já está no
     * array que vai pro RecyclerView. Se ele não estiver, é adicionado.
     *
     * @author Matheus Geiser <matheusgeiser@gmail.com>
     * @param jsonObj
     */
    public void addSMSObject(String jsonObj){
        Gson gson = new Gson();
        Bar bar = gson.fromJson(jsonObj,Bar.class);
        boolean verif = false;

        for(int i = 0; i < mBarList.size(); i++){

            Bar barArray = mBarList.get(i);
            if(bar.getNome().equals(barArray.getNome())){
               verif = true;
            }
        }

        if(!verif){
            mListarBaresAdapter.addmListBar(bar);
        }
    }

    /**
     * Método feito para pegar os valores do sharedPreferences e colocar na lista
     *
     *Método que pega a string que contém as informações do sharedPreference,
     * quebra essa string que contém os objetos JSON de cada bar e transforma
     * em um BAR OBJECT para adicionar no Array do RecyclerView
     *
     * @author Matheus Geiser <matheusgeiser@gmail.com>
     * @param listaBares
     */
    private void selectBares(String listaBares){

        Log.e("ListaBares",listaBares);
        if(!listaBares.equals("")){
            String[] bares = listaBares.split("====");
            for(int i = 0; i< bares.length;i++){
                Log.e("Bares Salvos",bares[i]);
                Gson gson = new Gson();
                Bar bar = gson.fromJson(bares[i],Bar.class);
                mBarList.add(bar);
            }
        }
    }

    /**
     * searchBar()
     *
     * @filter - perccorre um arrayList<Bar> e faz conparacao. Se sequencia de caracteres contem no ArrayList<Bar>.
     * depois de percorrer o ArrayList<Bar>, o adapter é atualizado com o notifyDataSetChanged contido no metodo @updateList
     *
     * @author Guilherme Lamim <guilherme.lamim96@gmail.com.br>
     */
    public void searchBar() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }

    void filter(String text) {
        ArrayList<Bar> filter = new ArrayList();
        for (Bar bar : mBarList) {
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if (bar.getNome().toLowerCase().contains(text)) {
                filter.add(bar);
            }
        }
        //update recyclerview
        mListarBaresAdapter.updateList(filter);
    }

    /**
     *
     * Método que verifica se os bares do Array já estão no RecyclerView
     *
     * é feita uma verificação comparando o nome dos bares, pois não
     * se pode ter dois iguais.
     *
     *
     * @author Matheus Geiser <matheusgeiser@gmail.com>
     * @author Guilherme Lamim <guilherme.lamim96@gmail.com.br>
     * @param listBar
     */
    public void verifica(ArrayList<Bar> listBar) {

        Log.e("VLR_array", String.valueOf(listBar.size()));
       if (listBar.size() != 0) {

           for(int i = 0; i<listBar.size(); i++){
               Bar barArray = listBar.get(i);
               boolean verif = false;

               for(int j = 0; j < mBarList.size(); j++){

                   Bar barAdapter = mBarList.get(j);
                   if(barArray.getNome().equals(barAdapter.getNome())){
                       verif = true;
                       break;
                   }
               }

               if(!verif){
                   mListarBaresAdapter.addmListBar(barArray);
               }

           }


       }
    }

}