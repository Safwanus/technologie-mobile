package com.example.mytodolistapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val mList: List<ItemsViewModel>, private val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        view.setOnClickListener {
            itemClickListener.onItemClick(view)
        }

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsViewModel = mList[position]

        holder.textViewStatus.text = itemsViewModel.status
        holder.textViewTitle.text = itemsViewModel.title
        holder.textViewDescription.text = itemsViewModel.description
        holder.bind(mList[position])
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewStatus: TextView = itemView.findViewById(R.id.textViewStatus)
        val textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)
        val textViewDescription: TextView = itemView.findViewById(R.id.textViewDescription)

        fun bind(itemsViewModel: ItemsViewModel)
        {
            textViewStatus.text = itemsViewModel.status
            textViewTitle.text = itemsViewModel.title
            textViewDescription.text = itemsViewModel.description
        }

    }
}