package com.example.taller4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class DetailFragment : Fragment() {

    private lateinit var viewModel: SharedViewModel
    private lateinit var detailTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        detailTextView = view.findViewById(R.id.detailTextView)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        viewModel.getSelectedItem().observe(viewLifecycleOwner, Observer { selectedItem ->
            selectedItem?.let {
                detailTextView.text = "Detalles del elemento seleccionado: $it"
            }
        })
    }
}