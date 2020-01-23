package com.example.debarembar.presenter;

import android.util.Log;

import com.example.debarembar.model.Bar;
import com.example.debarembar.model.Bebida;

/**
 *  Classe CadastroPresenter - coordena a interação entre model e view
 *
 *  Classe responsável por coordenar a interação entre as classes Bar e Bebida do pacote model
 *  com o layout fragment_cadastro e a classe CadastroFragment do pacote view.
 *
 *  @since 2.0.0
 *  @author Jonathan Silva <silva_jonathan@outlook.com.br>
 */

public class CadastroPresenter {

    /**
     * Construtor CadastroPresenter
     *
     * Utiliza o método setBar().
     *
     * @param nomeLocal String Nome do local cadastrado
     *
     * @author Jonathan Silva <silva_jonathan@outlook.com.br>
     */

    public CadastroPresenter(String nomeLocal, String nomeRua, String numero, String bairro, String municipio, String estado){
        setBar(nomeLocal, nomeRua, numero, bairro, municipio, estado);
    }

    /**
     * setBar
     *
     * Método que instancia o objeto Bar, repassando como parâmetro uma String nomeLocal,
     * nomeRua, numero, bairro, municipio e estado para o construtor da classe Bar e instancia
     * as bebidas de maneira manual e fixa através do método setBebida().
     *
     * @param nomeLocal String Nome do local cadastrado
     * @param nomeRua String Nome da rua do endereço do local
     * @param numero String Número do endereço do local
     * @param bairro String Nome do bairro do endereço do local
     * @param municipio String Nome do município do endereço do local
     * @param estado String Nome do estado (UF) do endereço do local
     *
     * @return retorna o objeto bar
     *
     * @author Jonathan Silva <silva_jonathan@outlook.com.br>
     */

    public Bar setBar(String nomeLocal, String nomeRua, String numero, String bairro, String municipio, String estado){
        Bar bar = new Bar(nomeLocal, nomeRua, numero, bairro, municipio, estado);
        bar.setBebidaArrayList(setBebida("Stella Artois 550 mL"));
        bar.setBebidaArrayList(setBebida("Corona 355 mL"));
        bar.setBebidaArrayList(setBebida("Budweiser 343 mL"));
        bar.setBebidaArrayList(setBebida("Becks 330 mL"));

        //Log.e verifica no terminal se a informação está sendo repassada.
        Log.e("conferindo", bar.getNome());

        return bar;
    }

    /**
     * setBebida
     *
     * Método que instancia o objeto Bebida, repassando como parâmetro uma String nomeBebida para o
     * construtor da classe Bebida.
     *
     * @param nomeBebida String Nome da bebida
     * @return retorna o objeto bebida
     *
     * @author Jonathan Silva <silva_jonathan@outlook.com.br>
     */

    public Bebida setBebida(String nomeBebida){
        Bebida bebida = new Bebida(nomeBebida);
        return bebida;
    }



}
