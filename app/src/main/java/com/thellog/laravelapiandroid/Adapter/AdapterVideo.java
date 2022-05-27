package com.thellog.laravelapiandroid.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.thellog.laravelapiandroid.DetailVideo;
import com.thellog.laravelapiandroid.Model.Item;
import com.thellog.laravelapiandroid.Model.Video_Model;
import com.thellog.laravelapiandroid.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;

public class AdapterVideo extends  RecyclerView.Adapter<AdapterVideo.VideoViewHolder> implements Filterable {
    private List<Item> items;
    private List<Item> mitems;
    private Context mContext;

    public AdapterVideo(List<Item> items, Context mContext) {
        this.items = items;
        this.mitems = items;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video , parent , false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        // Video_Model video_model = video_models.get(position);
        final  Item item = items.get(position);
        if (item == null){
            return;
        }
        Picasso.with(mContext).load(item.getSnippet().getThumbnails().getDefault().getUrl()).into(holder.imgVideo);
        holder.nameVideo.setText(item.getSnippet().getPublishedAt());
        holder.nameVideo1.setText(item.getSnippet().getTitle());


        holder.imgVideo.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               Intent intent = new Intent(mContext , DetailVideo.class);
               intent.putExtra("namevideo", item.getSnippet().getTitle());
               intent.putExtra("ngayvideo", item.getSnippet().getPublishedAt());
               intent.putExtra("idvideo" , item.getId().getVideoId());
               intent.putExtra("devideo", item.getSnippet().getDescription());
               intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if( items != null){
            return items.size();
        }
        return 0;
    }



    public  class  VideoViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgVideo;
        private TextView nameVideo;
        private TextView nameVideo1;
      //  private RelativeLayout layoutItem;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            // layoutItem = itemView.findViewById(R.id.liLayout1);
            imgVideo = itemView.findViewById(R.id.img_Video);
            nameVideo = itemView.findViewById(R.id.tvNameVideo);
            nameVideo1 = itemView.findViewById(R.id.tvNameVideo1);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strSearch = charSequence.toString();
                if(strSearch.isEmpty())
                {
                    items = mitems;
                }
                else {
                    List<Item> list = new ArrayList<>();
                    for (Item item : mitems){
                        if(item.getSnippet().getTitle().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(item);
                        }
                    }
                    items = list;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = items;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                items = (List<Item>)filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
