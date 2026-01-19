package com.example.latihanuas.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.latihanuas.R;

import org.json.JSONException;

public class Ddol_Fragment2 extends Fragment {

    private TextView txtSetup, txtPunchLine;
    private Button getAnotherBtn, getRevealBtn;
    private RequestQueue requestQueue;

    public Ddol_Fragment2 () {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ddol_2, container, false);



        return view;
    }

    public void fetchDdol(){

        String url = "https://official-joke-api.appspot.com/random_joke";

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET, url, null,
                response -> {

                    try {
                        String type = response.getString("type");
                        int id = response.getInt("id");
                        String setup = response.getString("setup");
                        String punchLine = response.getString("punchline");
                        txtSetup.setText(setup);
                        txtPunchLine.setText(punchLine);
                        txtPunchLine.setVisibility(View.GONE);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                },
                error -> {
                    Toast.makeText(getContext(), "Failed to load Joke", Toast.LENGTH_SHORT).show();
                }
        );

        requestQueue.add(request);
        getRevealBtn.setEnabled(true);

    }

}