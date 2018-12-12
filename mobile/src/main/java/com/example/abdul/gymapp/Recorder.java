package com.example.abdul.gymapp;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class Recorder extends AppCompatActivity {

    private Button play, stop, start;
    private MediaRecorder myAudioRecorder;
    private String outputFile;

    private String [] permissions = {"android.permission.RECORD_AUDIO", "android.permission.WRITE_EXTERNAL_STORAGE"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorder);

        int requestCode = 200;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }

        play = findViewById(R.id.play);
        start = findViewById(R.id.start);
        stop = findViewById(R.id.stop);

        stop.setEnabled(false);
        play.setEnabled(false);

        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording.3gp";

        myAudioRecorder = new MediaRecorder();
        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);

        //Define the 3gp output format
        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        myAudioRecorder.setOutputFile(outputFile);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    myAudioRecorder.prepare();
                    myAudioRecorder.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                start.setEnabled(false);
                stop.setEnabled(true);

                Toast.makeText(getApplicationContext(), "Recording Started",
                        Toast.LENGTH_LONG).show();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudioRecorder.stop();
                myAudioRecorder.release();
                myAudioRecorder = null;

                start.setEnabled(true);
                stop.setEnabled(false);
                play.setEnabled(true);

                Toast.makeText(getApplicationContext(), "Recording Finished",
                        Toast.LENGTH_LONG).show();
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mediaPlayer = new MediaPlayer();

                try {
                    mediaPlayer.setDataSource(outputFile);
                    mediaPlayer.prepare();
                    mediaPlayer.start();

                    Toast.makeText(getApplicationContext(), "Audio Playing",
                            Toast.LENGTH_LONG).show();

                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
    }
}
