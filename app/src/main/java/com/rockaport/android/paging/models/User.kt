package com.rockaport.android.paging.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.database.Cursor
import com.rockaport.android.paging.database.UsersTable
import io.bloco.faker.Faker

@Entity(tableName = UsersTable.name)
data class User(@ColumnInfo(name = UsersTable.id)
                @PrimaryKey(autoGenerate = true)
                val id: Long = 0,
                @ColumnInfo(name = UsersTable.firstName)
                val firstName: String,
                @ColumnInfo(name = UsersTable.lastName)
                val lastName: String,
                @ColumnInfo(name = UsersTable.address)
                val address: String) {

    fun getName(): String = "$firstName $lastName"

    companion object {
        private val faker = Faker()

        fun random(): User {
            with(faker) {
                return User(firstName = name.firstName(),
                        lastName = name.lastName(),
                        address = address.streetAddress())
            }
        }

        fun fromCursor(cursor: Cursor): User {
            with(cursor) {
                return User(id = getLong(getColumnIndex(UsersTable.id)),
                        firstName = getString(getColumnIndex(UsersTable.firstName)),
                        lastName = getString(getColumnIndex(UsersTable.lastName)),
                        address = getString(getColumnIndex(UsersTable.address)))
            }
        }
    }
}