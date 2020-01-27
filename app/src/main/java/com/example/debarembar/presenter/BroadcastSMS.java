package com.example.debarembar.presenter;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.debarembar.view.MainActivity;

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

    private static final String CHANNEL_ID = "BAREMBAR_APP";
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

                    Log.e("TESTE",infos);
                    SharedPreferences preferences = context.getSharedPreferences("json_bank",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("barSMS",infos);
                    editor.apply();


                    criarNotificacaoSimples(context);

                }


            } // bundle is null

        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" +e.toString());

        }
    }

    /**
     *
     * Criador da Notificação
     *
     * Copiei e colei da documentação e funcionou
     *basicamente ele primeiro cria um canal para a nossa aplicação para poder
     * criar a notificação, depois cria um intent para quando você clicar na
     * notificação ele ir para o aplicativo. Depois é feito o build da
     * notificação com o icone, titulo e texto pequeno da notificação
     * e depois é exibida na barra de notificações a nossa notificação
     *  ;)
     *
     *
     * @link <a href="https://developer.android.com/training/notify-user/build-notification">Documentação</a>
     *
     * @author Matheus Geiser <matheusgeiser@gmail.com>
     *
     * @param context
     */
    public void criarNotificacaoSimples(Context context){
        int id = 1;
        String titulo = "NOVO BAR RECEBIDO!!";
        String texto = "Seu contato compartilhou um bar com você! Confira!!";
        int icone = android.R.drawable.ic_dialog_info;

        createNotificationChannel(context);

        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(icone)
                .setContentTitle(titulo)
                .setContentText(texto)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        // notificationId is a unique int for each notification that you must define
        int notificationId = 232;
        notificationManager.notify(notificationId, builder.build());
    }

    /**
     *
     * Criando um canal para a notificação
     *
     *
     * percebi que não é possível criar uma notificação sem um canal
     * e por isso que é uma coisa tão importante.
     *
     * @author Matheus Geiser <matheusgeiser@gmail.com>
     *
     *     <<DOCUMENTAÇÃO>>
     * @link https://developer.android.com/training/notify-user/channels
     *
     * @param context
     */

    private void createNotificationChannel(Context context) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "bar em bar";
            String description = "app do bar";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}
