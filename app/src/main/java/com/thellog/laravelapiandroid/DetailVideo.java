package com.thellog.laravelapiandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.thellog.laravelapiandroid.Model.Item;

public class DetailVideo extends AppCompatActivity {
    private TextView txtName , txtNgay , txtDes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_video);

        txtName = findViewById(R.id.tv_NameVideoDetail);
        txtNgay = findViewById(R.id.tv_Ngay);
        txtDes = findViewById(R.id.tv_description);

        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId =  getIntent().getStringExtra("idvideo");
                youTubePlayer.loadVideo(videoId, 0);
            }
        });

        txtName.setText(getIntent().getStringExtra("namevideo"));
        txtNgay.setText(getIntent().getStringExtra("ngayvideo"));
        txtDes.setText(getIntent().getStringExtra("devideo"));

    }
}