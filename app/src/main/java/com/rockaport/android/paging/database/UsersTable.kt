package com.rockaport.android.paging.database

import android.provider.BaseColumns

class UsersTable {
    companion object {
        const val name = "users"
        const val id = BaseColumns._ID
        const val firstName = "first_name"
        const val lastName = "last_name"
        const val address = "address"
    }
}