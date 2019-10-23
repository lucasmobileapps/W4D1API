package com.example.w4d1api.view


import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.w4d1api.R
import com.example.w4d1api.model.Result
import kotlinx.android.synthetic.main.activity_main.*
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

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.movie_fragment, container, false)

        main_layout?.foreground?.colorFilter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragment_background_click.setOnClickListener{
            fragmentManager?.popBackStack()
        }

        val movie : Result? = arguments?.getParcelable("movie")
        val url = "https://image.tmdb.org/t/p/w342/${movie?.posterPath}"
        singlemovieName_textiew.text = movie?.title
        singlemovieType_textiew.text = movie?.overview
        Glide.with(this) //1
            .load(url)
            .placeholder(R.drawable.ic_movie)
            .error(R.drawable.ic_movie)
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(singlemovie_imageview)
    }

}
