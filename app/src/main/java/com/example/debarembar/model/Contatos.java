package com.example.debarembar.model;

/**
 * Classe modelo para cada contato
 *
 * Nesta classe temos os atributos e verificações de setters && getters
 *
 *
 * @author Matheus Geiser <matheusgeiser@gmail.com>
 * @since 1.0.0
 *
 */
public class Contatos {

    private String numeroContato;
    private String nomeContato;

    /**
     * Construtor da classe para atribuir as informações ao atributo
     *
     * @author Matheus Geiser <matheusgeiser@gmail.com>
     * @since 1.0.0
     *
     * @param numeroContato que recebe o numero do contato
     * @param nomeContato que recebe o nome do contato
     */
    public Contatos(String numeroContato, String nomeContato){
        setNomeContato(nomeContato);
        setNumeroContato(numeroContato);
    }

    /**
     * Método que atribui o valor do parâmetro ao atributo global numeroContato
     *
     * @param numeroContato
     */
    public void setNumeroContato(String numeroContato) {
        if(!numeroContato.equals(null) || !numeroContato.equals(""))
            this.numeroContato = numeroContato;
    }

    /**
     * Método que atribui o valor do parâmetro ao atributo global nomeContato
     * @param nomeContato
     */
    public void setNomeContato(String nomeContato){
        if(!nomeContato.equals(null) || !nomeContato.equals(""))
            this.nomeContato = nomeContato;
    }

    /**
     * Método que retorna o numero do contato
     * @return String numeroContato
     */
    public String getNumeroContato() {
        return numeroContato;
    }

    /**
     * Método que retorna o nome do contato
     * @return String nomeContato
     */
    public String getNomeContato() {
        return nomeContato;
    }

}
