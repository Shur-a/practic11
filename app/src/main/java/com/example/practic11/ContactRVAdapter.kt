package com.example.practic11

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactRVAdapter(context: Context?, val data: MutableList<contact>):
RecyclerView.Adapter<ContactRVAdapter.ContactViewHolder?>() {
    private  val layoutInflater:LayoutInflater=
       LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view:View = layoutInflater.inflate(R.layout.activity_main4,parent,false)
        return ContactViewHolder(view)
    }
    private  var iClickListener: ItemClickListener?= null
    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val item = data[position]
        holder.name1.text=item.name
        holder.surname.text=item.surname
        holder.n_telefon.text=item.n_telefon.toString()
        holder.job.text=item.job
    }

    override fun getItemCount(): Int= data.size

    inner class  ContactViewHolder(item: View): RecyclerView.ViewHolder(item),View.OnClickListener{
        var name1: TextView=item.findViewById(R.id.textView4)
        var surname: TextView=item.findViewById(R.id.textView5)
        var n_telefon: TextView=item.findViewById(R.id.textView6)
        var job: TextView=item.findViewById(R.id.textView7)
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            iClickListener?.onItemClick(view,adapterPosition)
        }
    }
    fun seClickListener(itemClickListener: ItemClickListener?){
        iClickListener=itemClickListener
    }
    interface ItemClickListener{
        fun onItemClick(view: View?,position: Int)
    }



}