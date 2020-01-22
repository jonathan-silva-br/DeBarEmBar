package com.example.debarembar.model;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.debarembar.R;

import java.util.ArrayList;

public class ListarBares extends AppCompatActivity {
    private RecyclerView mRecycleViewListBar;
    private BarTeste mBar;
    private ListarBaresAdapter mListarBaresAdapter;
    private ArrayList<BarTeste> mBarList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_listar_bares);
        mRecycleViewListBar = findViewById(R.id.recycleViewListBar);
        mRecycleViewListBar.setLayoutManager(new LinearLayoutManager(this));

        mBarList = new ArrayList<>();

        start();
    }
    public void start(){
        ArrayList<String> listProduct = new ArrayList<>();
        listProduct.add("Skol");
        listProduct.add("Original");

        mBarList.add(new BarTeste("Nome do bar aqui", 2 , listProduct));

        mListarBaresAdapter = new ListarBaresAdapter(ListarBares.this, mBarList);
        mRecycleViewListBar.setAdapter(mListarBaresAdapter);

        //mListarBaresAdapter.setOnItemClickListiner(ListarBares.this);
    }
}
