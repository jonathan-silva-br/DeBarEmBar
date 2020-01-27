package com.example.debarembar.model;

import java.util.ArrayList;

/**
 *  Classe Bar - define o modelo Bar
 *
 *  Define o modelo Bar que tem como parâmetros o nome, image, classificação, localização e bebidaArrayList.
 *
 *  @since 2.0.0
 *  @author Jonathan Silva <silva_jonathan@outlook.com.br>
 */

public class Bar {
    private String nome;
    private int image;
    private double classificacao;
    private String nomeRua;
    private String numeroEndereco;
    private String bairro;
    private String municipio;
    private String estado;
    private ArrayList<Bebida> bebidaArrayList;

    /**
     * Construtor Bar
     *
     * @param nomeRepassado String Nome do Bar repassado pelo usuário
     * @param nomeRua String Nome da rua do endereço do local
     * @param numeroEndereco String Número do endereço do local
     * @param bairro String Nome do bairro do endereço do local
     * @param municipio String Nome do município do endereço do local
     * @param estado String Nome do estado (UF) do endereço do local
     * @param classificacao flaot Classificação ou avaliação do local
     *
     * @author Jonathan Silva <silva_jonathan@outlook.com.br>
     */

    public Bar(String nomeRepassado, String nomeRua, String numeroEndereco, String bairro,
               String municipio, String estado, float classificacao){

        this.setNome(nomeRepassado);
        this.setNomeRua(nomeRua);
        this.setNumeroEndereco(numeroEndereco);
        this.setBairro(bairro);
        this.setMunicipio(municipio);
        this.setEstado(estado);
        this.setClassificacao(classificacao);
        bebidaArrayList = new ArrayList<>();
    }

    public Bar(String nomeRepassado, String nomeRua, String numeroEndereco, String bairro,
               String municipio, String estado, float classificacao, int image){

        this.setNome(nomeRepassado);
        this.setNomeRua(nomeRua);
        this.setNumeroEndereco(numeroEndereco);
        this.setBairro(bairro);
        this.setMunicipio(municipio);
        this.setEstado(estado);
        this.setClassificacao(classificacao);
        bebidaArrayList = new ArrayList<>();
        this.setImage(image);
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
     * getNomeRua
     * @return retorna a String nome da rua da classe Bar
     */

    public String getNomeRua() {
        return nomeRua;
    }

    /**
     * setNomeRua
     * @param nomeRua define a String nome da rua da classe Bar
     */

    public void setNomeRua(String nomeRua) {
        this.nomeRua = nomeRua;
    }

    /**
     * getNumeroEndereco
     * @return retorna a String número do local da classe Bar
     */

    public String getNumeroEndereco() {
        return numeroEndereco;
    }

    /**
     * setNumeroEndereco
     * @param numeroEndereco define a String número do local da classe Bar
     */

    public void setNumeroEndereco(String numeroEndereco) {
        this.numeroEndereco = numeroEndereco;
    }

    /**
     * getBairro
     * @return retorna a String do nome do bairro do local da classe Bar
     */

    public String getBairro() {
        return bairro;
    }

    /**
     * setBairro
     * @param bairro define a String do nome do bairro do local da classe Bar
     */

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * getMunicipio
     * @return retorna a String do nome do município do local da classe Bar
     */

    public String getMunicipio() {
        return municipio;
    }

    /**
     * setMunicipio
     * @param municipio define a String do nome do município do local da classe Bar
     */

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    /**
     * getEstado
     * @return retorna a String do nome do estado (UF) do local da classe Bar
     */

    public String getEstado() {
        return estado;
    }

    /**
     * setEstado
     * @param estado define a String do nome do estado (UF) do local da classe Bar
     */

    public void setEstado(String estado) {
        this.estado = estado;
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
