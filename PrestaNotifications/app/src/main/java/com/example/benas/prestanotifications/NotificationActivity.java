package com.example.benas.prestanotifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.AvoidXfermode;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private RecycleAdapter adapter;
    private ArrayList<InfoHolder> data = new ArrayList<InfoHolder>();
    private boolean isReceiverRegistered;
    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        SharedPreferences sharedPreferences = getSharedPreferences("notifications", MODE_PRIVATE);

        String NotificationDataRaw = sharedPreferences.getString("notification_data", "");

        if(!NotificationDataRaw.isEmpty()) {
            try {
                JSONArray jsonArray = new JSONArray(NotificationDataRaw);
                for (int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    String message = "Message: " +  jsonObject.getString("message");
                    String type = jsonObject.getString("type");
                    String message_date = jsonObject.getString("message_date");
                    String order_reference = "Order reference: " + jsonObject.getString("order_reference");
                    String buyer_name = "Buyer name: " + jsonObject.getString("buyer_name");
                    String cost = "Cost: " + jsonObject.getString("cost");
                    String order_amount = "Order amount: " + jsonObject.getString("order_amount");
                    String payment_method = "Payment method: " + jsonObject.getString("payment_method");
                    String order_status = "Order status: " + jsonObject.getString("order_status");

                    data.add(new InfoHolder(cost, type, buyer_name, order_amount, message_date, order_reference, order_status, payment_method, true));

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        adapter = new RecycleAdapter(this, data);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ItemTouchHelper.Callback callback = new RecyclerViewHelper(adapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView);

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                Log.i("TEST", "DAEJOOOOOO");

                    SharedPreferences sharedPreferences = getSharedPreferences("notifications", MODE_PRIVATE);
                    String NotificationDataRaw = sharedPreferences.getString("notification_data", "");

                    JSONArray jsonArray = null;
                    try {
                        jsonArray = new JSONArray(NotificationDataRaw);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    JSONObject jsonObject = null;
                    try {
                        jsonObject = jsonArray.getJSONObject(jsonArray.length()-1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    try {
                        String message = "Message: " +  jsonObject.getString("message");
                        String type = jsonObject.getString("type");
                        String message_date =  jsonObject.getString("message_date");
                        String order_reference = "Order reference: " + jsonObject.getString("order_reference");
                        String buyer_name = "Buyer name: " + jsonObject.getString("buyer_name");
                        String cost = "Cost: " + jsonObject.getString("cost");
                        String order_amount = "Order amount: " + jsonObject.getString("order_amount");
                        String payment_method = "Payment method: " + jsonObject.getString("payment_method");
                        String order_status = "Order status: " + jsonObject.getString("order_status");
                        adapter.add(new InfoHolder(cost, type, buyer_name, order_amount, message_date, order_reference, order_status, payment_method, true));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


            }
        };

        registerReceiver();

    }



    @Override
    protected void onResume() {
        registerReceiver();
        super.onResume();
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
        isReceiverRegistered = false;
        super.onPause();
    }

    private void registerReceiver(){
        if(!isReceiverRegistered) {
            LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver,
                    new IntentFilter("UPDATE_REQUIRED"));
            isReceiverRegistered = true;
        }
    }
}