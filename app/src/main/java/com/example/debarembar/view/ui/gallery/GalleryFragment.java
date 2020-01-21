package com.example.debarembar.view.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.debarembar.R;
import com.example.debarembar.model.Contatos;
import com.example.debarembar.presenter.AdapterContatos;

import java.util.ArrayList;

/**
 *
 *
 */
public class GalleryFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Contatos> listaContatos;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        //Chamando o adapter
        AdapterContatos adapter = new AdapterContatos(listaContatos, root.getContext());

        //Configurando RecyclerView
        recyclerView = root.findViewById(R.id.rvContatos);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(root.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(root.getContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapter);

        return root;
    }
}