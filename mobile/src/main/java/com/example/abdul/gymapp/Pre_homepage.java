package com.example.abdul.gymapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Pre_homepage extends AppCompatActivity {

    private Button button;
    private long isUserClickedBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_homepage);

        button = findViewById(R.id.Beginner);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBeginnerPage();
            }
        });

        button = findViewById(R.id.Intermediate);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openIntermediatePage();
            }
        });
    }

    public void openBeginnerPage(){
        Intent intent = new Intent(this, BeginnerPage.class);
        startActivity(intent);
    }

    public void openIntermediatePage(){
        Intent intent = new Intent(this, IntermediatePage.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed(){


        if (isUserClickedBackButton + 2000 > System.currentTimeMillis()){
            super.onBackPressed();
            return;
        }else {
            Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        isUserClickedBackButton = System.currentTimeMillis();
    }
}
