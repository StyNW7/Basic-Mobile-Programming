package com.example.musicinfoplayer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

import retrofit2.*;
public class FragmentMusic extends Fragment {

    RecyclerView recycler;
    MusicAdapter adapter;
    List<Track> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup c, Bundle b) {
        View v = i.inflate(R.layout.fragment_music, c, false);

        recycler = v.findViewById(R.id.recyclerMusic);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MusicAdapter(getContext(), list);
        recycler.setAdapter(adapter);

        loadMusic();

        return v;
    }

    private void loadMusic() {
        RetrofitAPIService api =
                RetrofitAPIClient.getClient().create(RetrofitAPIService.class);

        api.searchMusic("eminem").enqueue(new Callback<DeezerResponse>() {
            public void onResponse(Call<DeezerResponse> c, Response<DeezerResponse> r) {
                if (r.isSuccessful() && r.body() != null) {
                    list.clear();
                    list.addAll(r.body().getData());
                    adapter.notifyDataSetChanged();
                }
            }
            public void onFailure(Call<DeezerResponse> c, Throwable t) {}
        });
    }
}
