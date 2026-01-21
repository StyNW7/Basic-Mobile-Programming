package com.example.latihanuas.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.latihanuas.R;
import com.example.latihanuas.adapter.JokesAdapter;
import com.example.latihanuas.models.Joke;
import com.example.latihanuas.models.Stat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import kotlinx.coroutines.scheduling.Task;

public class JokesFragment extends Fragment {
    RecyclerView rvJokes;
    ArrayList<Joke> jokesList = new ArrayList<>();
    JokesAdapter adapter;

    public JokesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jokes, container, false);

        rvJokes = view.findViewById(R.id.rvJokes);
        rvJokes.setLayoutManager(new LinearLayoutManager(getContext()));

        fetchJokes();

        adapter = new JokesAdapter(jokesList);
        rvJokes.setAdapter(adapter);

        return view;
    }

    private void fetchJokes() {
        String url = "https://coba-api.risol.biz.id/get_jokes.php";
        jokesList.clear();
        //TODO: fetch pakai volley, simpan ke jokesList
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    //looping over length
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject data = jsonArray.getJSONObject(i);
                        //ambil id, type, setup, punchline, (stat)likes, views, shares

                        Joke joke = new Joke();
                        joke.id = data.getInt("id");
                        joke.type = data.getString("type");
                        joke.setup = data.getString("setup");
                        joke.punchline = data.getString("punchline");
                        joke.stats = new Stat();

                        JSONObject stats = data.getJSONObject("stats");
                        joke.stats.likes = stats.getInt("likes");
                        joke.stats.views = stats.getInt("views");
                        joke.stats.shares = stats.getInt("shares");

                        jokesList.add(joke);
                    }

                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsonObjectRequest);

    }

    public void fetchJokes2(){

        String url = "https://coba-api.risol.biz.id/get_jokes.php";
        jokesList.clear();
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            JSONArray jsonArray = response.getJSONArray("data");

                            for (int i = 0; i < jsonArray.length(); i++){

                                JSONObject data = jsonArray.getJSONObject(i);

                                Joke joke = new Joke();
                                joke.id = data.getInt("id");
                                joke.type = data.getString("type");
                                joke.setup = data.getString("setup");
                                joke.punchline = data.getString("punchline");

                                joke.stats = new Stat();
                                JSONObject stats = data.getJSONObject("stats");
                                joke.stats.shares = stats.getInt("shares");
                                joke.stats.likes = stats.getInt("likes");
                                joke.stats.views = stats.getInt("views");

                                jokesList.add(joke);

                            }

                            adapter.notifyDataSetChanged();

                        }

                        catch (JSONException e){
                            throw new RuntimeException(e);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "Fail to load jokes", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        requestQueue.add(request);

    }

}