package com.example.w4d1api.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.w4d1api.adapter.MovieAdapter
import com.example.w4d1api.model.Result
import com.example.w4d1api.presenter.Contract
import com.example.w4d1api.presenter.MovieDBPresenter
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.w4d1api.R


class MainActivity : AppCompatActivity(), Contract.ViewInterface, MovieAdapter.MovieAdapterDelegate {


    private lateinit var myPresenter: Contract.PresenterInterface
    lateinit var searchTitle : String

    lateinit var movieList: List<Result>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.w4d1api.R.layout.activity_main)
        setUpRecyclerView()





        myPresenter = MovieDBPresenter(this)
        my_button.setOnClickListener{
            searchTitle = my_edittext.text.toString()
            myPresenter.getMovieDBRepos(searchTitle)

            val mainLayout: ConstraintLayout = findViewById(R.id.main_layout)

            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(mainLayout.windowToken, 0)


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


        //val itemDecorator = DividerItemDecoration(this, LinearLayout.VERTICAL)
        //movie_recyclerview.addItemDecoration(itemDecorator)
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
