package com.example.sammary3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(private val usersList: MutableList<User>, val listener: UserItemListener) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = usersList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind()
    }                                                                               

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind() {
            val model = usersList[adapterPosition]
            val tvFirstName = itemView.findViewById(R.id.tvFirstName) as TextView
            val tvLastName = itemView.findViewById(R.id.tvLastName) as TextView
            val tvEmail = itemView.findViewById(R.id.tvEmail) as TextView

            val btnUpdate = itemView.findViewById(R.id.btnUpdate) as Button
            val btnDelete = itemView.findViewById(R.id.btnDelete) as Button

            tvFirstName.text = model.firstName
            tvLastName.text = model.lastName
            tvEmail.text = model.email

            btnUpdate.setOnClickListener {
                listener.updateUser(adapterPosition)
            }

            btnDelete.setOnClickListener {
                listener.deleteUser(adapterPosition)
            }
        }
    }
}