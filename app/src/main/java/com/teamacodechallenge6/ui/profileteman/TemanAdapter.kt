package com.teamacodechallenge6.ui.profileteman

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.teamacodechallenge6.R
import com.teamacodechallenge6.database.Teman
import com.teamacodechallenge6.database.TemanDatabase
import kotlinx.android.synthetic.main.dialog_teman.view.*
import kotlinx.android.synthetic.main.item_teman.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class TemanAdapter(val listTeman: List<Teman>, val context: Context) :
    RecyclerView.Adapter<TemanAdapter.ViewHolder>() {


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

        holder.itemView.ivEdit.setOnClickListener {
            val view = LayoutInflater.from(context).inflate(R.layout.dialog_teman, null, false)
            val dialogBuilder = androidx.appcompat.app.AlertDialog.Builder(context)
            dialogBuilder.setView(view)
            val dialog = dialogBuilder.create()
            dialog.setCancelable(true)
            view.etNama.setText(nama)
            view.etEmail.setText(email)
            val mDB = TemanDatabase.getInstance(holder.itemView.context)

            view.ibEdit.setOnClickListener {
                listTeman[position].nama = view.etNama.text.toString()
                listTeman[position].email = view.etEmail.text.toString()
                GlobalScope.async {
                    val result = mDB?.temanDao()?.updateTeman(listTeman[position])
                    (holder.itemView.context as ProfileTeman).runOnUiThread {
                        if (result != 0) {
                            Toast.makeText(
                                it.context,
                                "Sukses mengubah ${listTeman[position].nama} ",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else Toast.makeText(
                            it.context,"Gagal mengubah ${listTeman[position].nama} ", Toast.LENGTH_SHORT
                        ).show()
                    }
                    (holder.itemView.context as ProfileTeman).fetchData()
                }

                dialog.dismiss()
            }

            view.ibDelete.setOnClickListener {
                GlobalScope.async {
                    val result = mDB?.temanDao()?.deleteTeman(listTeman[position])
                    (holder.itemView.context as ProfileTeman).runOnUiThread {
                        if (result != 0) {
                            Toast.makeText(
                                it.context,
                                "Data ${listTeman[position].nama} berhasil dihapus",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else Toast.makeText(
                            it.context,"Data ${listTeman[position].nama} gagal dihapus", Toast.LENGTH_SHORT
                        ).show()
                    }
                    (holder.itemView.context as ProfileTeman).fetchData()
                }
                dialog.dismiss()
            }

            dialog.show()
        }
    }

    override fun getItemCount(): Int {
        return listTeman.size
    }

}