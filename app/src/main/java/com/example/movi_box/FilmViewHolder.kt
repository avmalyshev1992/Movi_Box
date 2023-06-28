package com.example.movi_box

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movi_box.databinding.FilmItemBinding


//В конструктор класс передается layout, который мы создали(film_item.xml)
class FilmViewHolder(val binding: FilmItemBinding) : RecyclerView.ViewHolder(binding.root) {


    //Привязываем view из layout к переменным
    private val title = binding.title
    private val poster = binding.poster
    private val description = binding.description
    //Вот здесь мы находим в верстке наш прогресс бар для рейтинга
    private val ratingDonut = binding.ratingDonut


    //В этом методе кладем данные из Film в наши View
    fun bind(film: Film) {


        //Устанавливаем заголовок
        binding.title.text = film.title
        //Устанавливаем постер
        //Указываем контейнер, в котором будет "жить" наша картинка
        Glide.with(itemView)
            //Загружаем сам ресурс
            .load(film.poster)
            //Центруем изображение
            .centerCrop()
            //Указываем ImageView, куда будем загружать изображение
            .into(binding.poster)
        //Устанавливаем описание
        binding.description.text = film.description
        //Устанавливаем рейтинг
        binding.ratingDonut.setProgress((film.rating * 10).toInt())
    }
}