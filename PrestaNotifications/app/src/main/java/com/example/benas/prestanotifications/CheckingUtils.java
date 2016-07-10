package com.example.benas.prestanotifications;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;

import java.util.Date;

/**
 * Created by Benas on 6/27/2016.
 */
public class CheckingUtils {

    private Context context;

    CheckingUtils(Context context){
        this.context = context;
    }


    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    //Error box to inform UI
    public static void createErrorBox(String message, Context context){
        new AlertDialog.Builder(context, R.style.MyAlertDialogStyle)
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
    public static void starterErrorBox(String message, Context context){
        new AlertDialog.Builder(context, R.style.MyAlertDialogStyle)

                .setMessage(message)
                .setCancelable(false)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(1);
                        dialog.dismiss();
                    }
                })
                .show();
    }



}
