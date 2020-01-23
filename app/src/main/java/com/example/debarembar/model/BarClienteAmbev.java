package com.example.debarembar.model;

import com.example.debarembar.R;

import java.util.ArrayList;

public class BarClienteAmbev {
    private String name;
    private int image;
    private double evaluation;
    private ArrayList<ProdutosAmbev> productsAmbev;
    private int localizacao;
    private CEP cep;

    public BarClienteAmbev(String name, int image, double evaluation, ArrayList<ProdutosAmbev> productsAmbev, int localizacao, CEP cep) {
        setName(name);
        setImage(image);
        setEvaluation(evaluation);
        setProductsAmbev(productsAmbev);
        setLocalizacao(localizacao);
        setCep(cep);
    }

    public CEP getCep() {
        return cep;
    }

    public void setCep(CEP cep) {
        this.cep = cep;
    }

    public int getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(int localizacao) {
        this.localizacao = localizacao;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public double getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(double evaluation) {
        this.evaluation = evaluation;
    }

    public ArrayList<ProdutosAmbev> getProductsAmbev() {
        return productsAmbev;
    }

    public void setProductsAmbev(ArrayList<ProdutosAmbev> productsAmbev) {
        this.productsAmbev = productsAmbev;
    }




}
