package com.example.debarembar.view.ui.cadastro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.debarembar.R;

public class CadastroFragment extends Fragment {

    private CadastroViewModel cadastroViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        cadastroViewModel =
                ViewModelProviders.of(this).get(CadastroViewModel.class);
        View root = inflater.inflate(R.layout.fragment_cadastro, container, false);
        final TextView textView = root.findViewById(R.id.textTituloView);
        cadastroViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}