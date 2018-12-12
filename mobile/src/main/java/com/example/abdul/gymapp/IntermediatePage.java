package com.example.abdul.gymapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

public class IntermediatePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermediate_page);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,  toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item){

        switch (item.getItemId()){
            case R.id.nav_message:
                openexercises();
                break;

            case R.id.nav_pictures:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new PicturesFragement()).commit();
                break;

            case R.id.nav_share:
                Toast.makeText(this, "Share Exercises",
                        Toast.LENGTH_LONG).show();
                break;

            case R.id.nav_logout:
                Toast.makeText(this, "Send Exercises",
                        Toast.LENGTH_LONG).show();
                break;

            case R.id.nav_profile:
                openProfile();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed(){
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    public void openProfile(){
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }

    public void openexercises(){
        Intent intent = new Intent(this, exercises.class);
        startActivity(intent);
    }
}
