package com.example.homework19



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider




class AddFragment : Fragment(){

    private val adapterState = MutableLiveData<MyRcAdapter>()
    private lateinit var viewModel: ViewModel
    private lateinit var myAdapter: MyRcAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        super.onViewCreated(view, savedInstanceState)
         viewModel = ViewModelProvider(this).get(ViewModel::class.java)

        val inputField: EditText = view.findViewById(R.id.editText)
        val btn: Button = view.findViewById(R.id.frBtnAdd)

        btn.setOnClickListener {
            val text = inputField.text.toString()
            viewModel.addToList(text)
            parentFragmentManager.popBackStack()
        }















    }
}


