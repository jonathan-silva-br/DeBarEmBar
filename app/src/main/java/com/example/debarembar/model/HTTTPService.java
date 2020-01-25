package com.example.debarembar.model;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;



//parametro do AsyncTask = tipo de informação    /   referente ao progresso da requisição    /   Retorno do consumo do webService
public class HTTTPService extends AsyncTask<Void, Void , CEP> {

    private final String cep;

    public HTTTPService(String cep){
        this.cep = cep;
    }

    @Override
    protected CEP doInBackground(Void... voids) {
        StringBuilder resposta = new StringBuilder();       //pega o retorno da conecxao "connection.connect();" e passa pra uma variavel
        try {
            URL url = new URL("http://ws.matheuscastiglioni.com.br/ws/cep/find/" + this.cep + "/json");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");      //tipo q o web service deve retornar @(CHAVE, JSON)
            connection.setConnectTimeout(5000);     //se der mais de 5 seg da o erro de TimeOut
            connection.connect();

            Scanner scanner = new Scanner(url.openStream()) ;       //pega a resposta e transforma em uma Stram e faz a leitura com Scanner
            while(scanner.hasNext()){  //enquanto o scanner tiver linhas para ser lidas, adicionar a linha na StringBuilder
                resposta.append(scanner.next());        //ele pega a linha e adiciona dentro no StringBuilder
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Gson().fromJson(resposta.toString(), CEP.class);     //pega a resposta q foi lida e converte para o objeto do tipo CEP
    }
}
