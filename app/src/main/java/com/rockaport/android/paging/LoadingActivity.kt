package com.rockaport.android.paging

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.rockaport.android.paging.models.User
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoadingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        Completable.fromCallable({
            val userDao = MainApplication.database.usersDao()
            if (userDao.getCount() == 0) {
                for (i in 0 until 1000) {
                    userDao.insert(User.random())
                }
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    startActivity(Intent(this, MainActivity::class.java))
                })
    }
}
