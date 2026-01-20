package com.example.latihanuas.services;

import com.example.latihanuas.model.Joke;

import retrofit2.Call;
import retrofit2.http.GET;

public class ApiService {

    @GET("random_joke")
    public Call<Joke> getJoke() {
        return null;
    }

}
