package com.example.abdul.gymapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginPage extends AppCompatActivity {

    private long isUserClickedBackButton;

    private Button button;
    private TextView text;

    private FirebaseAuth mAuth;
    EditText Email,Password;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        mAuth = FirebaseAuth.getInstance();


        Email = findViewById(R.id.Email);
        Password = findViewById(R.id.Password);


        button = findViewById(R.id.LogInButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginuser();
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

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }




    //login authentication for users to access the app
    private void loginuser(){
        final String email = Email.getText().toString().trim();
        final String password = Password.getText().toString().trim();

        if (email.isEmpty()){
            Email.setError("Cannot leave empty");
            Email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Email.setError("Please enter a valid email  ");
            Email.requestFocus();
            return;
        }

        if (password.isEmpty()){
            Password.setError("Cannot leave this blank");
            Password.requestFocus();
            return;
        }

        Task<AuthResult> authResultTask = mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(String.valueOf(this), "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            openPre_homepage();
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(String.valueOf(this), "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginPage.this, "Invalid Email and Password",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
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
