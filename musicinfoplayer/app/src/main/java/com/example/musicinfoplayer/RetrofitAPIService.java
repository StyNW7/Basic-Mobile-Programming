package com.example.musicinfoplayer;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitAPIService {

    @GET("search")
    Call<DeezerResponse> searchMusic(@Query("q") String query);
}
