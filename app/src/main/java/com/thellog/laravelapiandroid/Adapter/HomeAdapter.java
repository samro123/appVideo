package com.thellog.laravelapiandroid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thellog.laravelapiandroid.Model.Home_Model;
import com.thellog.laravelapiandroid.R;

import java.util.List;

public class HomeAdapter extends  RecyclerView.Adapter<HomeAdapter.HomeViewHolder>{
    private Context context ;
    private List<Home_Model> home_models;
    private RecyclerviewOnclickListener listener;

    public HomeAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Home_Model> list , RecyclerviewOnclickListener listeners ){
        this.home_models = list;
        this.listener = listeners;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home ,parent , false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        Home_Model home_model = home_models.get(position);
       if (home_model == null){
           return;
       }
       holder.imgHome.setImageResource(home_model.getImgeHome());
       holder.nameHome.setText(home_model.getNameHome());
    }

    @Override
    public int getItemCount() {
        if( home_models != null){
            return home_models.size();
        }
        return 0;
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imgHome;
        private TextView nameHome;
        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);

            imgHome = itemView.findViewById(R.id.imgHome);
            nameHome = itemView.findViewById(R.id.txtHome);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view , getAdapterPosition());

        }
    }

    public interface RecyclerviewOnclickListener{
        void onClick(View v , int position);
    }
}
