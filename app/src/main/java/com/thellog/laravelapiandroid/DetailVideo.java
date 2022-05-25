package com.thellog.laravelapiandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.thellog.laravelapiandroid.Model.Item;

public class DetailVideo extends AppCompatActivity {
    private TextView txtIDVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_video);

        Bundle bundle = getIntent().getExtras();
        if (bundle==null){
            return;
        }
        Item item = (Item) bundle.get("object_video");
         txtIDVideo = findViewById(R.id.txt_IDVideo);
         txtIDVideo.setText(item.getId().getVideoId());
    }
}