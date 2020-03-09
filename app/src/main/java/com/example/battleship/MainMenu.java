package com.example.battleship;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenu extends AppCompatActivity  {
    MediaPlayer start;
    VideoView mainVid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        start = MediaPlayer.create(MainMenu.this,R.raw.start);
        start.start();

        mainVid = (VideoView)findViewById(R.id.videoView2);
        String videopath = "android.resource://com.example.battleship/" + R.raw.updatemain;
        Uri uri = Uri.parse(videopath);
        mainVid.setVideoURI(uri);
        mainVid.start();

    }

}
