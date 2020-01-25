package com.example.debarembar.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Classe responsável pelos testes da classe Contatos
 *
 * Nesta classe temos os testes dos métodos e construtores da classe
 * Contatos
 *
 * @author Matheus Geiser <matheusgeiser@gmail.com>
 * @since 1.0.0
 *
 */
public class ContatosTest {


    @Test
    public void test_contatos_constructor(){
        Contatos victoria = new Contatos("+5547954543145","Joao");
        Assert.assertEquals("Joao", victoria.getNomeContato());
    }

}