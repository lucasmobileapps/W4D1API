package com.example.w4d1api.factory

import com.example.w4d1api.model.MoviedbQuery
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//API Key =  90bacac1e9b6f39c15b8ed77c50dd9be
// https://api.themoviedb.org/3/search/movie?api_key=90bacac1e9b6f39c15b8ed77c50dd9be&query=disney
// API READ Access token =   eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5MGJhY2FjMWU5YjZmMzljMTViOGVkNzdjNTBkZDliZSIsInN1YiI6IjVkYWYxMDFkYjViYzIxMDAxNDcyNjY2YSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.GFzCiivYQjUdP5Z5AALGDV4gg19WL6A4NK8_cyCPofg

interface MovieDBService{
    //search/movie?api_key=90bacac1e9b6f39c15b8ed77c50dd9be&query=disney
    @GET("search/movie")
    fun getMyRepositories(@Query("api_key") apiKey: String,
                          @Query("query") title: String) : Call<MoviedbQuery>
}