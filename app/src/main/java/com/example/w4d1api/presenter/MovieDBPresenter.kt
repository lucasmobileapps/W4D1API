package com.example.w4d1api.presenter

import android.util.Log
import com.example.w4d1api.factory.MovieDBFactory
import com.example.w4d1api.model.MoviedbQuery
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDBPresenter (private val myView: Contract.ViewInterface): Contract.PresenterInterface{
    override fun getMovieDBRepos() {
        val gitService = MovieDBFactory()

        gitService.getGitRepos().enqueue(object : Callback<List<MoviedbQuery>> {
            override fun onFailure(call: Call<List<MoviedbQuery>>, t: Throwable) {
                Log.d("LOG_X", t.toString())
            }

            override fun onResponse(
                call: Call<List<MoviedbQuery>>,
                response: Response<List<MoviedbQuery>>
            ) {
                response.body()?.let {myRepos ->
                    myView.displayRepos(myRepos)
                }

                Log.d("LOG_X", "I have ${response.body()?.size}")
            }

        })

    }
}