package com.example.debarembar.presenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.debarembar.R;
import com.example.debarembar.model.BarTeste;

import java.util.ArrayList;

public class ListarBares extends AppCompatActivity {
    private RecyclerView mRecycleViewListBar;
    private BarTeste mBar;
    private ListarBaresAdapter mListarBaresAdapter;
    private ArrayList<BarTeste> mBarList;
    private EditText etSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_listar_bares);
        mRecycleViewListBar = findViewById(R.id.recycleViewListBar);
        mRecycleViewListBar.setLayoutManager(new LinearLayoutManager(this));
        etSearch = findViewById(R.id.etBuscarBar);

        mBarList = new ArrayList<>();
        start();
        searchBar();
    }
    public void start(){
        ArrayList<String> listProduct = new ArrayList<>();
        listProduct.add("Skol");
        listProduct.add("Original");
        ArrayList<String> listProduct2 = new ArrayList<>();
        listProduct2.add("Budzin");
        listProduct2.add("Corona");
        listProduct2.add("LOKAL");
        listProduct2.add("KAISE QUENTE");

        mBarList.add(new BarTeste("Bar do gusto", 2 , listProduct, R.drawable.jontas));
        mBarList.add(new BarTeste("Moutilas", 5 , listProduct2, R.drawable.jontas));
        mBarList.add(new BarTeste("Manezinho", 1 , listProduct2, R.drawable.jontas));
        mBarList.add(new BarTeste("Marilha e Mendonça", 2 , listProduct, R.drawable.jontas));

        mListarBaresAdapter = new ListarBaresAdapter(ListarBares.this, mBarList);
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
        ArrayList<BarTeste> filter = new ArrayList();
        for(BarTeste bar: mBarList){
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
