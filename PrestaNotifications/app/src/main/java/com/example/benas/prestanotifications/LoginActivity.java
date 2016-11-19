package com.example.benas.prestanotifications;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class LoginActivity extends AppCompatActivity {

    private TextView username_text;
    private TextView password_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.push_right_in, R.anim.push_rigth_out);
        setContentView(R.layout.activity_login);

        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/manteka.ttf");

        TextView presta = (TextView) findViewById(R.id.presta_text_view);
        TextView notifications = (TextView) findViewById(R.id.notifications_text_view);
        presta.setTypeface(tf);
        notifications.setTypeface(tf);

        if(!CheckingUtils.isNetworkConnected(this)) {
            CheckingUtils.starterErrorBox("Please, enable your internet connection and start again", this);
            return;
        }else{



        startService(new Intent(this, RegisterTokenService.class));

        username_text = (TextView) findViewById(R.id.username_login);
        password_text = (TextView) findViewById(R.id.password_login);

        SharedPreferences sharedPreferences = getSharedPreferences("DataPrefs", MODE_PRIVATE);

        String username = sharedPreferences.getString("username","");
        String password = sharedPreferences.getString("password","");
        if(!username.isEmpty() && !password.isEmpty()){

            startActivity(new Intent(this, NotificationActivity.class));

        }
        }
    }

    public void login(View view){

        String username = username_text.getText().toString().trim();
        String password = password_text.getText().toString().trim();


        if(username.isEmpty()){
            username_text.setError("Please enter your username!");
            return;
        }
        if(password.isEmpty()){
            password_text.setError("Please enter your password!");
            return;
        }
        if(username.contains(" ")){
            username_text.setError("Username may not contain spaces!");
        }
        if(password.contains(" ")){
            password_text.setError("Password may not contain spaces!");
        }
        if(!CheckingUtils.isNetworkConnected(this)){
            CheckingUtils.createErrorBox("You need internet connection to login", this);
            return;
        }

        new ServerManager(this,"LOGIN").execute("LOGIN", username, password, "1");
    }

    public void getModule(View view) {
        String url = "http://addons.prestashop.com/en/24010-mobile-order-support-android-notifications.html";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}
