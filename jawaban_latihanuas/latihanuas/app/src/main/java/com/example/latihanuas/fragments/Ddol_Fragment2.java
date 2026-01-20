package com.example.latihanuas.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
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
import com.example.latihanuas.model.Joke;
import com.example.latihanuas.services.ApiService;

import org.chromium.base.Callback;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ddol_2, container, false);
        txtSetup = view.findViewById(R.id.txtSetup);
        txtPunchLine = view.findViewById(R.id.txtPunchline);
        getAnotherBtn = view.findViewById(R.id.btnGetAnother);
        getRevealBtn = view.findViewById(R.id.btnReveal);
        requestQueue = Volley.newRequestQueue(getContext());

        fetchDdol();

        getAnotherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchDdol();
            }
        });

        getRevealBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtPunchLine.setVisibility(View.VISIBLE);
                getRevealBtn.setEnabled(false);
            }
        });

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

    public void postMethod(){

        String url = "https://official-joke-api.appspot.com/random_joke";

        JSONObject payload = new JSONObject();

        try {
            payload.put("title", "test");
            payload.put("body", "testttt");
            payload.put("id", 1);
        }
        catch (JSONException e){
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                url,
                payload,
                response -> {
                    Log.d("SUCCESS", response.toString());
                },
                error -> {
                    Log.d("ERROR", error.toString());
                }
        );

    }

//    public void fetchRetrofit(){
//
//        String url = "https://official-joke-api.appspot.com/";
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(url)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        ApiService apiService = retrofit.create(ApiService.class);
//
//        apiService.getJoke().enqueue(new Callback<Joke>() {
//            @Override
//            public void onResponse(Call<Joke> call, Response<Joke> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    txtSetup.setText(response.body().getSetup());
//                    txtPunchLine.setText(response.body().getPunchline());
//                } else {
//                    Log.d("ERROR", "GET Method Unsuccessful");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Joke> call, Throwable t) {
//                // Feel free to fill anything here
//            }
//        });
//
//    }

}