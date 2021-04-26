package com.example.myassignment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myassignment.databinding.ItemListBinding

import com.example.myassignment.model.ListMovie

/**
 * Created by Shabbir on 26/4/21$.
 */
class ListMovieAdapter(
    private var movies: MutableList<ListMovie>
) : RecyclerView.Adapter<ListMovieAdapter.MovieViewHolder>() {

    fun appendMovies(movies: List<ListMovie>) {
        this.movies.addAll(movies)
        notifyItemRangeInserted(
            this.movies.size,
            movies.size - 1)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ItemListBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = movies[position]
        holder.itemView.setOnClickListener {
           // onClickListener.onClick(item)
        }
        holder.bind(item)
    }

    class MovieViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: ListMovie) {
            binding.movie = movie
        }
    }





}