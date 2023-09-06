package com.example.movi_box.data

import android.content.ContentValues
import android.database.Cursor
import com.example.movi_box.R
import com.example.movi_box.domain.Film

class MainRepository(databaseHelper: DatabaseHelper) {
    //Инициализируем объект для взаимодействия с БД
    private val sqlDb = databaseHelper.readableDatabase
    //Создаем курсор для обработки запросов из БД
    private lateinit var cursor: Cursor

    fun putToDb(film: Film) {
        //Создаем объект, который будет хранить пары ключ-значение, для того
        //чтобы класть нужные данные в нужные столбцы
        val cv = ContentValues()
        cv.apply {
            put(DatabaseHelper.COLUMN_TITLE, film.title)
            put(DatabaseHelper.COLUMN_POSTER, film.poster)
            put(DatabaseHelper.COLUMN_DESCRIPTION, film.description)
            put(DatabaseHelper.COLUMN_RATING, film.rating)
        }
        //Кладем фильм в БД
        sqlDb.insert(DatabaseHelper.TABLE_NAME, null, cv)
    }

    fun getAllFromDB(): List<Film> {
        //Создаем курсор на основании запроса "Получить все из таблицы"
        cursor = sqlDb.rawQuery("SELECT * FROM ${DatabaseHelper.TABLE_NAME}", null)
        //Сюда будем сохранять результат получения данных
        val result = mutableListOf<Film>()
        //Проверяем, есть ли хоть одна строка в ответе на запрос
        if (cursor.moveToFirst()) {
            //Итерируемся по таблице, пока есть записи, и создаем на основании объект Film
            do {
                val title = cursor.getString(1)
                val poster = cursor.getString(2)
                val description = cursor.getString(3)
                val rating = cursor.getDouble(4)

                result.add(Film(title, poster, description, rating))
            } while (cursor.moveToNext())
        }
        //Возвращаем список фильмов
        return result
    }
}
  /*  companion object {
        val filmsDataBase: List<Film> = listOf(

            Film(
                "Alien covenant Redemption",
                R.drawable.alien_covenant,
                "Во время обследования удаленной планеты, расположенной на другой стороне галактики, экипаж колониального корабля «Завет» обнаруживает, что то, что они изначально приняли за неизведанный рай, на самом деле — тёмный и опасный мир.",
                6.9f
            ),
            Film(
                "Life",
                R.drawable.life,
                "Группа исследователей с международного космического корабля обнаруживает жизнь на Марсе. Они еще не подозревают, какие события повлечет за собой их открытие.",
                7.5f
            ),
            Film(
                "Oblivion",
                R.drawable.oblivion,
                "Земля, пережившая войну с инопланетными захватчиками, опустела; остатки человечества готовятся покинуть непригодную для жизни планету.",
                7.0f
            ),
            Film(
                "One punch man",
                R.drawable.one_punch_man,
                "Каково живётся самому сильному человеку в мире? Не так уж и классно, как может показаться на первый взгляд. Когда ты всего достиг, научившись выносить злодеев буквально с одного удара, жизнь теряет краски",
                9.3f
            ),
            Film(
                "District 9",
                R.drawable.rayon9,
                "Более 20 лет назад инопланетяне установили первый контакт с Землей. Люди были готовы ко всему — от враждебного вторжения до невероятного технологического прорыва. Ни того, ни другого не произошло.",
                8.1f
            ),
            Film(
                "Star trek",
                R.drawable.star_trek,
                "Когда Нерон с планеты Ромул приходит из будущего, чтобы отомстить Федерации, конкуренты Кирк и Спок должны объединиться, чтобы не дать ему разрушить все, что им дорого.",
                7.7f
            ),
            Film(
                "Guardians of the Galaxy",
                R.drawable.stragi,
                "Отважному путешественнику Питеру Квиллу попадает в руки таинственный артефакт, принадлежащий могущественному и безжалостному злодею Ронану, строящему коварные планы по захвату Вселенной.",
                8.8f
            ),
            Film(
                "Transformers",
                R.drawable.transformers,
                "В течение многих столетий две расы роботов-инопланетян — Автоботы и Десептиконы — вели войну, ставкой в которой была судьба Вселенной. И вот война докатилась до Земли.",
                7.1f
            ),
            Film(
                "Valerian and the City of a Thousand Planets",
                R.drawable.valerian,
                "2700 год. Валериан и Лорелин — космические спецагенты, которые по долгу службы впутались в подозрительное дело и стали невольными участниками то ли межгалактического заговора, то ли аферы причудливых поселенцев планеты Альфа, прибывших туда из различных миров со всех уголков галактик.",
                6.5f
            ),
            Film(
                "Iron Man 2",
                R.drawable.iron,
                "Заявляя, что Тони Старк — тот самый железный человек, глава правительства США вызывает его на собрание-слушания, где просит раскрыть секрет его суперкостюма. Но Старк утверждает, что костюм — протез, который невозможно снять от него, он как будто вжился в него.",
                8.6f
            )

        )
    }*/
