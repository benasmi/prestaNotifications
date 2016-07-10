package com.example.benas.prestanotifications;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import me.leolin.shortcutbadger.ShortcutBadger;

/**
 * Created by Benas on 6/19/2016.
 */
public class GcmMessageReceiver extends GcmListenerService {

    public GcmMessageReceiver() {
    }

    @Override
    public void onMessageReceived(String from, Bundle data) {

        SharedPreferences sharedPreferences = getSharedPreferences("notifications", Context.MODE_PRIVATE);


        int badgeCount = sharedPreferences.getInt("badgeCount", 0);
        badgeCount++;

        sharedPreferences.edit().putInt("badgeCount", badgeCount).commit();

        ShortcutBadger.applyCount(this, badgeCount);

        String message = data.getString("message");
        String type = data.getString("type");
        String message_date = data.getString("message_date");
        String order_reference = data.getString("order_reference");
        String buyer_name = data.getString("buyer_name");
        String cost = data.getString("cost");
        String order_amount = data.getString("order_amount");
        String payment_method = data.getString("payment_method");
        String order_status = data.getString("order_status");


        sendNotification(buyer_name, type, message, cost);


        String notification_data = sharedPreferences.getString("notification_data", "");

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.putOpt("message", message);
            jsonObject.putOpt("type", type);
            jsonObject.putOpt("message_date", message_date);
            jsonObject.putOpt("order_reference", order_reference);
            jsonObject.putOpt("buyer_name", buyer_name);
            jsonObject.putOpt("cost", cost);
            jsonObject.putOpt("order_amount", order_amount);
            jsonObject.putOpt("payment_method", payment_method);
            jsonObject.putOpt("order_status", order_status);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(notification_data.isEmpty()){
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(jsonObject);
            sharedPreferences.edit().putString("notification_data", jsonArray.toString()).commit();
            return;
        }

        try {
            JSONArray jsonArray = new JSONArray(notification_data);
            jsonArray.put(jsonObject);
            sharedPreferences.edit().putString("notification_data", jsonArray.toString()).commit();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent("UPDATE_REQUIRED"));
    }

    private void sendNotification(String buyer_name, String type, String message, String cost) {
        Intent intent = new Intent(this, NotificationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);

        if(type.equals("0")) {
                    notificationBuilder.setSmallIcon(R.drawable.app_icon)
                    .setContentTitle("New order from " + buyer_name)
                    .setContentText(cost)
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent);
        }else if(type.equals("1")){
                    notificationBuilder.setSmallIcon(R.drawable.app_icon)
                    .setContentTitle("New message from " + buyer_name)
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent);
        }

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }


}
