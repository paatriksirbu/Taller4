package com.example.taller4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListFragment : Fragment() {
    private lateinit var viewModel: SharedViewModel
    private lateinit var adapter: MyAdapter

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

        viewModel.items.observe(viewLifecycleOwner) { items ->
            adapter.updateItems(items)
        }

        val editTextNewItem = view.findViewById<EditText>(R.id.editTextNewItem)
        val buttonAddItem = view.findViewById<Button>(R.id.buttonAddItem)
        buttonAddItem.setOnClickListener {
            val newItem = editTextNewItem.text.toString()
            if (newItem.isNotEmpty()) {
                viewModel.addItem(newItem)
                editTextNewItem.text.clear()
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
