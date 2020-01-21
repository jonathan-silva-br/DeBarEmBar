package com.example.debarembar.model;

import java.util.ArrayList;

/**
 *  Classe Bar - define o modelo Bar
 *
 *  Define o modelo Bar que tem como parâmetros o nome, image, classificação, localização e bebidaArrayList.
 *
 *  @since 1.0.0
 *  @author Jonathan Silva <silva_jonathan@outlook.com.br>
 */

public class Bar {

    private String nome;
    private int image;
    private double classificacao;
    private String localizacao;
    private ArrayList<Bebida> bebidaArrayList;

    /**
     * Construtor Bar
     *
     * @param nomeRepassado String Nome do Bar repassado pelo usuário
     * @param localizacaoRepassada String Localização do Bar repassado pelo usuário
     *
     * @author Jonathan Silva <silva_jonathan@outlook.com.br>
     */

    public Bar(String nomeRepassado, String localizacaoRepassada){
        this.setNome(nomeRepassado);
        this.setLocalizacao(localizacaoRepassada);
        bebidaArrayList = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public double getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(double classificacao) {
        this.classificacao = classificacao;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public ArrayList<Bebida> getBebidaArrayList() {
        return bebidaArrayList;
    }

    public void setBebidaArrayList(Bebida bebida) {
        bebidaArrayList.add(bebida);
    }

    public int getSize(){
        return bebidaArrayList.size();
    }




}
