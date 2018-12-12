package com.example.abdul.gymapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;

public class exercises extends AppCompatActivity {

    EditText exercise,sets,reps;
    private Button button;
    private FirebaseAuth mAuth;
    private Firebase mRootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);
        mAuth = FirebaseAuth.getInstance();
        mRootRef = new Firebase("https://gymapp-99a58.firebaseio.com/Users");

        exercise = findViewById(R.id.exercise);
        sets = findViewById(R.id.sets);
        reps = findViewById(R.id.reps);


        button = findViewById(R.id.submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Exercise = exercise.getText().toString();
                final String Sets = sets.getText().toString();
                final String Reps = reps.getText().toString();

                if (Exercise.isEmpty()){
                    exercise.setError("Cannot leave empty");
                    exercise.requestFocus();
                    return;
                }

                if (Exercise.isEmpty()){
                    reps.setError("Cannot leave empty");
                    reps.requestFocus();
                    return;
                }

                if (Sets.isEmpty()){
                    sets.setError("Cannot leave empty");
                    sets.requestFocus();
                    return;
                }

                String id = mAuth.getCurrentUser().getUid();
                Toast.makeText(exercises.this, "Exercise Saved",
                        Toast.LENGTH_SHORT).show();
                Firebase childRef = mRootRef.child(String.valueOf(id));
                childRef.child("exercises").push().setValue(exercise.getText().toString());
                childRef.child("Sets").push().setValue(sets.getText().toString());
                childRef.child("Reps").push().setValue(reps.getText().toString());
                finish();
            }

        });

    }
}
