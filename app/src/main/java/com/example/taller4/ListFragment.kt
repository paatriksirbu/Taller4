package com.example.taller4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taller4.MyAdapter

class ListFragment : Fragment() {
    private lateinit var viewModel: SharedViewModel

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
        val adapter = MyAdapter { selectedItem ->
            viewModel.selectItem(selectedItem)
        }
        recyclerView.adapter = adapter

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //Iniciar el ViewModel compartido
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
    }
}
