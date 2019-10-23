package com.example.w4d1api.view


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.w4d1api.R
import com.example.w4d1api.model.Result
import kotlinx.android.synthetic.main.movie_fragment.*
import kotlinx.android.synthetic.main.movie_item_view_layout.*

class FragmentMovie : Fragment() {

    private lateinit var fragmentMovieListener: FragmentMovieListener

    interface FragmentMovieListener {
        fun fragmentMovieSelected()
    }

    fun setFragmentMovieListener(listener: FragmentMovieListener) {
        fragmentMovieListener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.movie_fragment, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var movie : Result? = arguments?.getParcelable("movie")
        singlemovieName_textiew.text = movie?.title
        singlemovieType_textiew.text = movie?.overview

    }

}