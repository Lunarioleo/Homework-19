package com.example.homework26

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class FragmentList : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listView = view.findViewById<ListView>(R.id.listView)
        val fab = view.findViewById<FloatingActionButton>(R.id.floatingAddButton)
        val viewModel = ViewModelProvider(requireActivity())[MyViewModel::class.java]


        val target = viewModel.getDatabaseRef()
        fab.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, AddFragment())
                .addToBackStack("list_fragment")
                .commit()
        }

        target.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                viewModel.updateList(snapshot)
                viewModel.uiState.observe(viewLifecycleOwner){
                    when (it){
                        is MyViewModel.UiStates.ResultState->{
                            val adapter = ArrayAdapter(requireActivity(), android.R.layout.simple_list_item_1, it.l)
                            listView.adapter = adapter
                        }
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }

        })
    }
}