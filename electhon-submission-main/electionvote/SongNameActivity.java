package com.example.impactmakers.electionvote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class SongNameActivity extends AppCompatActivity {
    private EditText editTextName;
    private Button buttonGenerateSong;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_name);

        FirebaseApp.initializeApp(this);

        editTextName = findViewById(R.id.editTextName);
        buttonGenerateSong = findViewById(R.id.buttonGenerateSong);
        progressBar = findViewById(R.id.progressBar);

        buttonGenerateSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateSong();
            }
        });
    }

    private void generateSong() {
        // Show the progress bar
        progressBar.setVisibility(View.VISIBLE);

        // Search for the song with the user's name
        String fileName = editTextName.getText().toString() + ".mp3";
        StorageReference storageRef = FirebaseStorage.getInstance().getReference().child("songs/" + fileName);
        storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                // Song found, move to SongActivity
                progressBar.setVisibility(View.GONE);
                Intent intent = new Intent(SongNameActivity.this, SongActivity.class);
                intent.putExtra("songUri", uri.toString());
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Song not found, show a toast message
                progressBar.setVisibility(View.GONE);
                Toast.makeText(SongNameActivity.this, "We couldn't find your name, try again later.", Toast.LENGTH_SHORT).show();
            }
        });
    }

}