package com.teknorial.retrofit2tutorial.rest;

import com.teknorial.retrofit2tutorial.models.Models;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Teknorial on 06/02/2016.
 */
public interface RestApi {

    @GET("contohjson")
    Call<Models> getDataAdmin();
}
