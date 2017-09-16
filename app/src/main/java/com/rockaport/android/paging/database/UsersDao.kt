package com.rockaport.android.paging.database

import android.arch.paging.TiledDataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.database.Cursor
import com.rockaport.android.paging.models.User

@Dao
interface UsersDao {
    @Insert
    fun insert(user: User)

    @Query("select count(*) from ${UsersTable.name}")
    fun getCount(): Int

    @Query("select * from ${UsersTable.name}")
    fun getUsers(): List<User>

    @Query("select * from ${UsersTable.name}")
    fun getUsersTiled(): TiledDataSource<User>

    @Query("select * from ${UsersTable.name}")
    fun getUsersCursor(): Cursor
}