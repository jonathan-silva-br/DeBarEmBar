package com.example.debarembar.view.ui.cadastro;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
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
        final TextInputEditText nomeRua;
        nomeRua = root.findViewById(R.id.textNomeRua);

        //Text utilizado para obter o número do local cadastrado
        final TextInputEditText numero;
        numero = root.findViewById(R.id.textNumero);

        //Text utilizado para obter o bairro do local cadastrado
        final TextInputEditText bairro;
        bairro = root.findViewById(R.id.textBairro);

        //Text utilizado para obter o município do local cadastrado
        final TextInputEditText municipio;
        municipio = root.findViewById(R.id.textMunicipio);

        //Text utilizado para obter o estado do local cadastrado
        final TextInputEditText estado;
        estado = root.findViewById(R.id.textEstado);

        //RatingBar utilizado para obter a classificação do local cadastrado
        RatingBar classificacao;
        classificacao = root.findViewById(R.id.rb_classificacao);

        //CheckBox utilizado para setar a informação de existência da cerveja Stella Artois no local cadastrado
        CheckBox checkStella;
        checkStella = root.findViewById(R.id.checkBoxStellaArtois);

        //CheckBox utilizado para setar a informação de existência da cerveja Corona no local cadastrado
        CheckBox checkCorona;
        checkCorona = root.findViewById(R.id.checkBoxCorona);

        //CheckBox utilizado para setar a informação de existência da cerveja Budweiser no local cadastrado
        CheckBox checkBudweiser;
        checkBudweiser = root.findViewById(R.id.checkBoxBudweiser);

        //CheckBox utilizado para setar a informação de existência da cerveja Becks no local cadastrado
        CheckBox checkBecks;
        checkBecks = root.findViewById(R.id.checkBoxBecks);

        //EditText utilizado para obter o valorUnitario da cerveja Stella Artois
        EditText valorStella;
        valorStella = root.findViewById(R.id.editValorStellaArtois);

        //EditText utilizado para obter o valorUnitario da cerveja Corona
        EditText valorCorona;
        valorCorona = root.findViewById(R.id.editValorCorona);

        //EditText utilizado para obter o valorUnitario da cerveja Budweiser
        EditText valorBudweiser;
        valorBudweiser = root.findViewById(R.id.editValorBudweiser);

        //EditText utilizado para obter o valorUnitario da cerveja Becks
        EditText valorBecks;
        valorBecks = root.findViewById(R.id.editValorBecks);


        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repasseInfo(String.valueOf(nomeLocal.getText()), String.valueOf(nomeRua.getText()),
                        String.valueOf(numero.getText()), String.valueOf(bairro.getText()),
                        String.valueOf(municipio.getText()), String.valueOf(estado.getText()));
            }
        });

        return root;
    }

    /**
     * repasseInfo()
     *
     * Instancia o objeto CadastroPresenter, permitindo a interação entre model e view.
     * Utiliza como parâmetros String nomeLocal, nomeRua, numero, bairro, municipio e estado
     * que serão utilizados pelo construtor da classe Bar dentro da classe CadastroPresenter.
     *
     * @return retorna o objeto CadastroPresenter
     *
     * @author Jonathan Silva <silva_jonathan@outlook.com.br>
     */
    public CadastroPresenter repasseInfo(String nomeLocal, String nomeRua, String numero, String bairro, String municipio, String estado){
        CadastroPresenter cadastroPresenter = new CadastroPresenter(nomeLocal, nomeRua, numero, bairro, municipio, estado);
        return cadastroPresenter;
    }
}