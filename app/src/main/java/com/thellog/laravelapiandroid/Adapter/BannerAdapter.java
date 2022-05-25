package com.thellog.laravelapiandroid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thellog.laravelapiandroid.ImgBanner;
import com.thellog.laravelapiandroid.R;

import java.util.List;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.BannerHolder>{
     private Context acontext;
    private List<ImgBanner> imgBannerList;

    public BannerAdapter(Context acontext) {
        this.acontext = acontext;
    }

    public BannerAdapter(List<ImgBanner> imgBannerList) {
        this.imgBannerList = imgBannerList;
    }

    @NonNull
    @Override
    public BannerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_imgebanner , parent , false);

        return new BannerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BannerHolder holder, int position) {
        ImgBanner imgBanner = imgBannerList.get(position);
        if (imgBanner==null)
        {
            return;
        }
        holder.imgBanner.setImageResource(imgBanner.getResoureID());

    }

    @Override
    public int getItemCount() {
        if (imgBannerList!=null)
        {
            return imgBannerList.size();
        }
        return 0;
    }

    public class BannerHolder extends RecyclerView.ViewHolder{

        private ImageView imgBanner;

        public BannerHolder(@NonNull View itemView) {
            super(itemView);

            imgBanner = itemView.findViewById(R.id.img_Banner);
        }
    }

}
