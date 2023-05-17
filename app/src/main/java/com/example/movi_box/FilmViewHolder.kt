package com.example.movi_box

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.movi_box.databinding.FilmItemBinding


//В конструктор класс передается layout, который мы создали(film_item.xml)
class FilmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val filmBinding = FilmItemBinding.bind(itemView)



    //В этом методе кладем данные из Film в наши View
    fun bind(film: Film) {


        //Устанавливаем заголовок
        filmBinding.title.text = film.title
        //Устанавливаем постер
        filmBinding.poster.setImageResource(film.poster)
        //Устанавливаем описание
        filmBinding.description.text = film.description
    }
}