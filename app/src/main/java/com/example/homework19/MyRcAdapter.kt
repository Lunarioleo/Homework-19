package com.example.homework19

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyRcAdapter(var items: List<Task> = emptyList()): RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val listItemView = LayoutInflater.from(parent.context).inflate(R.layout.rc_item, parent, false)
        return ViewHolder(listItemView)
    }
    fun updateItems(itemsToUpdate:List<Task>){
        items = itemsToUpdate
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int  = items.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.text.text = items[position].action
    }
}
class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val text: TextView = itemView.findViewById(R.id.toDoText)
}