package com.example.torutsumesamle

import android.support.v7.widget.RecyclerView
import android.view.View

class RecyclerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val purpleView =  itemView.findViewById<View>(R.id.purpleView)
    val blueView =  itemView.findViewById<View>(R.id.blueView)
    val greenView =  itemView.findViewById<View>(R.id.greenView)
    val yellowView =  itemView.findViewById<View>(R.id.yellowView)
}
