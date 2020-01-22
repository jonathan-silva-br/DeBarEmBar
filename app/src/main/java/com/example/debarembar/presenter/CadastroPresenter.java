package com.example.debarembar.presenter;

import android.util.Log;
import android.view.View;

import com.example.debarembar.model.Bar;
import com.example.debarembar.model.Bebida;

/**
 *  Classe CadastroPresenter - coordena a interação entre model e view
 *
 *  Classe responsável por coordenar a interação entre as classes Bar e Bebida do pacote model
 *  com o layout fragment_cadastro e a classe CadastroFragment do pacote view.
 *  Possui como parâmetros nomeLocal, classificacaoLocal, imagemLocal e localizacaoLocal.
 *
 *  @since 1.0.0
 *  @author Jonathan Silva <silva_jonathan@outlook.com.br>
 */

public class CadastroPresenter {

    private String nomeLocal;
    private double classificacaoLocal;
    private int imagemLocal;
    private String localizacaoLocal;

    /**
     * Construtor CadastroPresenter
     *
     * Utiliza o método setBar().
     *
     * @param nomeLocal String Nome do local cadastrado
     *
     * @author Jonathan Silva <silva_jonathan@outlook.com.br>
     */

    public CadastroPresenter(String nomeLocal){
        setBar(nomeLocal);
    }

    /**
     * setBar
     *
     * Método que instancia o objeto Bar, repassando como parâmetro uma String nomeLocal para o
     * construtor da classe Bar e instancia as bebidas de maneira manual e fixa através do método
     * setBebida().
     *
     * @param nomeLocal String Nome do local cadastrado
     * @return retorna o objeto bar
     *
     * @author Jonathan Silva <silva_jonathan@outlook.com.br>
     */

    public Bar setBar(String nomeLocal){
        Bar bar = new Bar(nomeLocal);
        bar.setBebidaArrayList(setBebida("Stella Artois 550 mL"));
        bar.setBebidaArrayList(setBebida("Corona 355 mL"));
        bar.setBebidaArrayList(setBebida("Budweiser 343 mL"));
        bar.setBebidaArrayList(setBebida("Becks 330 mL"));

        Log.d("conferindo", bar.getNome());

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
