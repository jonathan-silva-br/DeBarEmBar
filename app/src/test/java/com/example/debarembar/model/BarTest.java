package com.example.debarembar.model;

import org.junit.Assert;
import org.junit.Test;

/**
 *  Teste Classe Bar
 *
 *  @author Jonathan Silva <silva_jonathan@outlook.com.br>
 */

public class BarTest {

    /**
     *  Teste do Parâmetro nome
     *
     *  @author Jonathan Silva <silva_jonathan@outlook.com.br>
     */

    @Test
    public void testNomeConstrutor(){
        String nome = "Tio João";
        String localizacao = "-26.917348, -49.071047";
        Bar bar = new Bar(nome, localizacao);
        Assert.assertEquals(nome,bar.getNome());
    }

    /**
     *  Teste do Parâmetro localizacao
     *
     *  @author Jonathan Silva <silva_jonathan@outlook.com.br>
     */

    @Test
    public void testLocalizacaoConstrutor(){
        String nome = "Tio João";
        String localizacao = "-26.917348, -49.071047";
        Bar bar = new Bar(nome, localizacao);
        Assert.assertEquals(localizacao,bar.getLocalizacao());
    }

    /**
     *  Teste do Parâmetro bebidaArrayList
     *
     *  @author Jonathan Silva <silva_jonathan@outlook.com.br>
     */

    @Test
    public void testArrayBebidas(){
        String nome = "Tio João";
        String localizacao = "-26.917348, -49.071047";
        Bar bar = new Bar(nome, localizacao);

        String nomeBebida = "Stella Artois";
        Bebida bebida = new Bebida(nomeBebida);

        bar.setBebidaArrayList(bebida);

        Assert.assertEquals(1,bar.getSize());
    }





}