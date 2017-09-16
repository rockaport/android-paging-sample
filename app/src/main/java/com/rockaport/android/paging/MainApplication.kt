package com.rockaport.android.paging

import android.app.Application
import android.arch.persistence.room.Room
import android.os.StrictMode
import com.rockaport.android.paging.database.Database

class MainApplication : Application() {
    companion object {
        lateinit var database: Database
    }

    override fun onCreate() {
        super.onCreate()

        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyDeath()
                .build())
        StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyDeath()
                .build())

        database = Room.databaseBuilder(this, Database::class.java, "database-name").build()
    }
}