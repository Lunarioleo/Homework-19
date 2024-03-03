package com.example.homework26

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class AddFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myViewModel = ViewModelProvider(requireActivity())[MyViewModel::class.java]
        val button = view.findViewById<Button>(R.id.addFieldButton)
        val editField = view.findViewById<EditText>(R.id.editField)
        button.setOnClickListener {
            myViewModel.addTask(editField.text.toString())
            parentFragmentManager.popBackStack()
        }
    }
}