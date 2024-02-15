package com.example.homework19


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rcView: RecyclerView = findViewById(R.id.rcView)
        val btnAdd: FloatingActionButton = findViewById(R.id.btnAdd)
        val viewModel = ViewModelProvider(this).get(ViewModel::class.java)


        rcView.layoutManager = LinearLayoutManager(this)
        val myAdapter = MyRcAdapter()
        rcView.adapter = myAdapter

        viewModel.listState.observe(this){ uiState->
            when (uiState){
                is ViewModel.States.EmptyList -> Unit
                is ViewModel.States.UpdatedList ->{
                    myAdapter.updateItems(uiState.list)
                }
            }
        }
        btnAdd.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, AddFragment())
                .addToBackStack(AddFragment().javaClass.name)
                .commit()
        }


        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int = makeMovementFlags(0, ItemTouchHelper.END)

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                if (direction == ItemTouchHelper.END) {
                    viewModel.removeFromList(myAdapter.items[viewHolder.adapterPosition])
                }
            }
        })
        itemTouchHelper.attachToRecyclerView(rcView)
    }


}