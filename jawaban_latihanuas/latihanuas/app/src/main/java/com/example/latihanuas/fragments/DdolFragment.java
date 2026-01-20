package com.example.latihanuas.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Request;
    import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.example.latihanuas.R;

import org.json.JSONException;

public class DdolFragment extends Fragment {
    private TextView txtSetup, txtPunchline;
    private Button btnReveal, btnGetAnother;
    private RequestQueue requestQueue;

    public DdolFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ddol, container, false);

        txtSetup = view.findViewById(R.id.txtSetup);
        txtPunchline = view.findViewById(R.id.txtPunchline);
        btnReveal = view.findViewById(R.id.btnReveal);
        btnGetAnother = view.findViewById(R.id.btnGetAnother);
        requestQueue = Volley.newRequestQueue(this.getContext());

        fetchDdol();

        btnReveal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnReveal.setEnabled(false);
                txtPunchline.setVisibility(View.VISIBLE);
            }
        });

        btnGetAnother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchDdol();
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

                        String setup = response.getString("setup");
                        String punchline = response.getString("punchline");
                        txtSetup.setText(setup);
                        txtPunchline.setText(punchline);
                        txtPunchline.setVisibility(View.GONE);

                    }
                    catch (JSONException e){
                        e.printStackTrace();
                    }
                },
                error -> {
                    Toast.makeText(getContext(), "Fail to load a joke", Toast.LENGTH_SHORT).show();
                }
        );

        requestQueue.add(request);
        btnReveal.setEnabled(true);

    }

}