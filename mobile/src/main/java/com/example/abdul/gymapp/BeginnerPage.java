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
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;
import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;


public class BeginnerPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private Button button;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beginner_page);
        mAuth = FirebaseAuth.getInstance();

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,  toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        button = findViewById(R.id.workout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWorkouts();
            }
        });
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
                openLogInPage();
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

    public void openLogInPage(){
        mAuth.signOut();
        Intent intent = new Intent(this, LoginPage.class);
        FLAG_ACTIVITY_CLEAR_TOP:
        startActivity(intent);
    }

    public void openWorkouts(){
        Intent intent = new Intent(this, workouts.class);
        startActivity(intent);
    }
}
