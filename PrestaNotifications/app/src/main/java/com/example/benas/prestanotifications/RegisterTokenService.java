package com.example.benas.prestanotifications;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.android.gms.gcm.GcmPubSub;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;


public class RegisterTokenService extends IntentService {

    private static final String[] TOPICS = {"global"};
    private SharedPreferences sharedPreferences;

    public RegisterTokenService() {
        super("RegisterTokenService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        try {

            InstanceID instanceID = InstanceID.getInstance(this);
            String token = instanceID.getToken(getString(R.string.gcm_defaultSenderId),
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);

            sharedPreferences = this.getSharedPreferences("DataPrefs", Context.MODE_PRIVATE);
            sharedPreferences.edit().putString("device_id", token).commit();

            subscribeTopics(token);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void subscribeTopics(String token) throws IOException {
        GcmPubSub pubSub = GcmPubSub.getInstance(this);
        for (String topic : TOPICS) {
            pubSub.subscribe(token, "/topics/" + topic, null);
        }
    }
}
