package com.example.abdul.gymapp;
import android.graphics.Color;
import android.view.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void text_msg(View view){
        TextView tv= (TextView) findViewById(R.id.forgottenpassword);

        //alter text of textview widget
        tv.setText("This text view is clicked");

        //assign the textview forecolor
        tv.setTextColor(Color.GREEN);
    }

}
