package com.example.w4d1api.factory

import com.example.w4d1api.model.MoviedbQuery
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieDBFactory {
    val BASE_URL = "https://api.themoviedb.org/3/"
    val API_KEY = "90bacac1e9b6f39c15b8ed77c50dd9be"

    private lateinit var movieService: MovieDBService

    init {
        movieService = createMovieService(getRetrofitInstance())
    }

    private fun createMovieService(retrofitInstance: Retrofit): MovieDBService {
        return retrofitInstance.create(MovieDBService::class.java)
    }

    private fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getMovieDBRepos(title: String): Call<MoviedbQuery> {
        return movieService.getMyRepositories(API_KEY, title)
    }
}