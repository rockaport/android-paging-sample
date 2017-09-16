package com.rockaport.android.paging.userslist

import android.database.Cursor
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rockaport.android.paging.R
import com.rockaport.android.paging.models.User
import kotlinx.android.synthetic.main.users_list_row.view.*

class UsersCursorListAdapter(private val cursor: Cursor) : RecyclerView.Adapter<UsersCursorListAdapter.UserViewHolder>() {
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val startTime = System.nanoTime()

        cursor.moveToPosition(position)

        val user = User.fromCursor(cursor)

        with(holder.view) {
            name.text = user.getName()
            address.text = user.address
            avatar.text = user.firstName.first().toUpperCase().toString()
        }

        Log.d("UsersCursorListAdapter", "${(System.nanoTime() - startTime) / 1000} us")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
            UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.users_list_row, parent, false))

    override fun getItemCount() = cursor.count

    fun close() {
        cursor.close()
    }

    class UserViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}