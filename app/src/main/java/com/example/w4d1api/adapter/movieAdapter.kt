package com.example.w4d1api.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.w4d1api.R
import com.example.w4d1api.model.Result

class MovieAdapter(private val movieadapterDelegate: MovieAdapterDelegate)

    : ListAdapter<Result, MovieAdapter.MovieViewHolder>(MovieDiffUtil()){
    interface  MovieAdapterDelegate{
        fun movieSelect(movie: Result)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item_view_layout, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.apply {
            movieName.text = getItem(position).title
            movieType.text = getItem(position).releaseDate
            viewGroup.setOnClickListener{
                movieadapterDelegate.movieSelect(getItem(position))
            }
            viewGroup.context?.let {
                val url = "https://image.tmdb.org/t/p/w185/${getItem(position).posterPath}"
                Glide.with(it) //1
                    .load(url)
                    .placeholder(R.drawable.ic_movie)
                    .error(R.drawable.ic_movie)
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(movieImageview)
                Log.d("LOG_X", url)
            }

        }
    }
    class MovieViewHolder(view: View): RecyclerView.ViewHolder(view){
        val movieName: TextView = view.findViewById(R.id.movieName_textiew)
        val movieType: TextView = view.findViewById(R.id.movieType_textiew)
        val movieImageview: ImageView = view.findViewById(R.id.movie_imageview)
        val viewGroup: ConstraintLayout = view.findViewById(R.id.movie_viewlayout)

    }
}

class MovieDiffUtil : DiffUtil.ItemCallback<Result>(){
    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem.title == newItem.title && oldItem.popularity == newItem.popularity
    }


}

