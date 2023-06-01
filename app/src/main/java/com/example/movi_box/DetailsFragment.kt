package com.example.movi_box

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.movi_box.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        detailBinding = null
    }

    private fun setFilmsDetails() {
        val film = arguments?.get("film") as Film

        detailBinding?.detailsToolbar?.title = film.title
        detailBinding?.detailsPoster?.setImageResource(film.poster)
        detailBinding?.detailsDescription?.text = film.description
    }
}