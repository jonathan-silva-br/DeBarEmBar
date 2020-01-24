package com.example.debarembar.presenter;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import org.json.JSONObject;

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


    private static final String CHANNEL_ID = "batata";
    private static final int notificationId = 222;

    @Override
    public void onReceive(Context context, Intent intent) {
        // Retrieves a map of extended data from the intent.
        final Bundle bundle = intent.getExtras();

        try {

            Object[] pdus = (Object[]) bundle.get("pdus");

            if (bundle != null) {

                for (int i = 0; i < pdus.length; i++){

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                    String senderNum = phoneNumber;
                    String message = currentMessage.getDisplayMessageBody();

                    if(message.contains("app@barembar")){
                        String[] m = message.split("====");

                        String infos = m[1];

                        JSONObject object = new JSONObject(infos);
                        Log.e("OBJETO RECEBIDO",object.toString());

                        notification(context);

                    }



                } // end for loop
            } // bundle is null

        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" +e);

        }
    }

    private void notification(Context context){


        try{
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                    //.setSmallIcon(R.drawable.notification_icon)
                    .setContentTitle("Batata é bom")
                    .setContentText("mensagem")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                CharSequence name = "";
                String description = "";
                int importance = NotificationManager.IMPORTANCE_DEFAULT;
                NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
                channel.setDescription(description);
                // Register the channel with the system; you can't change the importance
                // or other notification behaviors after this
                NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
                notificationManager.createNotificationChannel(channel);
                notificationManager.notify(notificationId, builder.build());
            }else{
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

                // notificationId is a unique int for each notification that you must define

                notificationManager.notify(notificationId, builder.build());

            }
        }catch(Exception e){
            Log.e("ErroNotificacao",e.toString());
        }

    }

}
