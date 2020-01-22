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

    public Bar(String nomeRepassado){
        this.setNome(nomeRepassado);
        bebidaArrayList = new ArrayList<>();
    }

    /**
     * getNome
     * @return retorna a String nome da classe Bar
     */

    public String getNome() {
        return nome;
    }

    /**
     * setNome
     * @param nome define a String nome da classe Bar
     */

    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * getImage
     * @return retorna o int image da classe Bar
     */

    public int getImage() {
        return image;
    }

    /**
     * setImage
     * @param image define o int image da classe Bar
     */

    public void setImage(int image) {
        this.image = image;
    }

    /**
     * getClassificacao
     * @return retorna o double classificacao da classe Bar
     */

    public double getClassificacao() {
        return classificacao;
    }

    /**
     * setImage
     * @param classificacao define o double classificacao da classe Bar
     */

    public void setClassificacao(double classificacao) {
        this.classificacao = classificacao;
    }

    /**
     * getLocalizacao
     * @return retorna a String localizacao da classe Bar
     */

    public String getLocalizacao() {
        return localizacao;
    }

    /**
     * setImage
     * @param localizacao define a String localizacao da classe Bar
     */

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    /**
     * getBebidaArrayList
     * @return retorna o ArrayList do tipo Bebida da classe Bar
     */

    public ArrayList<Bebida> getBebidaArrayList() {
        return bebidaArrayList;
    }

    /**
     * setImage
     * @param bebida define a Bebida para o bebidaArrayList da classe Bar
     */

    public void setBebidaArrayList(Bebida bebida) {
        bebidaArrayList.add(bebida);
    }

    /**
     * getSize
     * @return retorna o size da bebidaArrayList para validação de teste
     */

    public int getSize(){
        return bebidaArrayList.size();
    }




}
