package com.example.torutsumesamle

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class RecyclerAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, positon: Int) {
        if ((positon % 2) == 0) {
            holder.blueView.visibility = View.GONE
        }
        if ((positon % 3) == 0) {
            holder.greenView.visibility = View.GONE
        }
    }
}
