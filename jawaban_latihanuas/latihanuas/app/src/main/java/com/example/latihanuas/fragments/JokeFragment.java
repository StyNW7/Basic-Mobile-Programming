package com.example.latihanuas.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.latihanuas.R;
import com.example.latihanuas.adapter.JokeAdapter;
import com.example.latihanuas.model.Joke;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JokeFragment extends Fragment {

    private RecyclerView recyclerView;
    private JokeAdapter adapter;
    private ArrayList<Joke> jokeList;

    private static final String URL =
            "https://official-joke-api.appspot.com/random_ten";

    public JokeFragment () {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_joke, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        jokeList = new ArrayList<>();
        adapter = new JokeAdapter(getContext(), jokeList);
        recyclerView.setAdapter(adapter);

        fetchJokes();

        return view;

    }

    private void fetchJokes() {

        RequestQueue queue = Volley.newRequestQueue(requireContext());

        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                URL,
                null,
                response -> {

                    try {
                        for (int i = 0; i < response.length(); i++) {

                            JSONObject obj = response.getJSONObject(i);

                            String type = obj.getString("type");
                            String setup = obj.getString("setup");
                            String punchline = obj.getString("punchline");

                            Joke joke = new Joke(type, setup, punchline);
                            jokeList.add(joke);
                        }

                        adapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    Toast.makeText(
                            getContext(),
                            "Failed to load jokes",
                            Toast.LENGTH_SHORT
                    ).show();
                }
        );

        queue.add(request);

    }


    private void fetchJokes2(){

        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());

        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET, URL, null,
                response -> {
                    try {

                        for (int i = 0; i < response.length(); i++){

                            JSONObject obj = response.getJSONObject(i);
                            Joke joke = new Joke(
                                    obj.getString("type"),
                                    obj.getString("setup"),
                                    obj.getString("punchline")
                            );

                            jokeList.add(joke);

                        }

                        adapter.notifyDataSetChanged();

                    }
                    catch (JSONException e){
                        e.printStackTrace();
                    }
                },
                error -> {
                    Toast.makeText(getContext(), "Fail to fetch jokes", Toast.LENGTH_SHORT).show();
                }
        );

        requestQueue.add(request);

    }


}