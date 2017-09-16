package com.rockaport.android.paging.userslist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.DataSource
import android.arch.paging.PagedList
import com.rockaport.android.paging.UiThreadExecutor
import com.rockaport.android.paging.database.UsersDao
import com.rockaport.android.paging.models.User
import io.reactivex.Single
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors

class UsersPagingListViewModel : ViewModel() {
    private var dataSource: MutableLiveData<PagedList<User>> = MutableLiveData()

    fun getDataSource(userDao: UsersDao): LiveData<PagedList<User>> {
        Single.fromCallable({ userDao.getUsersTiled() })
                .map({
                    val pagedListConfig = PagedList.Config.Builder()
                            .setPageSize(50)
                            .setPrefetchDistance(50)
                            .build()

                    PagedList.Builder<Int, User>()
                            .setConfig(pagedListConfig)
                            .setDataSource(it as DataSource<Int, User>)
                            .setBackgroundThreadExecutor(Executors.newCachedThreadPool())
                            .setMainThreadExecutor(UiThreadExecutor())
                            .build()
                })
                .subscribeOn(Schedulers.io())
                .subscribe(Consumer {
                    dataSource.postValue(it)
                })

        return dataSource
    }
}