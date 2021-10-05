package com.example.appmusic;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView btnPlay;
    private ImageView btnPause;
    private ServiceConnection serviceConnection;

    private boolean isConnected;
    private MusicPlayerService musicPlayerService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectService();
        btnPlay =  findViewById(R.id.imageView3);
        btnPause = findViewById(R.id.imageView11);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isConnected){
                    return;
                }
                boolean isPlaying = musicPlayerService.play();

                Toast.makeText(musicPlayerService, isPlaying ? "Playing music" : "Pause Music", Toast.LENGTH_SHORT).show();

            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isConnected){
                    return;
                }
                boolean isPause = musicPlayerService.pause();

                Toast.makeText(musicPlayerService, isPause ? "Pause Music" : "Playing Music", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void connectService() {

        Intent intent = new Intent(this, MusicPlayerService.class);

        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                MusicPlayerService.MyServiceBinder myBinder = (MusicPlayerService.MyServiceBinder) service;

                musicPlayerService = myBinder.getService();
                isConnected = true;
                Toast.makeText(musicPlayerService, "Connected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                isConnected = false;
                Toast.makeText(musicPlayerService, "Disconnected", Toast.LENGTH_SHORT).show();
                musicPlayerService = null;

            }
        };
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }
}