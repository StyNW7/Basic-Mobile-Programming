package com.example.latihanuas.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.latihanuas.R;
import com.example.latihanuas.databinding.FragmentDance2Binding;
import com.example.latihanuas.services.PlayBabySharkService;

public class Dance2Fragment extends Fragment {

    FragmentDance2Binding binding;

    public Dance2Fragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentDance2Binding.inflate(inflater);

        binding.btnPlayStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent serviceIntent = new Intent(getActivity(), PlayBabySharkService.class);

                if (binding.btnPlayStop.getText().equals("Play")) {
                    binding.btnPlayStop.setText("Stop");
                    getActivity().startService(serviceIntent);
                } else {
                    binding.btnPlayStop.setText("Play");
                    getActivity().stopService(serviceIntent);
                }

            }
        });

        return binding.getRoot();

    }

}