package com.thellog.laravelapiandroid;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thellog.laravelapiandroid.Adapter.AdapterVideo;
import com.thellog.laravelapiandroid.Model.Item;
import com.thellog.laravelapiandroid.Model.Video_Model;
import com.thellog.laravelapiandroid.Service.APIService;
import com.thellog.laravelapiandroid.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Video extends AppCompatActivity {
    private RecyclerView recyclerViewVideo;
    private AdapterVideo adapterVideo;
    private List<Item> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        recyclerViewVideo = findViewById(R.id.reCycViewVideo);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerViewVideo.setLayoutManager(gridLayoutManager);
        // adapterVideo.setData();

        getData();


    }

    private void getData() {
        // Dataservice dataservice = APIService.getService();
        //Call<List<Quangcao>> callback = dataservice.GetDataBanner();

        DataService dataService = APIService.getService();
        Call<Video_Model> videoCall = dataService.getVideo();
        videoCall.enqueue(new Callback<Video_Model>() {
            @Override
            public void onResponse(Call<Video_Model> call, Response<Video_Model> response) {
                if (response.isSuccessful() && response.body().getItems() != null) {
                    if (!items.isEmpty()) {
                        items.clear();
                    }
                    items = response.body().getItems();
                    adapterVideo = new AdapterVideo(items, Video.this);
                    recyclerViewVideo.setAdapter(adapterVideo);
                    adapterVideo.notifyDataSetChanged();
                    return;
                } else {
                    Toast.makeText(Video.this, "No Result", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Video_Model> call, Throwable t) {

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_mani, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint("Search Latest Videos...");
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapterVideo.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterVideo.getFilter().filter(newText);
                return false;
            }
        });
        searchMenuItem.getIcon().setVisible(false, false);
        return true;
    }


}