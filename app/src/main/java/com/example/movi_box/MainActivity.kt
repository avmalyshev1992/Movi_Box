package com.example.movi_box

import android.appwidget.AppWidgetHostView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickToast(view: View) {
        Toast.makeText(this, "Меню", Toast.LENGTH_SHORT).show()

    }

    fun onClickToast2(view: View) {
        Toast.makeText(this, "Ваше избранное", Toast.LENGTH_SHORT).show()
    }

    fun onClickToast3(view: View) {
        Toast.makeText(this, "Смотреть позже", Toast.LENGTH_SHORT).show()
    }

    fun onClickToast4(view: View) {
        Toast.makeText(this, "Ваши подборки", Toast.LENGTH_SHORT).show()
    }

    fun onClickToast5(view: View) {
        Toast.makeText(this, "Настройки", Toast.LENGTH_SHORT).show()
    }
}