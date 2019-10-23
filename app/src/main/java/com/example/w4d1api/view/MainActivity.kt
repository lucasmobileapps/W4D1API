package com.example.w4d1api.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.w4d1api.R
import com.example.w4d1api.adapter.MovieAdapter
import com.example.w4d1api.factory.MovieDBFactory
import com.example.w4d1api.factory.MovieDBService
import com.example.w4d1api.model.MoviedbQuery
import com.example.w4d1api.model.Result
import com.example.w4d1api.presenter.Contract
import com.example.w4d1api.presenter.MovieDBPresenter
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity(), Contract.ViewInterface, MovieAdapter.MovieAdapterDelegate {


    private lateinit var myPresenter: Contract.PresenterInterface
    lateinit var searchTitle : String

    lateinit var movieList: List<Result>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpRecyclerView()



        myPresenter = MovieDBPresenter(this)
        my_button.setOnClickListener{
            searchTitle = my_edittext.text.toString()
            myPresenter.getMovieDBRepos(searchTitle)

            Log.d("LOG_X", "")
        }
    }

    override fun movieSelect(movie: Result) {
        var fragmentMovie = FragmentMovie()
        fragmentMovie.arguments = Bundle().apply{
            putParcelable("movie",movie)
        }
        supportFragmentManager.beginTransaction()
            .add(R.id.movie_framelayout, fragmentMovie)
            .addToBackStack(null)
            .commit()
    }

    fun setUpRecyclerView(){
        movie_recyclerview.adapter = MovieAdapter(this)
        movie_recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)


        val itemDecorator = DividerItemDecoration(this, LinearLayout.VERTICAL)
        movie_recyclerview.addItemDecoration(itemDecorator)
    }


    override fun displayRepos(repository: List<Result>) {

        movieList = repository
        /*
        val stringBuilder = StringBuilder()

        repository.forEach{repo ->
            stringBuilder.append("${repo.title}\n")
        }
        my_textview.text = stringBuilder.toString()

         */
        (movie_recyclerview.adapter as MovieAdapter).submitList(movieList)

    }

    override fun onFetchError() {
        Toast.makeText(this, "Error Fetching", Toast.LENGTH_SHORT).show()
    }
}
