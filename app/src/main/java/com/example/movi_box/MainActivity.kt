package com.example.movi_box

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movi_box.databinding.ActivityMainBinding
import com.example.movi_box.databinding.FilmItemBinding



class MainActivity : AppCompatActivity() {
    private lateinit var filmsAdapter: FilmListRecyclerAdapter


    val filmsDataBase = listOf(
        Film(
            "alien covenant Redemption",
            R.drawable.alien_covenant,
            "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency."
        ),
        Film(
            "life",
            R.drawable.life,
            "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son."
        ),
        Film(
            "oblivion",
            R.drawable.oblivion,
            "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice."
        ),
        Film(
            "one punch man",
            R.drawable.one_punch_man,
            "The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption."
        ),
        Film(
            "rayon9",
            R.drawable.rayon9,
            "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O."
        ),
        Film(
            "star trek",
            R.drawable.star_trek,
            "The real life of one of America's foremost founding fathers and first Secretary of the Treasury, Alexander Hamilton. Captured live on Broadway from the Richard Rodgers Theater with the original Broadway cast."
        ),
        Film(
            "stragi",
            R.drawable.stragi,
            "Greed and class discrimination threaten the newly formed symbiotic relationship between the wealthy Park family and the destitute Kim clan."
        ),
        Film(
            "transformers",
            R.drawable.transformers,
            "A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival."
        ),
        Film(
            "valerian",
            R.drawable.valerian,
            "In Gotham City, mentally troubled comedian Arthur Fleck is disregarded and mistreated by society. He then embarks on a downward spiral of revolution and bloody crime. This path brings him face-to-face with his alter-ego: the Joker."
        ),
        Film(
            "iron",
            R.drawable.iron,
            "April 6th, 1917. As a regiment assembles to wage war deep in enemy territory, two soldiers are assigned to race against time and deliver a message that will stop 1,600 men from walking straight into a deadly trap."
        )

    )
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        //   initNavigation()


        //находим наш RV
        binding?.mainRecycler.apply {
            //Инициализируем наш адаптер в конструктор передаем анонимно инициализированный интерфейс,
            //оставим его пока пустым, он нам понадобится во второй части задания
            filmsAdapter =
                FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnItemClickListener {
                    override fun click(film: Film) {
                        //Создаем бандл и кладем туда объект с данными фильма
                        val bundle = Bundle()
                        //Первым параметром указывается ключ, по которому потом будем искать, вторым сам
                        //передаваемый объект
                        bundle.putParcelable("film", film)
                        //Запускаем наше активити
                        val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                        //Прикрепляем бандл к интенту
                        intent.putExtras(bundle)
                        //Запускаем активити через интент
                        startActivity(intent)
                    }
                })
            //Присваиваем адаптер
            this!!.adapter = filmsAdapter
            //Присвои layoutmanager
            this!!.layoutManager = LinearLayoutManager(this@MainActivity)
            //Применяем декоратор для отступов
            val decorator = TopSpacingItemDecoration(8)
            this!!.addItemDecoration(decorator)
        }
//Кладем нашу БД в RV
        filmsAdapter.addItems(filmsDataBase)

    }
}

/*
             fun initNavigation() {
                 binding.topAppBar.setOnMenuItemClickListener {
                     when (it.itemId) {
                         R.id.settings -> {
                             Toast.makeText(this, "Настройки", Toast.LENGTH_SHORT).show()
                             true
                         }
                         else -> false
                     }
                 }


                 binding?.bottomNavigation?.setOnNavigationItemSelectedListener {
                     when (it.itemId) {
                         R.id.favorites -> {
                             Toast.makeText(this, "Избранное", Toast.LENGTH_SHORT).show()
                             true
                         }
                         R.id.watch_later -> {
                             Toast.makeText(this, "Посмотреть похже", Toast.LENGTH_SHORT).show()
                             true
                         }
                         R.id.selections -> {
                             Toast.makeText(this, "Подборки", Toast.LENGTH_SHORT).show()
                             true
                         }
                         else -> false
                     }
                 }


            }
*/



