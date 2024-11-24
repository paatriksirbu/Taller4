package com.example.taller4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ActividadPrincipal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actividad_principal)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_list_container, ListFragment())
                .commit()
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_detail_container, DetailFragment())
            .commit()

    }
}
