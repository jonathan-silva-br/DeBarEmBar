package com.example.debarembar.presenter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.debarembar.R;
import com.example.debarembar.model.CEP;
import com.example.debarembar.model.HTTTPService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class CadastroBarAmbev extends AppCompatActivity {
    Button btCadastroBarPreencher;
    EditText etCadastroBarEstado, etCadastroBarCep, etCadastroBarCidade, etCadastroBarBairro, etCadastroBarLogradouro;
    TextView textView;
    private RequestQueue mRequestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_cadastro_product_ambev);
        etCadastroBarLogradouro = findViewById(R.id.etCadastroBarLogradouro);
        etCadastroBarBairro = findViewById(R.id.etCadastroBarBairro);
        etCadastroBarCidade = findViewById(R.id.etCadastroBarCidade);
        btCadastroBarPreencher = findViewById(R.id.btCadastroBarPreencher);
        etCadastroBarEstado = findViewById(R.id.etCadastroBarEstado);
        etCadastroBarCep = findViewById(R.id.etCadastroBarCEP);
        textView = findViewById(R.id.textView5);
        mRequestQueue = Volley.newRequestQueue(this);



        btCadastroBarPreencher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etCadastroBarCep.getText().toString().length() > 0 && !etCadastroBarCep.getText().toString().equals("") && etCadastroBarCep.getText().toString().length() == 8) {
                    HTTTPService service = new HTTTPService(etCadastroBarCep.getText().toString());
                    try {
                        com.example.debarembar.model.CEP retorno = service.execute().get(); //o get() deve retorna o objeto definido na classe CEP
                        etCadastroBarEstado.setText(retorno.getEstado());
                        etCadastroBarCidade.setText(retorno.getCidade());
                        etCadastroBarBairro.setText(retorno.getBairro());
                        etCadastroBarLogradouro.setText(retorno.getLogradouro());

                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(CadastroBarAmbev.this, "CEP incorreto!!!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
