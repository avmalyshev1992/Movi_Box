package com.example.movi_box.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movi_box.view.rv_adapters.FilmListRecyclerAdapter
import com.example.movi_box.view.MainActivity
import androidx.lifecycle.Observer
import com.example.movi_box.data.MainRepository
import com.example.movi_box.view.rv_adapters.TopSpacingItemDecoration
import com.example.movi_box.databinding.FragmentFirstBinding
import com.example.movi_box.domain.Film
import com.example.movi_box.utils.AnimationHelper
import com.example.movi_box.viewmodel.FirstFragmentViewModel
import java.util.*


class FirstFragment : Fragment() {
    private val viewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(FirstFragmentViewModel::class.java)
    }

    private var bindingFirst: FragmentFirstBinding? = null
    private val binding get() = bindingFirst!!


    private lateinit var filmsAdapter: FilmListRecyclerAdapter
    private var filmsDataBase = listOf<Film>()
        //Используем backing field
        set(value) {
            if (field == value) return
            field = value
            filmsAdapter.addItems(field)
        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingFirst = FragmentFirstBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AnimationHelper.performFragmentCircularRevealAnimation(binding.firstFragmentRoot, requireActivity(), 1)

        initSearchView()

        initRecycler()

        viewModel.filmsListLiveData.observe(viewLifecycleOwner, Observer<List<Film>> {
            filmsDataBase = it
        })
    }

    private fun initSearchView() {
        bindingFirst?.searchView?.setOnClickListener {
            bindingFirst?.searchView?.isIconified = false
        }

        bindingFirst?.searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }
            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty()) {
                    filmsAdapter.addItems(MainRepository.filmsDataBase)
                    return true
                }
                val result = MainRepository.filmsDataBase.filter {
                    //Чтобы все работало правильно, нужно и запрос, и имя фильма приводить к нижнему регистру
                    it.title.lowercase(Locale.getDefault()).contains(newText.lowercase(Locale.getDefault()))
                }
                filmsAdapter.addItems(result)
                return true
            }
        })
    }

    private fun initRecycler() {
        bindingFirst?.mainRecycler?.apply {
            filmsAdapter =
                FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnItemClickListener {
                    override fun click(film: Film) {
                        (requireActivity() as MainActivity).launchDetailsFragment(film)
                    }
                })
            adapter = filmsAdapter
            layoutManager = LinearLayoutManager(requireContext())
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)
        }
    }

    override fun onDestroyView() {
        bindingFirst = null
        super.onDestroyView()
    }
}