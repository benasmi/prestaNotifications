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
    private int response;
    private String username;
    private String password;
    private GoogleCloudMessaging gcm;
    private String reg_id;

    public ServerManager(Context context){
        sharedPreferences = context.getSharedPreferences("DataPrefs", Context.MODE_PRIVATE);
        this.context = context;
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
    //Error box to inform UI
    private void createErrorBox(String message){
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
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
            response = login(username, password);
        }


        return null;
    }

    //Checking response codes for actions
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        dismissDialog();
        if(method_type.equals("LOGIN")){

            switch (response){
                case 0:
                    context.startActivity(new Intent(context, NotificationActivity.class));

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username", username);
                    editor.putString("password", password);
                    editor.commit();
                    break;
                case 1:
                    createErrorBox("Invalid email or password");
                    break;
            }
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



}
