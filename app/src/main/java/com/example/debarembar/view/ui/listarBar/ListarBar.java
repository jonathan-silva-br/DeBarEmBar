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
import com.example.debarembar.model.BarTeste;
import com.example.debarembar.presenter.CadastroPresenter;
import com.example.debarembar.presenter.ListarBaresAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;

public class ListarBar extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    private RecyclerView mRecycleViewListBar;
    private BarTeste mBar;
    private ListarBaresAdapter mListarBaresAdapter;
    private ArrayList<Bar> mBarList;
    CadastroPresenter cadastroPresenter;
    private EditText etSearch;
    private SharedPreferences preferences;

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

    public void start(Context context) {

        String jsonObj = preferences.getString("barSMS","");
        String listaBares = preferences.getString("listaBares","");

        selectBares(listaBares);

        if(!jsonObj.equals("")){

            Gson gson = new Gson();
            Bar bar = gson.fromJson(jsonObj,Bar.class);

            for(int i = 0; i < mBarList.size(); i++){
                if(!mBarList.contains(bar)){
                    mBarList.add(bar);
                }
            }
        }

        mListarBaresAdapter = new ListarBaresAdapter(context, mBarList,preferences);
        mRecycleViewListBar.setAdapter(mListarBaresAdapter);
        verifica(CadastroPresenter.listBar);
    }

    /**
     * Método feito para pegar os valores do sharedPreferences e colocar na lista
     *
     *
     *
     * @param listaBares
     */
    private void selectBares(String listaBares){

        if(!listaBares.equals("")){

            String[] bares = listaBares.split("====");

            for(int i = 0; i< bares.length;i++){


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

    //Verificar se tem ago no arrayList<Bar>
    public void verifica(ArrayList<Bar> listBar) {

        Log.e("VLR_array", String.valueOf(listBar.size()));
       if (listBar.size() != 0) {
           mListarBaresAdapter.addmListBar(listBar);
       }
    }

}