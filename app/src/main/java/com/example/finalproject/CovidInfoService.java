package com.example.finalproject;

import com.example.finalproject.CovidHistory;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface CovidInfoService {
    @GET("history")
    @Headers({
            "X-RapidAPI-Host: covid-193.p.rapidapi.com",
            "X-RapidAPI-Key: a86df08bacmshc5a4fb6f13649cep10c35ejsnbc1432c5b419"
    })

    Call<CovidHistory> covidHistory(@Query("country") String country, @Query("day") String day); // Call == request A
}
