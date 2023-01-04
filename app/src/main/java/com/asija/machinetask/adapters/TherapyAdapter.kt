package com.asija.machinetask.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asija.machinetask.R
import com.asija.machinetask.databinding.ItemTherapyBinding
import com.asija.machinetask.model.Data
import com.bumptech.glide.Glide

class TherapyAdapter(private val mContext: Context,val list: List<Data>): RecyclerView.Adapter<TherapyAdapter.MyViewHolder>() {


    class MyViewHolder(val binding:ItemTherapyBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemTherapyBinding.inflate(LayoutInflater.from(mContext),parent,false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {
            Glide.with(mContext).load(list[position].profile).placeholder(R.drawable.picture).into(image)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}