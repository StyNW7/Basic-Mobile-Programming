package com.example.musicinfoplayer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
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

        loadMusicVolley();

        return v;

    }

    private void loadMusic(){

        RetrofitAPIService api = RetrofitAPIClient.getClient().create(RetrofitAPIService.class);

        api.searchMusic("eminem").enqueue(new Callback<DeezerResponse>() {
            @Override
            public void onResponse(Call<DeezerResponse> call, Response<DeezerResponse> response) {
                if (response.isSuccessful() && response.body() != null){
                    list.clear();
                    list.addAll(response.body().getData());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<DeezerResponse> call, Throwable t) {

            }
        });

    }

    private void loadMusicVolley() {

        String url = "https://api.deezer.com/search?q=eminem";

        RequestQueue queue = Volley.newRequestQueue(requireContext());

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    try {
                        JSONArray dataArray = response.getJSONArray("data");

                        list.clear();

                        for (int i = 0; i < dataArray.length(); i++) {

                            JSONObject trackObj = dataArray.getJSONObject(i);

                            // Track
                            Track track = new Track();
                            track.setTitle(trackObj.getString("title"));
                            track.setPreview(trackObj.getString("preview"));

                            // Artist (nested object)

                            JSONObject artistObj = trackObj.getJSONObject("artist");
                            Artist artist = new Artist();

                            Field nameField = Artist.class.getDeclaredField("name");
                            nameField.setAccessible(true);
                            nameField.set(artist, artistObj.getString("name"));

                            track.setArtist(artist);

                            list.add(track);
                        }

                        adapter.notifyDataSetChanged();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                },
                error -> error.printStackTrace()
        );

        queue.add(request);
    }

}
