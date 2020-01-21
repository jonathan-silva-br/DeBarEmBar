package com.example.debarembar.model;

/**
 *  Classe Bebida - define o modelo Bebida
 *
 *  Define o modelo Bebida que tem como parâmetros o nome, valorUnitario e imagem.
 *
 *  @since 1.0.0
 *  @author Jonathan Silva <silva_jonathan@outlook.com.br>
 */

public class Bebida {

    private String nome;
    private double valorUnitario;
    private int imagem;

    /**
     * Construtor Bebida
     *
     * @param nomeRepassado String Nome da Bebida repassado pelo usuário
     *
     * @author Jonathan Silva <silva_jonathan@outlook.com.br>
     */

    public Bebida(String nomeRepassado){
        this.setNome(nomeRepassado);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }


}
