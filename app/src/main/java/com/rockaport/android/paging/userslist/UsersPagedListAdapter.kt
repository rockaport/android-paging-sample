package com.rockaport.android.paging.userslist

import android.arch.paging.PagedListAdapter
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rockaport.android.paging.R
import com.rockaport.android.paging.models.User
import kotlinx.android.synthetic.main.users_list_row.view.*

class UsersPagedListAdapter : PagedListAdapter<User, UsersPagedListAdapter.UserViewHolder>(UsersDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
            UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.users_list_row, parent, false))

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val startTime = System.nanoTime()

        val user = getItem(position)

        when (user) {
            null -> {
                holder.clear()
                return
            }
            else -> with(holder.view) {
                name.text = user.getName()
                address.text = user.address
                avatar.text = user.firstName.first().toUpperCase().toString()
            }
        }

        Log.d("UsersPagedListAdapter", "${(System.nanoTime() - startTime) / 1000} us")
    }

    class UserViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun clear() {
            with(view) {
                name.text = ""
                address.text = ""
                avatar.text = ""
            }
        }
    }
}