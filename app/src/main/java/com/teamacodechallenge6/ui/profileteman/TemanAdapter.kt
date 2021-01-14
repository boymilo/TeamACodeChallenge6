package com.teamacodechallenge6.ui.profileteman

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.teamacodechallenge6.R
import com.teamacodechallenge6.database.Teman
import kotlinx.android.synthetic.main.dialog_teman.view.*
import kotlinx.android.synthetic.main.item_teman.view.*


class TemanAdapter(private val listTeman: List<Teman>, val context: Context) :
    RecyclerView.Adapter<TemanAdapter.ViewHolder>(), TemanView {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private var presenter: TemanPresenter? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_teman, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val nama = listTeman[position].nama
        val email = listTeman[position].email
        holder.itemView.tvNama.text = nama
        holder.itemView.tvEmail.text = email

        holder.itemView.ivEdit.setOnClickListener {
            val view = LayoutInflater.from(context).inflate(R.layout.dialog_teman, null, false)
            val dialogBuilder = androidx.appcompat.app.AlertDialog.Builder(context)
            dialogBuilder.setView(view)
            val dialog = dialogBuilder.create()
            dialog.setCancelable(true)
            view.etNama.setText(nama)
            view.etEmail.setText(email)
            view.ibEdit.setOnClickListener {
                listTeman[position].nama = view.etNama.text.toString()
                listTeman[position].email = view.etEmail.text.toString()
                presenter = TemanPresenterImp(this)
                presenter?.editTeman(listTeman, position)
                Toast.makeText(
                    it.context,
                    "Sukses mengubah ${listTeman[position].nama} ",
                    Toast.LENGTH_SHORT
                ).show()
                dialog.dismiss()
            }

            view.ibDelete.setOnClickListener {
                presenter = TemanPresenterImp(this)
                presenter?.deleteTeman(listTeman, position)
                Toast.makeText(
                    it.context,
                    "Data ${listTeman[position].nama} berhasil dihapus",
                    Toast.LENGTH_SHORT
                ).show()
                dialog.dismiss()
            }
            view.btClose.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }
    }

    override fun getItemCount(): Int {
        return listTeman.size
    }

    override fun onSuccessAddTeman() {
        (context as ProfileTeman).fetchData()
    }


}