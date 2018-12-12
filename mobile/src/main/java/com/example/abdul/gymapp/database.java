package com.example.abdul.gymapp;

import android.app.Application;

import com.firebase.client.Firebase;

public class database extends Application {

    @Override
    public void onCreate(){
        super.onCreate();

        Firebase.setAndroidContext(this);
    }
}

