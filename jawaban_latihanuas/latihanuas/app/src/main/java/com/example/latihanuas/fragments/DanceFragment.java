package com.example.latihanuas.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.latihanuas.R;
import com.example.latihanuas.databinding.FragmentDanceBinding;
import com.example.latihanuas.services.PlayBabySharkService;


public class DanceFragment extends Fragment {

    FragmentDanceBinding binding;

    public DanceFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDanceBinding.inflate(inflater);
        binding.btnPlayStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent serviceIntent = new Intent(getActivity(), PlayBabySharkService.class);

                if (binding.btnPlayStop.getText().equals("Play")){
                    binding.btnPlayStop.setText("Stop");
                    getActivity().startService(serviceIntent);
                }

                else {
                    binding.btnPlayStop.setText("Play");
                    getActivity().stopService(serviceIntent);
                }

            }
        });

        return binding.getRoot();

    }

}