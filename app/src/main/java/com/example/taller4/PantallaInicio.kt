package com.example.taller4

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import com.example.taller4.main.ActividadPrincipal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar

class PantallaInicio : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var layout: ConstraintLayout
    private lateinit var saludoTextView: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        layout = findViewById(R.id.inicioLayout)
        saludoTextView = findViewById(R.id.saludoTextView)

        // Mostrar mensaje mientras se carga el saludo
        saludoTextView.text = "Cargando saludo"
        cargarSaludo()

        val mainImageButton: ImageButton = findViewById(R.id.mainImageButton)

        mainImageButton.setOnClickListener {
            val intent = Intent(this, ActividadPrincipal::class.java)
            startActivity(intent)
        }
    }

    override fun onResume(){
        super.onResume()
        try {
            val savedColor = sharedPreferences.getInt("backgroundColor", android.R.color.white)
            layout.setBackgroundColor(savedColor)
        } catch (e: Exception) {
            Toast.makeText(this, "Error al cargar el color de fondo", Toast.LENGTH_SHORT).show()
        }
    }

    private fun cargarSaludo(){
        lifecycleScope.launch {
            try {
                val saludo = withContext(Dispatchers.IO){
                    getSaludoSegunHora()
                }
                saludoTextView.text = saludo
            } catch (e: Exception){
                saludoTextView.text = "Error al cargar el saludo"
                Toast.makeText(this@PantallaInicio, "Error al cargar el saludo", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private suspend fun getSaludoSegunHora(): String {
        delay(1000)
        val horaActual = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        return when (horaActual){
            in 6..11 -> "Buenos dias"
            in 12..19 -> "Buenas tardes"
            else -> "Buenas noches"
        }.uppercase()
    }
}