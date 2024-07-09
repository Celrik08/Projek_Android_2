package com.example.latihan_2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RVAdapter(
    private val context: Context,
    private val dataList: ArrayList<UserMode>
): RecyclerView.Adapter<RVAdapter.MyViewHolder>() {

    class MyViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val tvid = view.findViewById<TextView>(R.id.tv_id)
        val tvemail = view.findViewById<TextView>(R.id.tv_email)
        val tvND = view.findViewById<TextView>(R.id.tv_first_name)
        val tvNB = view.findViewById<TextView>(R.id.tv_last_name)
        val rvMain = view.findViewById<CardView>(R.id.rvMain)
        val ImageView = view.findViewById<ImageView>(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.items_main, parent, false)
        return MyViewHolder(itemView)
    }

    // Di dalam onBindViewHolder di RVAdapter.kt
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvid.text = dataList[position].id.toString() // Mengonverensi Int menjadi String terlebih dahulu
        holder.tvemail.text = dataList.get(position).email
        holder.tvND.text = dataList.get(position).first_name
        holder.tvNB.text = dataList.get(position).last_name

        // Menggunakan Glide untuk memuat gambar ke ImageView
        Glide.with(context)
            .load(dataList[position].avatar) // URL gambar
            .into(holder.ImageView) // ImageView yang akan menampilkan gambar
        holder.rvMain?.setOnClickListener{
            Toast.makeText(context, "" + dataList.get(position).first_name, Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int =dataList.size

    fun setData(data: ArrayList<UserMode>){
        dataList.clear()
        dataList.addAll(data)
        notifyDataSetChanged()
    }
}