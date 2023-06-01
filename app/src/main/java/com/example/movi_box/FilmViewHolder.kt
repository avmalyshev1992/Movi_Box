package com.example.movi_box

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.movi_box.databinding.FilmItemBinding


class FilmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val filmBinding = FilmItemBinding.bind(itemView)


    fun bind(film: Film) {


        filmBinding.title.text = film.title
        filmBinding.poster.setImageResource(film.poster)
        filmBinding.description.text = film.description
    }
}