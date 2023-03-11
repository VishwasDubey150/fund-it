package com.example.fund_it

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val newlist:ArrayList<lists>):
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val curr=newlist[position]
        holder.titleImage.setImageResource(curr.titleImage)
        holder.tvHeading.text=curr.name
    }

    override fun getItemCount(): Int {
        return newlist.size
    }


    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        val titleImage: ImageView=itemView.findViewById(R.id.title_image)
        val tvHeading: TextView=itemView.findViewById(R.id.tvheading)
    }


}



























//    private  lateinit var mListener : onItemClickListener
//
//    interface onItemClickListener
//    {
//        fun onItemClick(position: Int)
//    }
//
//    fun setOnItemClickListener(listener: onItemClickListener)
//    {
//        mListener=listener
//    }
//
//    fun deleteItem(i:Int)
//    {
//        newlist.removeAt(i)
//        notifyDataSetChanged()
//    }
//
//    fun addItem(i:Int,news :lists)
//    {
//        newlist.add(i,news)
//        notifyDataSetChanged()
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//
//        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
//
//        return MyViewHolder(itemView,mListener)
//    }
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        val curr=newlist[position]
//        holder.image.setImageResource(curr.titleImage)
//        holder.titlename.text=curr.name
//    }
//
//    override fun getItemCount(): Int {
//        return newlist.size
//    }
//    class MyViewHolder(itemView: View,
////                       listener: onItemClickListener
//                       ):RecyclerView.ViewHolder(itemView) {
//
//}
//    }
//        val image:ImageView=itemView.findViewById(R.id.img)
//        val titlename:TextView=itemView.findViewById(R.id.name)
//
//
//        init {
//            itemView.setOnClickListener {
//                listener.onItemClick(adapterPosition)
//            }
//        }
//    }

