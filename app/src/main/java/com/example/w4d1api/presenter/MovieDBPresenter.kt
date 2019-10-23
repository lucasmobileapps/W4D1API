package com.example.w4d1api.presenter

import android.util.Log
import com.example.w4d1api.factory.MovieDBFactory
import com.example.w4d1api.model.MoviedbQuery
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDBPresenter (private val myView: Contract.ViewInterface): Contract.PresenterInterface{
    override fun getMovieDBRepos(title: String) {
        val movieDBService = MovieDBFactory()

        movieDBService.getMovieDBRepos(title).enqueue(object : Callback<MoviedbQuery> {

            override fun onFailure(call: Call<MoviedbQuery>, t: Throwable) {
                Log.d("LOG_X", t.toString())
            }

            override fun onResponse(
                call: Call<MoviedbQuery>,
                response: Response<MoviedbQuery>
            ) {
                response.body()?.let {myRepos ->
                    var movieRepos = myRepos.results?.map {eachItem->
                        eachItem!!
                    }?: listOf()

                    myView.displayRepos(movieRepos)
                }

                //Log.d("LOG_X", "I have ${response.body()?.size}")
            }

        })

    }
}