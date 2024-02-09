package com.example.impactmakers.electionvote;

import android.Manifest;
import android.app.DownloadManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import android.media.AudioManager;
import android.media.MediaPlayer;


public class SongActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private String userName;
    private static final int REQUEST_CODE_SHARE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
        }

        // Get the user name passed from MainActivity
        Intent intent = getIntent();
        Uri songUri = Uri.parse(intent.getStringExtra("songUri"));
        userName = new File(songUri.getPath()).getName().replace(".mp3", "");

        // Initialize the MediaPlayer with the song file
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(getApplicationContext(), songUri);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set up the play button
        Button buttonPlay = findViewById(R.id.button_play);
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                } else {
                    mediaPlayer.start();
                }
            }
        });

        Button buttonDownload = findViewById(R.id.button_download);
        buttonDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(SongActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    // Permission not granted, request it again
                    ActivityCompat.requestPermissions(SongActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            1);
                } else {
                    // Permission already granted, proceed with download
                    String downloadUrl = intent.getStringExtra("songUri");
                    DownloadManager.Request request = new DownloadManager.Request(Uri.parse(downloadUrl));
                    request.setTitle(userName);
                    request.setDescription("Downloading " + userName);
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, userName + ".mp3");
                    DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                    if (downloadManager != null) {
                        downloadManager.enqueue(request);
                        Toast.makeText(SongActivity.this, "Downloading...", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        Button buttonShare = findViewById(R.id.button_share);
        buttonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Share the MP3 file
                File mp3File = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC), userName + ".mp3");
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("audio/mp3");
                Uri mp3Uri = FileProvider.getUriForFile(SongActivity.this, BuildConfig.APPLICATION_ID + ".fileprovider", mp3File);
                shareIntent.putExtra(Intent.EXTRA_STREAM, mp3Uri);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Download this Song");
                shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivityForResult(Intent.createChooser(shareIntent, "Share song via"), REQUEST_CODE_SHARE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SHARE && resultCode == RESULT_OK) {
            // Handle the result of the sharing activity
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Release the MediaPlayer before exiting
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
    @Override
    public void onBackPressed () {
        new AlertDialog.Builder(this)
                .setTitle("Confirm exit")
                .setMessage("Do you really want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Release the MediaPlayer before exiting
                        if (mediaPlayer != null) {
                            mediaPlayer.release();
                            mediaPlayer = null;
                        }
                        SongActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
    }