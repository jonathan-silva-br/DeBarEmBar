package com.example.debarembar.view.ui.cadastro;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.debarembar.R;
import com.example.debarembar.presenter.CadastroPresenter;
import com.google.android.material.textfield.TextInputEditText;

public class CadastroFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cadastro, container, false);

        //Button utilizado para salvar as informações do cadastro de local.
        Button btnCadastrar;
        btnCadastrar = root.findViewById(R.id.btn_cadastrar);

        //Text utilizado para obter o nome do local cadastrado
        final TextInputEditText nomeLocal;
        nomeLocal = root.findViewById(R.id.textNomeLocal);

        //Text utilizado para obter a rua do local cadastrado
        TextInputEditText nomeRua;
        nomeRua = root.findViewById(R.id.textNomeRua);

        //Text utilizado para obter o número do local cadastrado
        TextInputEditText numero;
        numero = root.findViewById(R.id.textNumero);

        //Text utilizado para obter o bairro do local cadastrado
        TextInputEditText bairro;
        bairro = root.findViewById(R.id.textBairro);

        //Text utilizado para obter o município do local cadastrado
        TextInputEditText municipio;
        municipio = root.findViewById(R.id.textMunicipio);

        //Text utilizado para obter o estado do local cadastrado
        TextInputEditText estado;
        estado = root.findViewById(R.id.textEstado);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repasseInfo(String.valueOf(nomeLocal.getText()));
                Log.e("message", String.valueOf(nomeLocal.getText()));

            }
        });

        return root;
    }

    /**
     * repasseInfo()
     *
     * Instancia o objeto CadastroPresenter, permitindo a interação entre model e view.
     * Utiliza como parâmetro uma String nomeLocal que será utilizada pelo construtor da classe Bar
     * dentro da classe CadastroPresenter.
     *
     * @return retorna o objeto CadastroPresenter
     *
     * @author Jonathan Silva <silva_jonathan@outlook.com.br>
     */
    public CadastroPresenter repasseInfo(String nomeLocal){
        CadastroPresenter cadastroPresenter = new CadastroPresenter(nomeLocal);
        return cadastroPresenter;
    }
}