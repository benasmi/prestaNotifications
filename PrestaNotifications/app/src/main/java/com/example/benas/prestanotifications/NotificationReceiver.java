package com.example.benas.prestanotifications;

import android.os.Bundle;

import com.google.android.gms.gcm.GcmListenerService;

/**
 * Created by Martynas on 17/06/2016.
 */
public class NotificationReceiver extends GcmListenerService{
    @Override
    public void onMessageReceived(String s, Bundle bundle) {
        super.onMessageReceived(s, bundle);

    }
}
