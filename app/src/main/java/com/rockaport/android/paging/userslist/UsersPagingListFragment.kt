package com.rockaport.android.paging.userslist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rockaport.android.paging.MainApplication
import com.rockaport.android.paging.R
import kotlinx.android.synthetic.main.fragment_users_list.view.*


class UsersPagingListFragment : Fragment() {
    private lateinit var viewModelPaging: UsersPagingListViewModel
    private val usersPagedListAdapter: UsersPagedListAdapter = UsersPagedListAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_users_list, container)

        viewModelPaging = ViewModelProviders.of(this).get(UsersPagingListViewModel::class.java)

        with(view.users_list) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = usersPagedListAdapter
        }

        return view
    }

    override fun onStart() {
        super.onStart()

        viewModelPaging.getDataSource(MainApplication.database.usersDao()).observe(this, Observer {
            usersPagedListAdapter.setList(it)
        })
    }
}