package com.example.debarembar.model;

/**
 *  Classe Bebida - define o modelo Bebida
 *
 *  Define o modelo Bebida que tem como parâmetros o nome, valorUnitario e imagem.
 *
 *  @since 1.1.0
 *  @author Jonathan Silva <silva_jonathan@outlook.com.br>
 */

public class Bebida {

    private String nome;
    private String valorUnitario;
    private int imagem;

    /**
     * Construtor Bebida
     *
     * @param nomeRepassado String Nome da Bebida repassado pelo usuário
     * @param valorUnitario String Valor da Bebida repassado pelo usuário
     *
     * @author Jonathan Silva <silva_jonathan@outlook.com.br>
     */

    public Bebida(String nomeRepassado, String valorUnitario){
        this.setNome(nomeRepassado);
        this.setValorUnitario(valorUnitario);
    }

    /**
     * getNome
     * @return retorna a String nome da classe Bebida
     */

    public String getNome() {
        return nome;
    }

    /**
     * setNome
     * @param nome define a String nome da classe Bebida
     */

    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * getValorUnitario
     * @return retorna o double valorUnitario da classe Bebida
     */

    public String getValorUnitario() {
        return valorUnitario;
    }

    /**
     * setValorUnitario
     * @param valorUnitario define o double valorUnitario da classe Bebida
     */

    public void setValorUnitario(String valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    /**
     * getImagem
     * @return retorna o int imagem da classe Bebida
     */

    public int getImagem() {
        return imagem;
    }

    /**
     * setImagem
     * @param imagem define o int imagem da classe Bebida
     */

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }


}
