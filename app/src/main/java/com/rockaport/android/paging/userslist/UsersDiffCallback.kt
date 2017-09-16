package com.rockaport.android.paging.userslist

import android.support.v7.recyclerview.extensions.DiffCallback
import com.rockaport.android.paging.models.User

class UsersDiffCallback : DiffCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User) = (oldItem == newItem)

    override fun areContentsTheSame(oldItem: User, newItem: User) = (oldItem == newItem)
}