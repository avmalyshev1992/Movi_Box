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

    private lateinit var filmsAdapter: FilmListRecyclerAdapter
    private lateinit var binding: FragmentFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AnimationHelper.performFragmentCircularRevealAnimation(binding.firstFragmentRoot, requireActivity(), 1)

        initSearchView()

        initRecycler()

        //Кладем нашу БД в RV
        viewModel.filmsListLiveData.observe(viewLifecycleOwner) {
            filmsAdapter.addItems(it)
        }
    }

    private fun initSearchView() {
        binding.searchView.setOnClickListener {
            binding.searchView.isIconified = false
        }


        //Подключаем слушателя изменений введенного текста в поиска
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            //Этот метод отрабатывает при нажатии кнопки "поиск" на софт клавиатуре
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }
            //Этот метод отрабатывает на каждое изменения текста
            override fun onQueryTextChange(newText: String): Boolean {
                //Если ввод пуст то вставляем в адаптер всю БД
                if (newText.isEmpty()) {
                    viewModel.filmsListLiveData.observe(viewLifecycleOwner) {
                        filmsAdapter.addItems(it)
                    }
                    return true
                }
                //Фильтруем список на поиск подходящих сочетаний
                viewModel.filmsListLiveData.observe(viewLifecycleOwner) {
                    filmsAdapter.addItems(it.filter { it.title.lowercase(Locale.getDefault()).contains(newText.lowercase(Locale.getDefault())) })
                }
                return true
            }
        })
    }

    private fun initRecycler() {
        //находим наш RV
        binding.mainRecycler.apply {
            filmsAdapter =
                FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnItemClickListener {
                    override fun click(film: Film) {
                        (requireActivity() as MainActivity).launchDetailsFragment(film)
                    }
                })
            //Присваиваем адаптер
            adapter = filmsAdapter
            //Присвои layoutmanager
            layoutManager = LinearLayoutManager(requireContext())
            //Применяем декоратор для отступов
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)
        }
    }
}