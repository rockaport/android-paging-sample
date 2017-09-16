package com.rockaport.android.paging.userslist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.database.Cursor
import com.rockaport.android.paging.database.UsersDao
import io.reactivex.Single
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class UsersCursorListViewModel : ViewModel() {
    private var dataSource: MutableLiveData<Cursor> = MutableLiveData()

    fun getDataSource(userDao: UsersDao): LiveData<Cursor> {
        Single.fromCallable({ userDao.getUsersCursor() })
                .subscribeOn(Schedulers.io())
                .subscribe(Consumer {
                    dataSource.postValue(it)
                })

        return dataSource
    }
}