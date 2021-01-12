package com.teamacodechallenge6.ui.pilihLawan

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teamacodechallenge6.R
import com.teamacodechallenge6.database.Teman
import com.teamacodechallenge6.ui.profileteman.ProfileTeman
import kotlinx.android.synthetic.main.item_teman.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class PilihLawanAdapter(val listTeman: List<Teman>, val context: Context) :
    RecyclerView.Adapter<PilihLawanAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_teman, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var nama = listTeman[position].nama
        var email = listTeman[position].email
        holder.itemView.tv_nama.text = nama
        holder.itemView.tv_email.text = email

        holder.itemView.ivEdit.visibility = View.INVISIBLE

        holder.itemView.setOnClickListener {
            var intent = Intent (context, ProfileTeman::class.java)
            intent.putExtra("nama", nama)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listTeman.size
    }

}