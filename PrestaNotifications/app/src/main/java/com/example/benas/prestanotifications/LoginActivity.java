package com.example.benas.prestanotifications;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    private TextView username_text;
    private TextView password_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        username_text = (TextView) findViewById(R.id.username_login);
        password_text = (TextView) findViewById(R.id.password_login);
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

        new ServerManager(this).execute("LOGIN", username, password);
    }
}
