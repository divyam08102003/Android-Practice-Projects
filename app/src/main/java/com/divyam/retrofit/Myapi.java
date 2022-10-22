package com.divyam.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Myapi {

    @GET("todos")
    Call<List<Model>> getmodels();
}
