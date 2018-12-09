package com.example.abdul.gymapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class LoginPage extends AppCompatActivity {

    private Button button;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        button = findViewById(R.id.LogInButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPre_homepage();
            }
        });

        button = findViewById(R.id.register);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegisterPage();
            }
        });

        text = findViewById(R.id.forgottenpassword);
        text.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openReset();
            }
        });

    }

    public void openPre_homepage(){
        Intent intent = new Intent(this, Pre_homepage.class);
        startActivity(intent);
    }

    public void openRegisterPage(){
        Intent intent = new Intent(this, RegisterPage.class);
        startActivity(intent);
    }

    public void openReset(){
        Intent intent = new Intent(this, Reset.class);
        startActivity(intent);
    }
}