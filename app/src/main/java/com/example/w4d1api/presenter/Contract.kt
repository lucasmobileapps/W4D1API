package com.example.w4d1api.presenter

import com.example.w4d1api.model.MoviedbQuery

interface Contract {
    interface PresenterInterface{
        fun getMovieDBRepos()
    }
    interface ViewInterface{
        fun displayRepos(repository: List<MoviedbQuery>)
        fun onFetchError()
    }
}