package com.hva.retrofit_kotlin_example.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hva.retrofit_kotlin_example.R
import com.hva.retrofit_kotlin_example.data.model.User
import com.hva.retrofit_kotlin_example.ui.main.adapter.MainAdapter.DataViewHolder
import kotlinx.android.synthetic.main.item_user.view.*

class MainAdapter(private val users: ArrayList<User>) : RecyclerView.Adapter<DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) {
            itemView.apply {
                textViewUserName.text = user.name
                textViewUserEmail.text = user.email
                Glide.with(imageViewAvatar.context)
                    .load("https://images.pexels.com/photos/4348799/pexels-photo-4348799.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500")
                    .into(imageViewAvatar)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        )

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(users[position])
    }

    fun addUsers(users: List<User>) {
        this.users.apply {
            clear()
            addAll(users)
        }

    }
}