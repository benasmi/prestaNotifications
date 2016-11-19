package com.mbproductions.benas.prestanotifications;

import android.content.Intent;
import android.util.Log;

import com.google.android.gms.iid.InstanceIDListenerService;

/**
 * Created by Benas on 6/19/2016.
 */
public class GcmOnTokenRefresh extends InstanceIDListenerService {

    public GcmOnTokenRefresh(){
        Log.i("TEST", "GcmOnTokenRefresh");
    }


    @Override
    public void onTokenRefresh() {
        startService(new Intent(this, RegisterTokenService.class));
        super.onTokenRefresh();
    }
}
