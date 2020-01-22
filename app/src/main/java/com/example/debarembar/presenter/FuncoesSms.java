package com.example.debarembar.presenter;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.telephony.SmsManager;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

/**
 * Classe responsável pelas funções de SMS
 *
 * @author Matheus Geiser <matheusgeiser@gmail.com>
 * @since 1.0.0
 *
 */
public class FuncoesSms {

    /**
     * Método responsável por enviar o SMS
     *
     * Através do método checkpermission, se tiver a permissão necessária
     * ele irá utilizar o smsManager para enviar uma mensagem para o número
     * informado no parâmetro com o conteúdo do {@param mensagem}.
     *
     * @author Matheus Geiser <matheusgeiser@gmail.com>
     * @since 1.0.0
     *
     * @param c - contexto do aplicativo
     * @param numero - numero do telefone que estou enviando
     * @param mensagem - conteúdo do SMS
     */
    public void SendSms(Context c, String numero, String mensagem){


        if(checkPermission(Manifest.permission.SEND_SMS, c)){
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(numero,null,mensagem,null,null);
            Toast.makeText(c, "Compartilhado!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(c, "Não foi possível enviar o SMS", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     *
     * Método que ve se tem a permissão necessária
     *
     * @author Matheus Geiser <matheusgeiser@gmail.com>
     * @since 1.0.0
     *
     * @param permission
     * @param context
     * @return true se houver permissão, se não ele retorna false
     */
    public boolean checkPermission(String permission, Context context){
        int check = ContextCompat.checkSelfPermission(context,permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }


}
