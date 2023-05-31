package com.example.movi_box

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movi_box.databinding.FragmentFirstBinding


class FirstFragment : Fragment() {

    private var bindingFrag: FragmentFirstBinding? = null
    private val binding1 get() = bindingFrag!!


    private lateinit var filmsAdapter: FilmListRecyclerAdapter

    val filmsDataBase = listOf(
        Film(
            "Alien covenant Redemption",
            R.drawable.alien_covenant,
            "Во время обследования удаленной планеты, расположенной на другой стороне галактики, экипаж колониального корабля «Завет» обнаруживает, что то, что они изначально приняли за неизведанный рай, на самом деле — тёмный и опасный мир."
        ),
        Film(
            "Life",
            R.drawable.life,
            "Группа исследователей с международного космического корабля обнаруживает жизнь на Марсе. Они еще не подозревают, какие события повлечет за собой их открытие."
        ),
        Film(
            "Oblivion",
            R.drawable.oblivion,
            "Земля, пережившая войну с инопланетными захватчиками, опустела; остатки человечества готовятся покинуть непригодную для жизни планету."
        ),
        Film(
            "One punch man",
            R.drawable.one_punch_man,
            "Каково живётся самому сильному человеку в мире? Не так уж и классно, как может показаться на первый взгляд. Когда ты всего достиг, научившись выносить злодеев буквально с одного удара, жизнь теряет краски"
        ),
        Film(
            "District 9",
            R.drawable.rayon9,
            "Более 20 лет назад инопланетяне установили первый контакт с Землей. Люди были готовы ко всему — от враждебного вторжения до невероятного технологического прорыва. Ни того, ни другого не произошло."
        ),
        Film(
            "Star trek",
            R.drawable.star_trek,
            "Когда Нерон с планеты Ромул приходит из будущего, чтобы отомстить Федерации, конкуренты Кирк и Спок должны объединиться, чтобы не дать ему разрушить все, что им дорого."
        ),
        Film(
            "Guardians of the Galaxy",
            R.drawable.stragi,
            "Отважному путешественнику Питеру Квиллу попадает в руки таинственный артефакт, принадлежащий могущественному и безжалостному злодею Ронану, строящему коварные планы по захвату Вселенной."
        ),
        Film(
            "Transformers",
            R.drawable.transformers,
            "В течение многих столетий две расы роботов-инопланетян — Автоботы и Десептиконы — вели войну, ставкой в которой была судьба Вселенной. И вот война докатилась до Земли."
        ),
        Film(
            "Valerian and the City of a Thousand Planets",
            R.drawable.valerian,
            "2700 год. Валериан и Лорелин — космические спецагенты, которые по долгу службы впутались в подозрительное дело и стали невольными участниками то ли межгалактического заговора, то ли аферы причудливых поселенцев планеты Альфа, прибывших туда из различных миров со всех уголков галактик."
        ),
        Film(
            "Iron Man 2",
            R.drawable.iron,
            "Заявляя, что Тони Старк — тот самый железный человек, глава правительства США вызывает его на собрание-слушания, где просит раскрыть секрет его суперкостюма. Но Старк утверждает, что костюм — протез, который невозможно снять от него, он как будто вжился в него."
        )

    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingFrag = FragmentFirstBinding.inflate(inflater, container, false)
        val view = binding1.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bindingFrag = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingFrag?.mainRecycler?.apply {
            filmsAdapter = FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnItemClickListener{
                override fun click(film: Film) {
                    (requireActivity() as MainActivity).launchDetailsFragment(film)
                }
            })
            adapter = filmsAdapter
            layoutManager = LinearLayoutManager(requireContext())
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)
        }
        filmsAdapter.addItems(filmsDataBase)
    }
}


