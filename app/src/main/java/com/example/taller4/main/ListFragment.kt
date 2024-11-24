package com.example.taller4.main

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taller4.ItemRepository
import com.example.taller4.MyAppWidgetProvider
import com.example.taller4.R
import com.example.taller4.SharedViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment : Fragment() {
    private lateinit var viewModel: SharedViewModel
    private lateinit var adapter: MyAdapter
    private lateinit var sharedPreferences: SharedPreferences
    private val itemRepository = ItemRepository()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Inflamos el layout para este fragmento
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        // Inicializar RecyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Inicializar Adapter y asignarlo al RecyclerView
        adapter = MyAdapter(mutableListOf()) { selectedItem ->
            viewModel.selectItem(selectedItem)
        }
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        sharedPreferences = requireActivity().getSharedPreferences("widget_data", Context.MODE_PRIVATE)

        viewModel.items.observe(viewLifecycleOwner) { items ->
            adapter.updateItems(items)

            // Actualizar SharedPreferences con la nueva lista de elementos
            val itemsString = items.joinToString(separator = ",")
            sharedPreferences.edit().putString("items_list", itemsString).apply()

            // Actualizar el widget
            val intent = Intent(requireContext(), MyAppWidgetProvider::class.java).apply {
                action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
            }
            val ids = AppWidgetManager.getInstance(requireContext())
                .getAppWidgetIds(ComponentName(requireContext(), MyAppWidgetProvider::class.java))
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids)
            requireContext().sendBroadcast(intent)
        }
        val editTextNewItem = view.findViewById<EditText>(R.id.editTextNewItem)
        val fabAddItem = view.findViewById<FloatingActionButton>(R.id.fabAddItem)

        fabAddItem.setOnClickListener {
            val newItem = editTextNewItem.text.toString()
            if (newItem.isNotEmpty()) {
                viewModel.addItem(newItem)
                editTextNewItem.text?.clear()

                itemRepository.addItem(newItem)
            } else {
                Toast.makeText(context, "Por favor ingrese un elemento", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //Iniciar el ViewModel compartido
        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
    }
}
