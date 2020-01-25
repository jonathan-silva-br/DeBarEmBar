package com.example.debarembar.model;

import org.junit.Assert;
import org.junit.Test;

/**
 *  Teste Classe Bebida
 *
 *  @author Jonathan Silva <silva_jonathan@outlook.com.br>
 */

public class BebidaTest {

    /**
     *  Teste do Parâmetro nome
     *
     *  @author Jonathan Silva <silva_jonathan@outlook.com.br>
     */

    @Test
    public void testNomeConstrutor(){
        String nome = "Stella Artois";
        Bebida bebida = new Bebida(nome);
        Assert.assertEquals(nome,bebida.getNome());
    }

}