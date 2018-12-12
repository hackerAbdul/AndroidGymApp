package com.example.abdul.gymapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class workouts extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private Firebase mRootRef;

    private ListView mListView;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workouts);
        mAuth = FirebaseAuth.getInstance();
        mRootRef = new Firebase("https://gymapp-99a58.firebaseio.com/Users");

        Firebase.setAndroidContext(this);

        mListView = findViewById(R.id.listview);
        mTextView = findViewById(R.id.SavedExercises);


        mRootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String id = mAuth.getCurrentUser().getUid();
                List<String> your_array_list = new ArrayList<String>();
                for (DataSnapshot snapshot:dataSnapshot.child(id).child("All Exercises").getChildren()){

                    String exercise = snapshot.child("Exercise").getValue(String.class);
                    String sets = snapshot.child("Sets").getValue(String.class);
                    String reps = snapshot.child("Reps").getValue(String.class);
                    String record = exercise + ": " + sets + "x" + reps;
                    your_array_list.add(record);



                }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.listcolour, your_array_list );
                mListView.setAdapter(arrayAdapter);




            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    // Read from the database

}
