package com.example.debarembar.view;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.debarembar.R;
import com.example.debarembar.model.Bar;
import com.example.debarembar.model.Contatos;
import com.example.debarembar.presenter.AdapterContatos;
import com.example.debarembar.presenter.ListarBaresAdapter;

import java.util.ArrayList;


/**
 * Classe responsável pelas ações da tela Compartilhar
 *
 * Neste fragmento temos o recycler view, o adapter e uma lista com os contatos
 *
 * É configurado o recyclerView e adicionado o adapter ao mesmo. Depois é
 * chamado o método responsável por pegar a lista dos contatos e colocá-lo
 * no array do adapter
 *
 * @author Matheus Geiser <matheusgeiser@gmail.com>
 * @since 1.1.0
 *
 */
public class ContatosActivity extends AppCompatActivity {

    /**
     * Variável global que vai receber o recyclerView
     */
    private RecyclerView recyclerView;

    /**
     * Array global que irá receber todos os contatos do celular
     * para depois ser colocado no adapter, para assim aparecer
     * na tela
     */
    private ArrayList<Contatos> listaContatos;

    /**
     *Variável do adapter que irá ser utilizado
     */
    private AdapterContatos adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contatos);

        Bar bar = ListarBaresAdapter.bar;

        //Array
        listaContatos = new ArrayList<>();

        //Chamando o adapter
        adapter = new AdapterContatos(listaContatos, this, bar);

        //Configurando RecyclerView
        recyclerView = findViewById(R.id.rvContatos);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapter);

        getContactList(this);

    }
    /**
     *
     * <p>Método que pega os contatos do celular e coloca no array do RecyclerView</p>
     *
     * Através de um cursor que vai realizar a pesquisa no banco de dados do
     * celular para ter acesso às informações dos contatos do celular, para
     * que a cada volta do laço while é armazenado nas variáveis string as
     * informações do nome do contato e do seu respectivo número. Logo após
     * é criado um objeto com o nome e o telefone, sendo adicionado ao array
     * do adapter e logo depois notificando o adapter para atualizar as
     * informações.
     *
     * @author Matheus Geiser <matheusgeiser@gmail.com>
     * @since 1.0.0
     *
     * @param context - Precisamos do contexto da aplicação para realizar a
     *                pesquisa dos contatos
     */
    private void getContactList(Context context){

        Cursor phones = context.getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,null,null,null);

        while(phones.moveToNext()){
            String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phone = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            Contatos contatos = new Contatos(phone,name);

            listaContatos.add(contatos);

        }


        adapter.notifyDataSetChanged();

    }
}
