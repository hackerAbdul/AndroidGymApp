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
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;


public class RegisterPage extends AppCompatActivity {

    private EditText text;
    private Button button;
    private FirebaseAuth mAuth;
    EditText Name,Email,Password,repassword;
    private Firebase mRootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        mAuth = FirebaseAuth.getInstance();
        mRootRef = new Firebase("https://gymapp-99a58.firebaseio.com/Users");

        Name = findViewById(R.id.Name);
        Email = findViewById(R.id.Email);
        Password = findViewById(R.id.Password);
        repassword = findViewById(R.id.repassword);

        text = findViewById(R.id.Name);
        text = findViewById(R.id.Email);


        button = findViewById(R.id.signup);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registeruser();
            }
        });




    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }


    //validation for the register page
    private void registeruser() {
        final String name = Name.getText().toString().trim();
        final String email = Email.getText().toString().trim();
        final String password = Password.getText().toString().trim();
        final String password2 = repassword.getText().toString().trim();

        if (name.isEmpty()){
            Name.setError("Name is required");
            Name.requestFocus();
            return;
        }

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

        if (password2.isEmpty()){
            repassword.setError("Cannot leave this blank");
            repassword.requestFocus();
            return;
        }

        if (!password2.equals(password)){
            Toast.makeText(this, "Password do not match",
                    Toast.LENGTH_LONG).show();
        }

        Task<AuthResult> authResultTask = mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(String.valueOf(this), "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            String UserId = mAuth.getUid();
                            Toast.makeText(RegisterPage.this, "Authentication Successful",
                                    Toast.LENGTH_SHORT).show();
                            String value = text.getText().toString();
                            Firebase childRef = mRootRef.child(String.valueOf(UserId));

                            childRef.child("Name").setValue(value);
                            childRef.child("Email").setValue(value);

                            openPre_homepage();
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(String.valueOf(this), "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterPage.this, "Authentication Unsuccessful",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }
    public void openPre_homepage(){
        Intent intent = new Intent(this, Pre_homepage.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


}
