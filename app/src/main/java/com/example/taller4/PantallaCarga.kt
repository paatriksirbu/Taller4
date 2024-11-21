package com.example.taller4

import android.content.Intent
import android.graphics.drawable.Animatable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import java.lang.Thread.sleep


class PantallaCarga : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_carga)

        val progressIndicator: ImageView = findViewById(R.id.progressIndicator)

        // Inicia la animación
        val drawable = progressIndicator.drawable
        if (drawable is Animatable) {
            drawable.start()
            Thread{
                sleep(2000) //Simulamos la carga de la app y redirigimos a la activity de pantalla inicio.
                val intent = Intent(this, PantallaInicio::class.java)
                startActivity(intent)
                finish()    //Asegura terminar la activity de PantallaCarga.
            }.start()
        }
    }
}
