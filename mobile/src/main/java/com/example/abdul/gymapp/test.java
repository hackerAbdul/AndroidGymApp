package com.example.abdul.gymapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;

public class test extends AppCompatActivity {

    EditText exercise,sets,reps;
    private Button button;
    private FirebaseAuth mAuth;
    private Firebase mRootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mAuth = FirebaseAuth.getInstance();
        mRootRef = new Firebase("https://gymapp-99a58.firebaseio.com/Users");

        exercise = findViewById(R.id.exercise);
        sets = findViewById(R.id.sets);
        reps = findViewById(R.id.reps);

        button = findViewById(R.id.submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = mAuth.getCurrentUser().getUid();
                Toast.makeText(test.this, exercise.getText(),
                        Toast.LENGTH_SHORT).show();
                Firebase childRef = mRootRef.child(String.valueOf(id));
                childRef.child("exercise").setValue(exercise.getText().toString());
                childRef.child("sets").setValue(sets.getText().toString());
                childRef.child("reps").setValue(reps.getText().toString());
            }
        });
    }
}
