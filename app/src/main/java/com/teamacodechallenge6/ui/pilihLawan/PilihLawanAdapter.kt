package com.teamacodechallenge6.ui.pilihLawan

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teamacodechallenge6.R
import com.teamacodechallenge6.data.database.Teman
import com.teamacodechallenge6.ui.playGame.MainGamePlayer
import kotlinx.android.synthetic.main.item_teman.view.*

class PilihLawanAdapter(private val listTeman: List<Teman>, val context: Context) :
    RecyclerView.Adapter<PilihLawanAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_teman, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val nama = listTeman[position].nama
        val email = listTeman[position].email

        holder.itemView.tvNama.text = nama
        holder.itemView.tvEmail.text = email

        holder.itemView.ivEdit.visibility = View.INVISIBLE

        holder.itemView.setOnClickListener {
            val intent = Intent (context, MainGamePlayer::class.java)
            intent.putExtra("nama", nama)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listTeman.size
    }

}