package com.example.benas.prestanotifications;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Benas on 6/14/2016.
 */
public class ServerManager extends AsyncTask<String, String, String>{

    private final Context context;
    private final SharedPreferences sharedPreferences;
    private ProgressDialog dialog;
    private String method_type;
    private String dialog_type;
    private int response;
    private String username;
    private String startActivity;
    private String password;
    private GoogleCloudMessaging gcm;
    private String reg_id;

    public ServerManager(Context context, String dialog_type){
        sharedPreferences = context.getSharedPreferences("DataPrefs", Context.MODE_PRIVATE);
        this.context = context;
        this.dialog_type = dialog_type;
    }

    //Dialog box
    private void createDialog(String message){
        dialog = new ProgressDialog(context);
        dialog.setCancelable(false);
        dialog.setMessage(message);
        dialog.show();
    }
    private void dismissDialog(){
        if(dialog != null && dialog.isShowing()){
            dialog.dismiss();
        }

    }


    //Crearing dialog boxes, etc...
    @Override
    protected void onPreExecute() {

        super.onPreExecute();
    }


    //Sending information to server.
    @Override
    protected String doInBackground(String... params) {
        method_type = params[0];

        if(method_type.equals("LOGIN")){

            username=params[1];
            password=params[2];
            startActivity = params[3];
            response = login(username, password);
        }else if(method_type.equals("LOGOUT")){
            logout();
        }


        return null;
    }

    //Checking response codes for actions
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        dismissDialog();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(method_type.equals("LOGIN")){

            switch (response){
                case 0:
                    if(startActivity.equals("1")){
                    context.startActivity(new Intent(context, NotificationActivity.class));
                    }

                    editor.putString("username", username);
                    editor.putString("password", password);
                    editor.commit();
                    break;
                case 1:
                    CheckingUtils.createErrorBox("Invalid email or password", context);

                    editor.putString("username","");
                    editor.putString("password","");
                    editor.commit();

                    context.startActivity(new Intent(context, LoginActivity.class));

                    break;
            }
        }else if(method_type.equals("LOGOUT")){
            sharedPreferences.edit().putString("username","").commit();
            sharedPreferences.edit().putString("password","").commit();
            context.getSharedPreferences("notifications", Context.MODE_PRIVATE).edit().putString("notification_data", "").commit();
            context.startActivity(new Intent(context, LoginActivity.class));
        }
    }


    //Login method
    private int login(String username, String password) {

        //Connect to mysql.
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://bm.binded.co.uk/login.php");


        //JSON object.
        JSONObject jsonObject = new JSONObject();

        try {
            Log.i("TEST", sharedPreferences.getString("device_id", "error"));
            jsonObject.putOpt("email_username", username);
            jsonObject.putOpt("password", password);
            jsonObject.putOpt("device_id", sharedPreferences.getString("device_id", ""));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        EntityBuilder entity = EntityBuilder.create();
        entity.setText(jsonObject.toString());
        httpPost.setEntity(entity.build());

        JSONObject responseObject = null;

        try {
            //Getting response
            HttpResponse response = httpClient.execute(httpPost);
            String responseBody = EntityUtils.toString(response.getEntity());
            System.err.println(responseBody);
            responseObject = new JSONObject(responseBody);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            return responseObject.getInt("code");
        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private void logout() {

        //Connect to mysql.
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://bm.binded.co.uk/delete_token.php");


        //JSON object.
        JSONObject jsonObject = new JSONObject();

        try {

            jsonObject.putOpt("email_username", sharedPreferences.getString("username", ""));
            jsonObject.putOpt("password", sharedPreferences.getString("password", ""));
            jsonObject.putOpt("device_id", sharedPreferences.getString("device_id", ""));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        EntityBuilder entity = EntityBuilder.create();
        entity.setText(jsonObject.toString());
        httpPost.setEntity(entity.build());

        JSONObject responseObject = null;

        try {
            //Getting response
            HttpResponse response = httpClient.execute(httpPost);
            String responseBody = EntityUtils.toString(response.getEntity());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
