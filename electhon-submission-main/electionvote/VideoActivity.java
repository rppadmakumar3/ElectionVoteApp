package com.example.impactmakers.electionvote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class VideoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private YouTubeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        recyclerView = findViewById(R.id.recycler_view);
        adapter = new YouTubeAdapter();

        // Set layout manager for the RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Set adapter for the RecyclerView
        recyclerView.setAdapter(adapter);

        // Load YouTube video data and set it to the adapter
        List<YouTubeVideo> videos = loadYouTubeData();
        adapter.setVideos(videos);
    }

    private List<YouTubeVideo> loadYouTubeData() {
        List<YouTubeVideo> videos = new ArrayList<>();

        // Add sample YouTube videos
        videos.add(new YouTubeVideo("HW9hEzIF9DM", "Vote Awarenes", "https://img.youtube.com/vi/HW9hEzIF9DM/0.jpg"));
        videos.add(new YouTubeVideo("Pwmv7vAFv4g", "Voter Awareness - Motivational Song", "https://img.youtube.com/vi/Pwmv7vAFv4g/0.jpg"));
        videos.add(new YouTubeVideo("8c2YEQw9acs", "Video Msg on Voter Awareness", "https://img.youtube.com/vi/8c2YEQw9acs/0.jpg"));
        videos.add(new YouTubeVideo("NbH11R3f910", "Election awareness Short Film", "https://img.youtube.com/vi/NbH11R3f910/0.jpg"));
        videos.add(new YouTubeVideo("iqp3iIxHWNE", "Election Awareness Video", "https://img.youtube.com/vi/iqp3iIxHWNE/0.jpg"));

        return videos;
    }
}