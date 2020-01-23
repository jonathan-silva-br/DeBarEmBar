package com.example.debarembar.presenter;

import android.util.Log;

import com.example.debarembar.model.Bar;
import com.example.debarembar.model.Bebida;

/**
 *  Classe CadastroPresenter - coordena a interação entre model e view
 *
 *  Classe responsável por coordenar a interação entre as classes Bar e Bebida do pacote model
 *  com o layout fragment_cadastro e a classe CadastroFragment do pacote view.
 *
 *  @since 3.0.0
 *  @author Jonathan Silva <silva_jonathan@outlook.com.br>
 */

public class CadastroPresenter {

    /**
     * Construtor CadastroPresenter
     *
     * Utiliza o método setBar().
     *
     * @param nomeLocal String Nome do local cadastrado
     * @param nomeRua String Nome da rua do endereço do local
     * @param numero String Número do endereço do local
     * @param bairro String Nome do bairro do endereço do local
     * @param municipio String Nome do município do endereço do local
     * @param estado String Nome do estado (UF) do endereço do local
     * @param classificacao Float Classificação do local
     * @param checkStella boolean CheckBox que indica se existe a venda dessa bebida no local
     * @param checkCorona boolean CheckBox que indica se existe a venda dessa bebida no local
     * @param checkBudweiser boolean CheckBox que indica se existe a venda dessa bebida no local
     * @param checkBecks boolean CheckBox que indica se existe a venda dessa bebida no local
     * @param valorStella String Valor dessa bebida no local
     * @param valorCorona String Valor dessa bebida no local
     * @param valorBudweiser String Valor dessa bebida no local
     * @param valorBecks String Valor dessa bebida no local
     *
     * @author Jonathan Silva <silva_jonathan@outlook.com.br>
     */

    public CadastroPresenter(String nomeLocal, String nomeRua, String numero, String bairro,
                             String municipio, String estado, float classificacao,
                             boolean checkStella, boolean checkCorona, boolean checkBudweiser, boolean checkBecks,
                             String valorStella, String valorCorona, String valorBudweiser, String valorBecks){

        setBar(nomeLocal, nomeRua, numero, bairro, municipio,
                estado, classificacao, checkStella, checkCorona, checkBudweiser, checkBecks,
                valorStella, valorCorona, valorBudweiser, valorBecks);
    }

    /**
     * setBar
     *
     * Método que instancia o objeto Bar, repassando como parâmetro uma String nomeLocal,
     * nomeRua, numero, bairro, municipio e estado para o construtor da classe Bar e instancia
     * as bebidas de maneira manual e fixa através do método setBebida().
     *
     * @param nomeLocal String Nome do local cadastrado
     * @param nomeRua String Nome da rua do endereço do local
     * @param numero String Número do endereço do local
     * @param bairro String Nome do bairro do endereço do local
     * @param municipio String Nome do município do endereço do local
     * @param estado String Nome do estado (UF) do endereço do local
     * @param classificacao Float Classificação do local
     * @param checkStella boolean CheckBox que indica se existe a venda dessa bebida no local
     * @param checkCorona boolean CheckBox que indica se existe a venda dessa bebida no local
     * @param checkBudweiser boolean CheckBox que indica se existe a venda dessa bebida no local
     * @param checkBecks boolean CheckBox que indica se existe a venda dessa bebida no local
     * @param valorStella String Valor dessa bebida no local
     * @param valorCorona String Valor dessa bebida no local
     * @param valorBudweiser String Valor dessa bebida no local
     * @param valorBecks String Valor dessa bebida no local
     *
     * @return retorna o objeto bar
     *
     * @author Jonathan Silva <silva_jonathan@outlook.com.br>
     */

    public void setBar(String nomeLocal, String nomeRua, String numero, String bairro, String municipio,
                      String estado, float classificacao, boolean checkStella, boolean checkCorona,
                      boolean checkBudweiser, boolean checkBecks, String valorStella, String valorCorona,
                      String valorBudweiser, String valorBecks){

        Bar bar = new Bar(nomeLocal, nomeRua, numero, bairro, municipio, estado, classificacao);

        /**
         * Verifica se o checkBox checkStella foi setado e se a String valorStella está preenchida
         * e então repassa essas informações para o método setBebida().
          */
        if(checkStella == true && valorStella != null){
            bar.setBebidaArrayList(setBebida("Stella Artois 550 mL", valorStella));
        }

        /**
         * Verifica se o checkBox checkCorona foi setado e se a String valorCorona está preenchida
         * e então repassa essas informações para o método setBebida().
         */

        if(checkCorona == true && valorCorona != null){
            bar.setBebidaArrayList(setBebida("Corona 355 mL", valorCorona));
        }

        /**
         * Verifica se o checkBudweiser checkCorona foi setado e se a String valorBudweiser está preenchida
         * e então repassa essas informações para o método setBebida().
         */

        if(checkBudweiser == true && valorBudweiser != null){
            bar.setBebidaArrayList(setBebida("Budweiser 343 mL", valorBudweiser));
        }

        /**
         * Verifica se o checkBecks checkCorona foi setado e se a String valorBecks está preenchida
         * e então repassa essas informações para o método setBebida().
         */

        if(checkBecks == true && valorBecks != null){
            bar.setBebidaArrayList(setBebida("Becks 330 mL", valorBecks));
        }

        /**
         * Log.e verifica no terminal se as informações está sendo repassada.
         */
        Log.e("Confere NomeLocal", bar.getNome());
        Log.e("Confere NomeRua", bar.getNomeRua());
        Log.e("Confere Numero", bar.getNumeroEndereco());
        Log.e("Confere Bairro", bar.getBairro());
        Log.e("Confere Municipio", bar.getMunicipio());
        Log.e("Confere Estado", bar.getEstado());
        Log.e("Confere Classificacao", String.valueOf(bar.getClassificacao()));
        Log.e("Confere Stella", String.valueOf(bar.getSize()));
    }

    /**
     * setBebida
     *
     * Método que instancia o objeto Bebida, repassando como parâmetro uma String nomeBebida e valorBebida
     * para o construtor da classe Bebida.
     *
     * @param nomeBebida String Nome da bebida
     * @param valorBebida String Valor da bebida
     * @return retorna o objeto bebida
     *
     * @author Jonathan Silva <silva_jonathan@outlook.com.br>
     */

    public Bebida setBebida(String nomeBebida, String valorBebida){
        Bebida bebida = new Bebida(nomeBebida, valorBebida);
        return bebida;
    }



}
