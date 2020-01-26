package com.example.debarembar.presenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 *
 * Classe responsável pelo recebimento do Broadcast
 *
 * No onReceive pegamos o intent e colocamos no bundle para que possamos
 * retirar os pdus, para assim construir a mensagem do sms que foi captada.
 * Se o bundle for nulo, então ele irá percorrer os pdus e pegar as mensagens.
 *
 * Logo abaixo é realizada uma verificação se a mensagem que recebemos tem
 * o app@barembar, que é uma maneira de identificar que o sms é proveniente
 * do nosso próprio aplicativo, para assim transformar o conteúdo dele em
 * um JSONObject, para adicionar na lista da tela dos bares.
 *
 * @author Matheus Geiser <matheusgeiser@gmail.com>
 * @since 1.0.0
 *
 */
public class BroadcastSMS extends BroadcastReceiver {

    private String mensagemCompleta = "";

    @Override
    public void onReceive(Context context, Intent intent) {
        // Retrieves a map of extended data from the intent.
        final Bundle bundle = intent.getExtras();

        try {

            Object[] pdus = (Object[]) bundle.get("pdus");

            if (bundle != null) {

                for (int i = 0; i < pdus.length; i++){

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);

                    mensagemCompleta+= currentMessage.getDisplayMessageBody();


                } // end for loop

                if(mensagemCompleta.contains("app@barembar")){
                    String[] m = mensagemCompleta.split("====");
                    String infos = m[1];

                    SharedPreferences preferences = context.getSharedPreferences("json_bank",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    Log.e("LOOG",infos);
                    editor.putString("barSMS",infos);

                }


            } // bundle is null

        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" +e);

        }
    }

}
