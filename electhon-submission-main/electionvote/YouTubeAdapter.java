package com.example.impactmakers.electionvote;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.util.List;

public class YouTubeAdapter extends RecyclerView.Adapter<YouTubeAdapter.VideoViewHolder> {

    private List<YouTubeVideo> videos;

    public void setVideos(List<YouTubeVideo> videos) {
        this.videos = videos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_youtube_video, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        YouTubeVideo video = videos.get(position);
        holder.bind(video);
    }

    @Override
    public int getItemCount() {
        return videos != null ? videos.size() : 0;
    }

    static class VideoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView thumbnail;
        private final TextView title;
        private String videoId;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.thumbnail_image_view);
            title = itemView.findViewById(R.id.title_text_view);
            itemView.setOnClickListener(this);
        }

        public void bind(YouTubeVideo video) {
            videoId = video.getVideoId();
            String thumbnailUrl = video.getThumbnailUrl();
            String titleText = video.getTitle();
            Picasso.get().load(thumbnailUrl).into(thumbnail);
            title.setText(titleText);
        }

        @Override
        public void onClick(View v) {
            // Play the video in the YouTube app or website
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=" + videoId));
            v.getContext().startActivity(intent);
        }
    }
}