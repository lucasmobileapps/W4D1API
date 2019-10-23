package com.example.w4d1api.presenter

import com.example.w4d1api.model.MoviedbQuery
import com.example.w4d1api.model.Result

interface Contract {
    interface PresenterInterface{
        fun getMovieDBRepos(title: String)
    }
    interface ViewInterface{
        fun displayRepos(repository: List<Result>)
        fun onFetchError()
    }
}