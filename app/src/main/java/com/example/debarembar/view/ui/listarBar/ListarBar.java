package com.example.debarembar.view.ui.listarBar;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.example.debarembar.presenter.ListarBaresAdapter;

import java.util.ArrayList;

public class ListarBar extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    private RecyclerView mRecycleViewListBar;
    private BarTeste mBar;
    private ListarBaresAdapter mListarBaresAdapter;
    private ArrayList<Bar> mBarList;

    private EditText etSearch;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_listar_bares, container, false);

        mRecycleViewListBar = root.findViewById(R.id.recycleViewListBar);
        mRecycleViewListBar.setLayoutManager(new LinearLayoutManager(root.getContext()));
        etSearch = root.findViewById(R.id.etBuscarBar);
        mBarList = new ArrayList<>();
        start(root.getContext());
        searchBar();


        return root;
    }

    public void start(Context context){
        ArrayList<String> listProduct = new ArrayList<>();
        listProduct.add("Skol");
        listProduct.add("Original");
        ArrayList<String> listProduct2 = new ArrayList<>();
        listProduct2.add("Budzin");
        listProduct2.add("Corona");
        listProduct2.add("LOKAL");
        listProduct2.add("KAISE QUENTE");

        mBarList.add(new Bar("Bar do gusto", "Rua tamarindo", "22", "Centro", "Blumenau", "SC", 2));
        mBarList.add(new Bar("BMoutilas", "Rua outra", "222", "2 de setembro", "Gaspar", "PR", 4));

        mListarBaresAdapter = new ListarBaresAdapter(context, mBarList);
        mRecycleViewListBar.setAdapter(mListarBaresAdapter);

        //mListarBaresAdapter.setOnItemClickListiner(ListarBares.this);
    }

    public void searchBar(){
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

    void filter(String text){
        ArrayList<Bar> filter = new ArrayList();
        for(Bar bar: mBarList){
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if(bar.getNome().toLowerCase().contains(text)){
                filter.add(bar);
            }
        }
        //update recyclerview
        mListarBaresAdapter.updateList(filter);
    }

}