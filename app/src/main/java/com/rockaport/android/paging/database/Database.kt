package com.rockaport.android.paging.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.rockaport.android.paging.models.User

@Database(entities = arrayOf(User::class), version = 1)
abstract class Database : RoomDatabase() {
    abstract fun usersDao(): UsersDao
}