package com.example.debarembar.model;

import java.util.ArrayList;

public class BarTeste {
    private String nome;
    private String imagem;
    private int avaliacao;
    private ArrayList<String> product;

    public ArrayList<String> getProduct() {
        return product;
    }

    public void setProduct(ArrayList<String> product) {
        this.product = product;
    }

    public BarTeste(String nome, String imagem, int avaliacao){
        setNome(nome);
        setImagem(imagem);
        setAvaliacao(avaliacao);
    }
    public BarTeste(String nome, int avaliacao, ArrayList<String> listProduct ){
        setNome(nome);
        setAvaliacao(avaliacao);
        setProduct(listProduct);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }

    @Override
    public String toString() {
        return "BarTeste{" +
                "nome='" + nome + '\'' +
                ", imagem='" + imagem + '\'' +
                ", avaliacao=" + avaliacao +
                ", product=" + product +
                '}';
    }
}
