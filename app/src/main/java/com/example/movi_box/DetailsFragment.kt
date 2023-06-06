package com.example.movi_box

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.movi_box.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {
    private lateinit var film: Film
    private var detailBinding: FragmentDetailsBinding? = null
    private val binding get() = detailBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailBinding = FragmentDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFilmsDetails()

        detailBinding?.detailsFabFavorites?.setOnClickListener {
            if (!film.isInFavorites) {
                detailBinding?.detailsFabFavorites?.setImageResource(R.drawable.round_star_24)
                film.isInFavorites = true
            } else {
                detailBinding?.detailsFabFavorites?.setImageResource(R.drawable.round_star_border_24)
                film.isInFavorites = false
            }
        }

        detailBinding?.detailsFab?.setOnClickListener {

            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(
                Intent.EXTRA_TEXT,
                "Check out this film: ${film.title} \n\n ${film.description}"
            )
            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent, "Share To:"))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        detailBinding = null
    }

    private fun setFilmsDetails() {
        //Получаем наш фильм из переданного бандла
        film = arguments?.get("film") as Film

        //Устанавливаем заголовок
        detailBinding?.detailsToolbar?.title = film.title
        //Устанавливаем картинку
        detailBinding?.detailsPoster?.setImageResource(film.poster)
        //Устанавливаем описание
        detailBinding?.detailsDescription?.text = film.description

        detailBinding?.detailsFabFavorites?.setImageResource(
            if (film.isInFavorites) R.drawable.round_star_border_24
            else R.drawable.round_star_24
        )
    }
}